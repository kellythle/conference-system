package Presenter;

import Entity.Event;
import UseCase.EventManager
import UseCase.UserManager

/**
 * A presenter class that returns values that will be displayed in the UI
 * when an Organizer is scheduling events.
 *
 * @author Kelly Le
 */

public class SchedulePresenter {

    private EventManager eventManager = new EventManager();
    private UserManager userManager = new UserManager();

    /**
     * Returns a string of speakers that are available and unavailable
     * given the room and time chosen by the Organizer.
     *
     * @param speakerList - the list of existing speakers
     * @param room - the chosen room
     * @param time - the chosen time
     *
     * @return a string of available and unavailable speakers
     */
    public String displaySpeakerList(ArrayList<String> speakerList, Pair<Integer, Integer> room, Date time) {
        String available_speakers = "Available Speakers: ";
        String unavailable_speakers = "Unavailable Speakers: ";
        ArrayList<String> unavailable_list = new ArrayList<>();
        for (Event e : eventManager.getEventList()) {
            if (e.getTime() == time && e.getRoomNum().equals(room.getKey())) {
                unavailable_speakers += e.getSpeaker() + ", ";
                unavailable_list.add(e.getSpeaker());
            }
        }

        for (String s : userManager.getSpeakerList()) {
            if (!unavailable_list.contains(s)) {
                available_speakers += s + ", ";
            }

            available_speakers = available_speakers.replaceAll(", $", "");
            unavailable_speakers = unavailable_speakers.replaceAll(", $", "");
            return available_speakers + "\n" + unavailable_speakers;
        }
    }

    /**
     * Returns a string of all the available start times for an event.
     *
     * @return a string of available start times
     */
    public String displayStartTimes () {
        String available_times = "";
        int start_time = eventManager.getStartTime();
        while (start_time < eventManager.getEndTime()){
            available_times += start_time.toString() + ", ";
            start_time += 1;
        }

        available_times = available_times.replaceAll(", $", "");
        return available_times;

    }

    /**
     * Returns a string of all the available rooms for an event given the chosen time.
     *
     * @param time - the chosen time
     *
     * @return a string of available rooms
     */
    public String displayAvailableRooms (Date time) {
        String available_rooms = "";
        ArrayList <Pair<Integer, Integer>> unavailable_rooms = new ArrayList<>();
        for (Event e : eventManager.getEventList()) {
            if (e.getTime() == time){
                Pair<Integer, Integer> unavailable_room = <e.getRoomNum(), getRoomCapacity()>;
                unavailable_rooms.add(unavailable_room);
            }
        }

        for (Room r: eventManager.getRoomList()) {
            if (!unavailable_rooms.contains(r)){
                available_rooms += r + ", ";
            }
        }

        available_rooms = available_rooms.replaceAll(", $", "");
        return available_rooms;
    }

    /**
     * Returns a string that creating an event was a success.
     *
     * @param creationSuccess - true if an event was created
     * @param event - the event to be created
     *
     * @return a string to that confirms the event was created
     */
    public String createEventResult(boolean creationSuccess, Event event) {
        if (creationSuccess) {
            return "Event Creation Successful!" +
                    "\n" + "Name: " + e.getName() +
                    "\n" + "Time: " + e.getTime().toString() +
                    "\n" + "Speaker: " + e.getSpeaker() +
                    "\n" + "Room Number: " + e.getRoomNum().toString();
        }
        return "Event Creation Failed";

}
