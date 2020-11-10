package Presenter;

import Entity.Event;
import UseCase.EventManager
import UseCase.UserManager

import java.util.ArrayList;
import java.util.Date;

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
        String availableSpeakers = "Available Speakers: ";
        String unavailableSpeakers = "Unavailable Speakers: ";
        ArrayList<String> unavailableList = new ArrayList<>();
        for (Event e : eventManager.getEventList()) {
            if (e.getTime() == time && e.getRoomNum().equals(room.getKey())) {
                unavailableSpeakers += e.getSpeaker() + ", ";
                unavailableList.add(e.getSpeaker());
            }
        }

        for (String s : userManager.getSpeakerList()) {
            if (!unavailableList.contains(s)) {
                availableSpeakers += s + ", ";
            }

            availableSpeakers = availableSpeakers.replaceAll(", $", "");
            unavailableSpeakers = unavailableSpeakers.replaceAll(", $", "");
            return availableSpeakers + "\n" + unavailableSpeakers;
        }
    }

    /**
     * Returns a string of all the available start times for an event.
     *
     * @return a string of available start times
     */
    public String displayStartTimes () {
        String availableTimes = "";
        int startTime = eventManager.getStartTime();
        while (startTime < eventManager.getEndTime()){
            availableTimes += startTime.toString() + ", ";
            startTime += 1;
        }

        availableTimes = availableTimes.replaceAll(", $", "");
        return availableTimes;

    }

    /**
     * Returns a string of all the available rooms for an event given the chosen time.
     *
     * @param time - the chosen time
     *
     * @return a string of available rooms
     */
    public String displayRoomList (Date time) {
        String availableRooms = "Available Rooms: ";
        String unavailableRooms = "Unavailable Rooms: ";
        ArrayList <Pair<Integer, Integer>> unavailableList = new ArrayList<>();
        for (Event e : eventManager.getEventList()) {
            if (e.getTime() == time){
                Pair<Integer, Integer> unavailableRoom = <e.getRoomNum(), getRoomCapacity()>;
                unavailableList.add(unavailableRoom);
                unavailableRooms += e.getRoomNum().toString();
            }
        }

        for (Room r: eventManager.getRoomList()) {
            if (!unavailableList.contains(r)){
                availableRooms += r.getRoomNum().toString() + ", ";
            }
        }

        availableRooms = availableRooms.replaceAll(", $", "");
        unavailableRooms = unavailableRooms.replaceAll(", $", "");
        return availableRooms + "\n" + unavailableRooms;
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
