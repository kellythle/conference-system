package UseCase;

import Entity.Event;
import javafx.util.Pair;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class stores a list of existing events, legal starting
 * time, legal ending time and manages every event related command.
 *
 * @author Kelly Le, Filip Jovanovic, An Yen
 */
public class EventManager implements Serializable {

    private ArrayList<Event> eventList = new ArrayList<>();
    private ArrayList<Pair<Integer, Integer>> roomList = new ArrayList<>();

    /**
     * Returns the starting time of this conference
     *
     * @return the startTime of this conference
     */
    public int getStartTime() {
        // the opening time of this conference is 9 am.
        return 9;
    }

    /**
     * Returns the ending time of this conference.
     *
     * @return the endTime of this conference
     */
    public int getEndTime() {
        // the ending time of this conference is 5 pm.
        return 17;
    }

    /**
     * Return a list of all possible start times.
     *
     * @return an ArrayList of strings of start times.
     */
    public ArrayList<String> getStartTimes () {
        ArrayList<String> availableTimes = new ArrayList<>();
        int startTime = this.getStartTime();
        while (startTime < this.getEndTime()){
            if (startTime < 10){
                availableTimes.add("0" + startTime);
            } else {
                availableTimes.add(Integer.toString(startTime));
            }
            startTime += 1;
        }
        return availableTimes;
    }


    /**
     * Returns the list of events.
     *
     * @return event list
     */
    public ArrayList<Event> getEventList() {
        return eventList;
    }


    /**
     * Sets the list of events to a version from a read file
     * @param readInList The version of eventList from a read file
     */
    public void setEventList(ArrayList<Event> readInList) {
        this.eventList = readInList;
    }

    /**
     * Returns the list of rooms.
     *
     * @return room list
     */
    public ArrayList<Pair<Integer, Integer>> getRoomList() {
        return roomList;
    }

    /**
     * Returns the room pair given the room's number.
     * @param roomNum - the room's number
     *
     * @return the room pair
     */
    public Pair<Integer, Integer> getRoom(Integer roomNum){
        for (Pair<Integer, Integer> r: this.getRoomList()){
            if(r.getKey().equals(roomNum)){
                return r;
            }
        }
        return null;
    }

    /**
     * Sets the room list to a version from a read file
     * @param readInRoomList The version of roomList from a read file
     */
    public void setRoomList(ArrayList<Pair<Integer, Integer>> readInRoomList){
        this.roomList = readInRoomList;
    }

    /**
     * Returns an event that is created.
     *
     * @param name - the name of this event
     * @param speaker - the speaker's name
     * @param time - the occurring time of this event
     * @param room - the occurring room of this event
     * @return the Event that is created
     */
    private Event createNewEvent(String name, String speaker,
                                 LocalDateTime time, Pair<Integer, Integer> room) {
        return new Event(name, speaker, time, room);
    }

    /**
     * Returns true if the event name exists in the event list.
     *
     * @param name - the name of the event
     *
     * @return true if the event name exists
     */
    public boolean getEvent(String name){
        if (this.getEventList() == null || this.getEventList().isEmpty()){
            return false;
        }

        for (Event e: this.getEventList()) {
            if (e.getName().toLowerCase().trim().equals(name.toLowerCase().trim())){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the event instance if the event name exists in the event list.
     * Else returns null.
     * @param name - the event name
     * @return the Event instance with the corresponding event name
     */
    private Event findEventByName(String name){
        for (Event e: eventList){
            if(e.getName().equals(name)){
                return e;
            }
        }
        return null;
    }

    /**
     * Creates an event and is added to the conference.
     * Passes silently if event was not able to be made.
     * Checks if there exists an event occurring in the same room and
     * at the same time, if the speaker gives another talk
     * at the same time, and if the event name already exists.
     *
     * @param name - the name of this event
     * @param speaker - the speaker's name
     * @param time - the occurring time of this event
     * @param room - the occurring room of this event
     */
    public void addEvent(String name, String speaker,
                         LocalDateTime time, Pair<Integer, Integer> room) {
        Event newEvent = createNewEvent(name, speaker, time, room);
        if(eventList == null || eventList.isEmpty()) {
            this.eventList = new ArrayList<>();
            this.eventList.add(newEvent);
        }
        for (Event e : eventList) {
            if ((e.getTime() == time && e.getRoomNum().equals(room.getKey())) || (e.getTime() == time
                    && e.getSpeaker().equals(speaker)) || e.getName().equals(name)) {
                return;
            }
        }
        ArrayList<Event> sortedEvents = new ArrayList<>();
        boolean Added = false;
        for (Event e : eventList) {
            if (e.getTime().isAfter(newEvent.getTime()) && !Added) {
                sortedEvents.add(newEvent);
                sortedEvents.add(e);
                Added = true;
            } else {
                sortedEvents.add(e);
            }
        }
        if (!Added){
            sortedEvents.add(newEvent);
        }
        this.eventList = sortedEvents;

    }

    /**
     * Decide whether this user can sign up for this event or not.
     * If the user is already in this event, or this user had signed up
     * another event that occurs at the same time as this event, or this
     * event is already fulled, or this user is the speaker of this event
     * this user cannot sign up for this event and this method returns false.
     * Otherwise the user can sign up for this event and this method
     * returns true.
     *
     * @param username - the username of this user.
     * @param eventName - the event's name the user wanted to sign up for.
     * @return true is the user can sign up for this event. Otherwise false.
     */
    private boolean canAddUserToEvent(String username, String eventName){
        Event event = findEventByName(eventName);
        assert event != null;
        if (event.getSpeaker().equals(username)){
            return false;
        }
        if (event.getRoomCapacity().equals(event.getAttendees().size())) {
            return false;
        }
        for (Event e: eventList){
            if(event.getTime().equals(e.getTime())) {
                if(e.getAttendees().contains(username)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if the user is added successfully to
     * this event, and returns false if fails to add this
     * user to this event.
     *
     * @param username - the name of the user
     * @param eventName - the event's name the user wanted to sign up for.
     * @return true if this user is added successfully to the event, otherwise false.
     */
    public boolean addUserToEvent(String username, String eventName){
        Event event = findEventByName(eventName);
        if (canAddUserToEvent(username, eventName)){
            assert event != null;
            event.addAttendee(username);
            return true;
        }
        return false;
    }

    /**
     * Returns true if the user is successfully deleted from
     * this event, and returns false if fails to delete this
     * user from this event (not in event list).
     *
     * @param username - the name of the user
     * @param eventName - the event's name the user wanted to sign up for.
     * @return true if this user is successfully deleted from the event, otherwise false.
     */
    public boolean deleteUserFromEvent(String username, String eventName){
        for (Event e: this.eventList){
            if (e.getName().equals(eventName)) {
                if (e.getAttendees().contains(username)) {
                    return e.getAttendees().remove(username);
                }
            }
        }
        return false;
    }

    /**
     * Return a list of Speakers available at the given time.
     *
     * @param speakerList - the list of existing speakers
     * @param time - the chosen time
     *
     *
     * @return a list of available Speakers
     */
    public ArrayList<String> getAvailableSpeakers(ArrayList<String> speakerList, LocalDateTime time) {
        ArrayList<String> availableList = new ArrayList<>();
        ArrayList<String> unavailableList = new ArrayList<>();
        if (this.eventList != null) {
            for (Event e : this.eventList) {
                if (e.getTime().compareTo(time) == 0) {
                    unavailableList.add(e.getSpeaker());
                }
            }
        }

        for (String s : speakerList) {
            if (!unavailableList.contains(s)) {
                availableList.add(s);
            }
        }
        return availableList;
    }

    /**
     * Returns a list of available rooms.
     *
     * @param time - time for Event
     *
     * @return a list of available rooms
     */
    public ArrayList<Integer> getAvailableRooms (LocalDateTime time) {
        ArrayList<Integer> availableRooms = new ArrayList<>();
        ArrayList<Integer> unavailableRooms = new ArrayList<>();
        if (this.eventList != null) {
            for (Event e : this.eventList) {
                if (e.getTime().compareTo(time) == 0) {
                    unavailableRooms.add(e.getRoomNum());
                }
            }
        }

        for (Pair<Integer, Integer> r : this.roomList) {
            if (!unavailableRooms.contains(r.getKey())) {
                availableRooms.add(r.getKey());
            }
        }
        return availableRooms;
    }

    /**
     * Generates a display format of events.
     *
     * @param eventList - an arraylist of events
     * @return a String object that is the display format of events.
     */
    public String eventListGenerator(ArrayList<Event> eventList){
        String events = "";
        if (eventList == null){
            return events;
        }
        for (Event e: eventList){
            int space = e.getRoomCapacity() - e.getAttendees().size();
            String available = "Full";
            if (space > 0){
                available = "Available";
            }
            events += "Name: " + e.getName() +
                    ", Time: " + e.getTime().toString() +
                    ", Speaker: " + e.getSpeaker() +
                    ", Room Number: " + e.getRoomNum().toString() + ", " +
                    available + "\n";
        }
        return events;
    }

    public ArrayList<Event> getRegisteredEvents(ArrayList<String> registeredEventNames){
        ArrayList<Event> registeredEvents = new ArrayList<>();
        for (Event e : eventList) {
            if (registeredEventNames.contains(e.getName())) {
                registeredEvents.add(e);
            }
        }
        return registeredEvents;
    }

    public ArrayList<String> getEventListBySpeaker(String username) {
        ArrayList<String> events = new ArrayList<>();
        for (Event e: eventList) {
            if (e.getSpeaker().equals(username)) {
                events.add(e.getName());
            }
        }
        return events;
    }

    public ArrayList<String> getEventAttendees(String eventName) {
        ArrayList<String> users = new ArrayList<>();
        for (Event e : eventList) {
            if (e.getName().equals(eventName)) {
                users = e.getAttendees();
            }
        }
        return users;
    }
}
