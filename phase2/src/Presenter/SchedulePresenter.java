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
                "2. Delete an Event\n" +
                "3. Change an Event's Capacity\n" +
                "4. Create a Speaker\n" +
                "5. Exit Scheduling Menu\n" +
                "Enter 1, 2, 3, 4, or 5: ");
    }

    /**
     * Prints a menu for choosing what kind of event to create.
     */
    public void printEventTypeMenu(){
        System.out.println("Options:\n" +
                "1. Schedule a normal event (1 speaker required)\n" +
                "2. Schedule a panel (2 speakers required)\n" +
                "3. Schedule a party (0 speakers required)\n" +
                "4. Back to Schedule Menu\n" +
                "Enter 1, 2, 3 or 4");
    }

    /**
     * Prints "Input invalid, please try again."
     */
    public void printFailScheduleMenu(){
        System.out.println("Input invalid, please try again.");
    }



    /**
     * Prints a string of speakers that are available and unavailable
     * given the time chosen by the Organizer.
     *
     * @param speakerList - the list of existing speakers
     * @param time - the chosen time
     * @param duration - the duration of an event
     * @param enteredSpeaker - the speaker name(s) that has been selected for
     *                       an event wanted to be create.
     */
    public void displaySpeakerList(ArrayList<String> speakerList, LocalDateTime time,
                                   int duration, ArrayList<String> enteredSpeaker) {
        StringBuilder availableSpeakers = new StringBuilder("Available Speakers: ");
        StringBuilder unavailableSpeakers = new StringBuilder("Unavailable Speakers: ");

        for (String s : speakerList) {
            if (eventManager.getAvailableSpeakers(speakerList, time, duration).contains(s)
                    && !enteredSpeaker.contains(s)) {
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
     * Prints a string of all the available start times for the conference.
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
     * Prints asking the Organizer to choose the date in a specified format when creating an event.
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
     * Prints a prompt for the Organizer to choose a capacity for their event.
     */
    public void printCapacityPrompt(String room){
        System.out.println("Enter the maximum capacity of people for this event " + "(0 < capacity <= " +
                eventManager.getRoom(Integer.parseInt(room)).getValue() +"): ");
    }

    /**
     *
     */
    public void printInvalidCapacity(){
        System.out.println("That capacity is below or equal to the current capacity, " +
                "over the room capacity, or not a correct format.");
    }

    /**
     * Prints a string of all the available and unavailable rooms for an event
     * given the chosen time and prompts the user to enter the room they wish
     * to book for their event.
     *
     * @param time - the chosen time
     * @param duration - the duration of an event
     */
    public void displayRoomList (LocalDateTime time, int duration) {
        StringBuilder availableRooms = new StringBuilder("Available Rooms: ");
        StringBuilder unavailableRooms = new StringBuilder("Unavailable Rooms: ");
        for (Pair<Integer, Integer> r: eventManager.getRoomList()) {
            if (!eventManager.getAvailableRooms(time, duration).contains(r.getKey())) {
                unavailableRooms.append(r.getKey().toString()).append(", ");
            } else {
                availableRooms.append(r.getKey().toString()).append(", ");
            }
        }

        availableRooms = new StringBuilder(availableRooms.toString().replaceAll(", $", ""));
        unavailableRooms = new StringBuilder(unavailableRooms.toString().replaceAll(", $", ""));
        System.out.println("Here are the available and unavailable rooms:\n" + availableRooms + "\n" +
                unavailableRooms + "\nPlease enter the room you wish to book for this Event or 0 to return to the " +
                "Scheduling Menu: ");
    }

    /**
     * Prints "That room does not exist or is unavailable."
     */
    public void printFailRoom(){
        System.out.println("That room does not exist or is unavailable.");
    }

    /**
     * Prints whether or not creating an event was a success. If it is a success, the
     * event's information is also printed.
     *
     * @param creationSuccess - true if an event was created
     * @param name - name of the event
     * @param speaker - speaker(s) of the event
     * @param time - time of the event
     * @param room - room the event takes place in
     * @param duration - the duration of the event
     * @param capacity - the capacity of the event
     */
    public void createEventResult(boolean creationSuccess, String name, ArrayList<String> speaker, LocalDateTime time,
                                  Pair<Integer, Integer> room, int duration, int capacity) {
        String speakerString;
        if (speaker.isEmpty()){
            speakerString = "No speakers";
        }else{
                speakerString = speaker.toString();
        }
        if (creationSuccess) {
            System.out.println("Event Creation Successful!" +
                    "\n" + "Name: " + name +
                    "\n" + "Time: " + time.toString() +
                    "\n" + "Duration: " + duration + " hours" +
                    "\n" + "Speaker(s): " + speakerString +
                    "\n" + "Room Number: " + room.getKey().toString() +
                    "\n" + "Capacity: " + capacity + " people");
        } else{
            System.out.println("Event Creation Failed.");
        }
    }

    /**
     * Prints asking the Organizer for a Speaker username.
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
     * Prints "You cannot make a Speaker account with an empty username or spaces, or you have
     * entered a username that already exists in the system."
     */
    public void failedUsername(){
        System.out.println("You cannot make a Speaker account with an empty username or spaces, or you have " +
                "entered a username that already exists in the system.");
    }

    /**
     * Prints "You cannot make a Speaker account with an empty password or with spaces."
     */
    public void failedPassword(){
        System.out.println("You cannot make a Speaker account with an empty password or " +
                "with spaces.");
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

    /**
     * Prints a prompt for the Organizer to enter the event they would like to delete.
     */
    public void printDeletePrompt() {
        System.out.println("What Event would you like to delete? Enter the desired Event " +
                "name or 0 to return to the Scheduling Menu: ");
    }

    /**
     * Prints "You have entered an Event Name that does not exist."
     */
    public void printNoEvent() {
        System.out.println("You have entered an Event Name that does not exist.");
    }

    /**
     * Prints a prompt for the Organizer to confirm their deletion of an event which contains attendees.
     */
    public void printAttendeesExist() {
        System.out.println("There are attendees that are registered for this event. Do you still wish to delete " +
                "this event?\nEnter 0 to keep the event and return to the Scheduling menu or 1 to delete the event.");
    }

    /**
     * Prints "You have successfully deleted this event."
     */
    public void printDeletionSuccess(){
        System.out.println("You have successfully deleted this event.");
    }

    /**
     * Prints message that requires user to enter the duration of the event.
     */
    public void printEnterDurationPrompt() {
        System.out.println("Enter the duration of your event in hours.\n" +
                "Enter 1, 2 or 3\n" +
                "(Each event must be at least 1 hour long and at most 3 hours long.): ");
    }

    /**
     * Prints "Invalid event duration. Please try again."
     */
    public void printInvalidDuration() {
        System.out.println("Invalid event duration. Please try again.");
    }

    /**
     * Prints "First speaker:"
     */
    public void printFirstSpeakerPrompt() {
        System.out.print("First speaker: ");
    }

    /**
     * Prints "Second speaker:"
     */
    public void printSecondSpeakerPrompt() {
        System.out.print("Second speaker: ");
    }

    /**
     * Prints a prompt for the Organizer to change the chosen event capacity.
     */
    public void printChangeCapacityEvent() {
        System.out.println("Enter the Event of which you would like to change the capacity or 0 to return to " +
                "the Scheduling Menu: ");
    }

    /**
     * Prints the capacity of the event and what the Organizer would like to change it to.
     *
     * @param event - the event that is chosen
     * @param room - the room number of the event
     */
    public void printChangeCapacity(String event, Integer room){
        System.out.println(event + "'s current Capacity is " + eventManager.getCapacityByEvent(event) + " and its " +
                "Room's Capacity is " + eventManager.getRoom(room).getValue() + ".\nEnter the new " +
                "Room Capacity (must not be greater than " + eventManager.getRoom(room).getValue() +
                ") you would like or enter 0 to return to the Scheduling Menu: ");
    }

    /**
     * Print "Your Event's Capacity is already equal to the Room's Capacity."
     */
    public void printEventEqualRoom() {
        System.out.println("Your Event's Capacity is already equal to the Room's Capacity.");
    }

    /**
     * Print "Capacity Change Success!"
     */
    public void printChangeSuccess(){
        System.out.println("Capacity Change Success!");
    }

    /**
     * Print "Your input is already this Event's current capacity."
     */
    public void printCapacityMatch(){
        System.out.println("Your input is already this Event's current capacity.");
    }
}