package Presenter;

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

    private final EventManager eventManager;

    /**
     * Creates an instance of SchedulePresenter with parameter EventManager.
     * @param em - an EventManager instance
     */
    public SchedulePresenter(EventManager em){
        this.eventManager = em;
    }
    /**
     * Prints a menu for scheduling Events.
     */
    public void printScheduleMenu(){
        System.out.println("Welcome to the Scheduling Menu!\n" +
                "Options:\n" +
                "1. Schedule an Event\n" +
                "2. Create a Speaker\n" +
                "3. Exit Scheduling Menu\n" +
                "Enter 1, 2, or 3: ");
    }

    /**
     * Prints "Input invalid, please try again."
     */
    public void printFailScheduleMenu(){
        System.out.println("Input invalid, please try again.");
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
            if (eventManager.getAvailableSpeakers(speakerList, time).contains(s)) {
                availableSpeakers.append(s).append(", ");
            } else {
                unavailableSpeakers.append(s).append(", ");
            }
        }
        availableSpeakers = new StringBuilder(availableSpeakers.toString().replaceAll(", $", ""));
        unavailableSpeakers = new StringBuilder(unavailableSpeakers.toString().replaceAll(", $", ""));
        System.out.println("Here are the available and unavailable speakers:\n" +
                availableSpeakers + "\n" + unavailableSpeakers + "\n"
                + "Please enter the Speaker you wish to book for this Event or 0 to return to the Scheduling Menu: ");
    }

    /**
     * Prints "This Speaker does not exist in our system or is unavailable."
     */
    public void printFailSpeaker(){
        System.out.println("This Speaker does not exist in our system or is unavailable.");
    }

    /**
     * Prints a string of all the available start times for an event.
     */
    public void displayStartTimes () {
        StringBuilder availableTimes = new StringBuilder();
        for (String t: eventManager.getStartTimes()){
            availableTimes.append(t).append(", ");
        }
        availableTimes = new StringBuilder(availableTimes.toString().replaceAll(", $", ""));
        System.out.println("Here are the available start times:\n" + availableTimes +"\n");
    }


    /**
     * Prints "That time is not a valid option."
     */
    public void printFailStartTimes(){
        System.out.println("That time is not a valid option.");
    }


    /**
     * Prints asking the Organizer to choose the date when creating an event.
     */
    public void printEnterDate(){
        System.out.println("Enter a date in format yyyy-mm-dd (ex. 2020-05-21): ");
    }

    /**
     * Prints asking the Organizer to choose the starting hour when creating an event.
     */
    public void printEnterTime(){
        System.out.println("Enter the starting hour of this event (ex. 9 am is 09, 4 pm is 16): ");
    }

    /**
     * Prints "Invalid date format"
     */
    public void printInvalidDateFormat(){
        System.out.println("Invalid date format.");
    }

    /**
     * Prints "Invalid Date."
     */
    public void printInvalidDate(){
        System.out.println("Invalid Date.");
    }
    /**
     * Prints a string of all the available and unavailable rooms for an event given the chosen time.
     *
     * @param time - the chosen time
     */
    public void displayRoomList (LocalDateTime time) {
        StringBuilder availableRooms = new StringBuilder("Available Rooms: ");
        StringBuilder unavailableRooms = new StringBuilder("Unavailable Rooms: ");
        for (Pair<Integer, Integer> r: eventManager.getRoomList()) {
            if (!eventManager.getAvailableRooms(time).contains(r.getKey())) {
                unavailableRooms.append(r.getKey().toString()).append(", ");
            } else {
                availableRooms.append(r.getKey().toString()).append(", ");
            }
        }

        availableRooms = new StringBuilder(availableRooms.toString().replaceAll(", $", ""));
        unavailableRooms = new StringBuilder(unavailableRooms.toString().replaceAll(", $", ""));
        System.out.println("Here are the available and unavailable rooms:\n" + availableRooms + "\n" +
                unavailableRooms + "\nPlease enter the room you wish to book for this Event: ");
    }

    /**
     * Prints "That room does not exist or is unavailable."
     */
    public void printFailRoom(){
        System.out.println("That room does not exist or is unavailable.");
    }

    /**
     * Prints whether or not creating an event was a success.
     *
     * @param creationSuccess - true if an event was created
     * @param name - name of the event
     * @param speaker - speaker of the event
     * @param time - time of the event
     * @param room - room the event takes place in
     */
    public void createEventResult(boolean creationSuccess, String name, String speaker, LocalDateTime time,
                                  Pair<Integer, Integer> room) {
        if (creationSuccess) {
            System.out.println("Event Creation Successful!" +
                    "\n" + "Name: " + name +
                    "\n" + "Time: " + time.toString() +
                    "\n" + "Speaker: " + speaker +
                    "\n" + "Room Number: " + room.getKey().toString());
        } else{
            System.out.println("Event Creation Failed.");
        }
    }

    /**
     * Prints asking the Organizer for a Speaker username
     */
    public void addSpeaker(){
        System.out.println("Please enter the username of who you would like to make a Speaker or 0 to return to " +
                "the Scheduling Menu: ");
    }

    /**
     * Prints asking the Organizer for a Speaker password.
     */
    public void addSpeakerPassword(){
        System.out.println("Please enter the password you want this Speaker to have: ");
    }

    /**
     * Prints "Speaker successfully created!"
     */
    public void successSpeaker(){
        System.out.println("Speaker successfully created!");
    }

    /**
     * Prints "Speaker creation failed."
     */
    public void failedSpeaker(){
        System.out.println("Speaker creation failed.");
    }

    /**
     * Prints "You cannot make a Speaker account with an empty username or you have
     * entered a username that already exists in the system."
     */
    public void failedUsername(){
        System.out.println("You cannot make a Speaker account with an empty username or you have " +
                "entered a username that already exists in the system.");
    }

    /**
     * Prints "You cannot make a Speaker account with an empty password."
     */
    public void failedPassword(){
        System.out.println("You cannot make a Speaker account with an empty password.");
    }

    /**
     * Prints asking the Organizer for an event name.
     */
    public void printName(){
        System.out.println("What would you like to name the Event? Enter the desired Event name or 0 to return to " +
                "the Scheduling Menu: ");
    }

    /**
     * Prints "You have entered no name for the Event or an Event name that already exists.".
     */
    public void printFailedName(){
        System.out.println("You have entered no name for the Event or an Event name that already exists.");
    }

    /**
     * Prints "You have exited the Scheduling System."
     */
    public void printEndScheduling(){
        System.out.println("You have exited the Scheduling System.");
    }

}
