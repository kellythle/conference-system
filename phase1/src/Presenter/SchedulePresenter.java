package Presenter;

import Entity.Event;
import UseCase.EventManager;
import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A presenter class that prints values that will be displayed in the UI
 * when an Organizer is scheduling events.
 *
 * @author Kelly Le
 */

public class SchedulePresenter {

    private EventManager eventManager;

    /**
     * Creates an instance of SchedulePresenter with parameter EventManager.
     * @param em
     */
    public SchedulePresenter(EventManager em){
        this.eventManager = em;
    }
    /**
     * Prints a menu for scheduling Events.
     */
    public void printScheduleMenu(){
        System.out.println("Welcome to the Scheduling Menu! \n " +
                "Options:\n" +
                "1. Schedule an Event\n" +
                "2. Create a Speaker\n" +
                "3. Exit this Menu\n" +
                "Please enter 1, 2, or 3 to choose your option: ");
    }

    /**
     * Prints a message if the user entered an invalid menu option.
     */
    public void printFailScheduleMenu(){
        System.out.println("That is not an option in our menu.");
    }

    /**
     * Return a list of Speakers available at the given time.
     *
     * @param speakerList - the list of existing speakers
     * @param time - the chosen time
     *
     *
     * @return list of available Speakers
     */
    public ArrayList<String> availableSpeakers(ArrayList<String> speakerList, LocalDateTime time) {
        ArrayList<String> availableList = new ArrayList<>();
        ArrayList<String> unavailableList = new ArrayList<>();
        if (eventManager.getEventList() != null) {
            for (Event e : eventManager.getEventList()) {
                if (e.getTime() == time) {
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
     * Prints a string of speakers that are available and unavailable
     * given the room and time chosen by the Organizer.
     *
     * @param speakerList - the list of existing speakers
     * @param time - the chosen time
     *
     */
    public void displaySpeakerList(ArrayList<String> speakerList, LocalDateTime time) {
        StringBuilder availableSpeakers = new StringBuilder("Available Speakers: ");
        StringBuilder unavailableSpeakers = new StringBuilder("Unavailable Speakers: ");

        for (String s : speakerList) {
            if (this.availableSpeakers(speakerList, time).contains(s)) {
                availableSpeakers.append(s).append(", ");
            } else {
                unavailableSpeakers.append(s).append(", ");
            }
        }
            availableSpeakers = new StringBuilder(availableSpeakers.toString().replaceAll(", $", ""));
            unavailableSpeakers = new StringBuilder(unavailableSpeakers.toString().replaceAll(", $", ""));
            System.out.println("Here are the available and unavailable speakers:\n" +
                    availableSpeakers + "\n" + unavailableSpeakers + "\n"
                    + "Please enter the Speaker you wish to book for this Event or 0 to exit: ");
    }

    /**
     * Prints a message if the user entered an invalid speaker.
     */
    public void printFailSpeaker(){
        System.out.println("This Speaker does not exist in our system or is unavailable.");
    }

    /**
     * Prints a string of all the available start times for an event.
     */
    public void displayStartTimes () {
        String availableTimes = "";
        int startTime = eventManager.getStartTime();
        while (startTime < eventManager.getEndTime()){
            if (startTime < 10){
                availableTimes += "0" + startTime + ", ";
            } else {
                availableTimes += startTime + ", ";
            }
            startTime += 1;
        }
        availableTimes = availableTimes.replaceAll(", $", "");
        System.out.println("Here are the available start times:\n" + availableTimes +"\n");

    }

    /**
     * Prints a message if the user entered an invalid start time.
     */
    public void printFailStartTimes(){
        System.out.println("That time is not an option or is unavailable.");
    }

    /**
     * Returns a list of available rooms.
     *
     * @param time - time for Event
     * @return list of available rooms
     */
    public ArrayList<Integer> availableRooms (LocalDateTime time) {
        ArrayList<Integer> availableRooms = new ArrayList<>();
        ArrayList<Integer> unavailableRooms = new ArrayList<>();
        if (eventManager.getEventList() != null) {
            for (Event e : eventManager.getEventList()) {
                if (e.getTime() == time) {
                    Pair<Integer, Integer> unavailableRoom = new Pair(e.getRoomNum(), e.getRoomCapacity());
                    unavailableRooms.add(unavailableRoom.getKey());
                }
            }
        }

        for (Pair r : eventManager.getRoomList()) {
            if (!unavailableRooms.contains(r.getKey())) {
                availableRooms.add((Integer) r.getKey());
            }
        }
        return availableRooms;
    }

    public void printEnterDate(){
        System.out.println("Enter a date in format yyyy-mm-dd (ex. 2020-05-21): ");
    }

    public void printEnterTime(){
        System.out.println("Enter the starting hour of this event (ex. 9 am is 09, 4 pm is 16): ");
    }

    public void printInvalidDateFormat(){
        System.out.println("Invalid date format");
    }

    public void printInvalidDate(){
        System.out.println("Invalid Date.");
    }
    /**
     * Prints a string of all the available rooms for an event given the chosen time.
     *
     * @param time - the chosen time
     */
    public void displayRoomList (LocalDateTime time) {
        StringBuilder availableRooms = new StringBuilder("Available Rooms: ");
        StringBuilder unavailableRooms = new StringBuilder("Unavailable Rooms: ");
        ArrayList <Pair<Integer, Integer>> unavailableList = new ArrayList<>();
        if (eventManager.getEventList() != null) {
            for (Event e : eventManager.getEventList()) {
                if (e.getTime() == time) {
                    Pair<Integer, Integer> unavailableRoom = new Pair(e.getRoomNum(), e.getRoomCapacity());
                    unavailableList.add(unavailableRoom);
                    unavailableRooms.append(e.getRoomNum().toString());
                }
            }
        }

        for (Pair r: eventManager.getRoomList()) {
            if (!unavailableList.contains(r)){
                availableRooms.append(r.getKey().toString()).append(", ");
            }
        }

        availableRooms = new StringBuilder(availableRooms.toString().replaceAll(", $", ""));
        unavailableRooms = new StringBuilder(unavailableRooms.toString().replaceAll(", $", ""));
        System.out.println("Here are the available and unavailable rooms:\n" + availableRooms + "\n" +
                unavailableRooms + "\nPlease enter the room you wish to book for this Event: ");
    }

    /**
     * Prints a message if the user entered an invalid room.
     */
    public void printFailRoom(){
        System.out.println("That room does not exist or is unavailable.");
    }

    /**
     * Returns a string that creating an event was a success.
     *
     * @param creationSuccess - true if an event was created
     * @param event - the event to be created
     *
     */
    public void createEventResult(boolean creationSuccess, Event event) {
        if (creationSuccess) {
            System.out.println("Event Creation Successful!" +
                    "\n" + "Name: " + event.getName() +
                    "\n" + "Time: " + event.getTime().toString() +
                    "\n" + "Speaker: " + event.getSpeaker() +
                    "\n" + "Room Number: " + event.getRoomNum().toString());
        } else{
            System.out.println("Event Creation Failed");
        }
    }

    /**
     * Prints asking for a Speaker username.
     */
    public void addSpeaker(){
        System.out.println("Please enter the username of who you would like to make a Speaker: ");
    }

    /**
     * Prints asking for a Speaker username.
     */
    public void addSpeakerPassword(){
        System.out.println("Please enter the password you want this Speaker to have: ");
    }

    /**
     * Prints that a Speaker was created.
     */
    public void successSpeaker(){
        System.out.println("Speaker was successfully created.");
    }

    /**
     * Prints that a Speaker was not created.
     */
    public void failedSpeaker(){
        System.out.println("Speaker could not be created.");
    }

    /**
     * Prints asking for an event name.
     */
    public void printName(){
        System.out.println("What would you like to name the Event? Enter the desired Event name or 0 to exit from " +
                "creating an event: ");
    }

    /**
     * Prints out the scheduling system has been exited.
     */
    public void printEndScheduling(){
        System.out.println("You have exited the Scheduling System.");
    }
}
