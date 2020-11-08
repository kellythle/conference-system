package UseCase;

import Entity.Event;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class stores a list of existing events, legal starting
 * time, legal ending time and manages every event related command.
 *
 * @author Kelly Le, Filip Jovanovic, An Yen
 */
public class EventManager {

    private ArrayList<Event> eventList;
    private int startTime = 9; // the opening time of this conference is 9 am.
    private int endTime = 17; // the ending time of this conference is 5 pm.

    /**
     * Returns the starting time of this conference
     *
     * @return the startTime of this conference
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Returns the ending time of this conference.
     *
     * @return the endTime of this conference
     */
    public int getEndTime() {
        return endTime;
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
     * Check if the starting of the event wanted to be created is in the legal starting and ending time of
     * the conference.
     *
     * @param time - the occurring time of this event
     * @return true if the event can be created and false if cannot
     */
    private boolean canCreateEvent(Date time){
        if(time.getHours() >= this.getStartTime() && time.getHours() < this.getEndTime()){
            return true;
        }
        return false;
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
    private Event createEvent(String name, String speaker,
                              Date time, Pair<Integer, Integer> room) {
        return new Event(name, speaker, time, room);
    }

    /**
     * Returns true if an event is created and added to the conference successfully.
     * Returns false if the event cannot be created or added.
     * Checks if there exists an event occurring in the same room and
     * at the same time. Also checks if the speaker gives another talk
     * at the same time.
     *
     * @param name - the name of this event
     * @param speaker - the speaker's name
     * @param time - the occurring time of this event
     * @param room - the occurring room of this event
     * @return true if successfully creates and adds the new event to the conference and false otherwise
     */
    public boolean addEvent(String name, String speaker,
                            Date time, Pair<Integer, Integer> room) {
        if (!canCreateEvent(time)){
            return false;
        }

        for (Event e : eventList) {
            if ((e.getTime() == time && e.getRoomNum().equals(room.getKey())) || (e.getTime() == time
                    && e.getSpeaker().equals(speaker))) {
                return false;
            }
        }
        Event newEvent = createEvent(name, speaker, time, room);
        this.eventList.add(newEvent);
        return true;
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
     * @param event - the event the user wanted to sign up for.
     * @return true is the user can sign up for this event. Otherwise false.
     */
    private boolean canAddUserToEvent(String username, Event event){
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
     * @param event - the event the user wanted to sign up for.
     * @return true if this user is added successfully yo he event, otherwise false.
     */
    public boolean addUserToEvent(String username, Event event){
        if (canAddUserToEvent(username, event)){
            event.addAttendee(username);
            return true;
        }
        return false;
    }
}
