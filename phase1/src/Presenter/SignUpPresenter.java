package Presenter;

import Entity.Event;
import UseCase.EventManager;

import java.util.ArrayList;

/**
 * A presenter class that prints information on UI that directs the user
 * and shows result to the user during all sign up event ans delete event
 * processes.
 *
 * @author An Yen
 */
public class SignUpPresenter {

    /**
     * Prints the menu of Sign Up System.
     */
    public void printMenu(){
        System.out.println("Welcome to the Sign Up Menu! \n" +
                "Options:\n" +
                "1. Sign up for an event\n" +
                "2. Delete a registered event\n" +
                "3. See all events\n" +
                "4. See registered events\n" +
                "5. Exit Sign Up Menu\n" +
                "Enter 1, 2, 3, 4, or 5: ");
    }

    /**
     * Generates a display format of events.
     *
     * @param eventList - an arraylist of events
     * @return a String object that is the display format of events.
     */
    private String EventListGenerator(ArrayList<Event> eventList){
        String events = "";
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

    /**
     * Prints all events. Each line represents one event and is in the
     * sequence: "Name, Time, Speaker, Room Number, Available/Full".
     *
     * @param eventList - an arraylist of events
     */
    public void displayEventList(ArrayList<Event> eventList){
        String events = this.EventListGenerator(eventList);
        System.out.println("All Events: ");
        System.out.println(events);
    }

    /**
     * Prints message that asks the user to enter an event name that
     * he/she wants to sign up for or 0 to exit
     */
    public void printSignUpEventPrompt(){
        System.out.println("Enter the event name you want to sign up for " +
                "(or enter 0 to return to the Sign Up menu): ");
    }

    /**
     * Prints the events whose name are in the given arraylist
     * Each line represents one event and is in the sequence:
     * "Name, Time, Speaker, Room Number, Available/Full".
     *
     * @param registeredEventNames - an arraylist of event names
     * @param em - an instance of EventManager
     */
    public void displayRegisteredEvents(ArrayList<String> registeredEventNames, EventManager em) {
        ArrayList<Event> registeredEvents = new ArrayList<>();
        for (Event e: em.getEventList()) {
            if (registeredEventNames.contains(e.getName())){
                registeredEvents.add(e);
            }
        }
        String events = this.EventListGenerator(registeredEvents);
        System.out.println("Registered Events: ");
        System.out.println(events);
    }

    /**
     * Prints message that asks the user to enter an event name that
     * he/she wants to delete or 0 to exists
     */
    public void printDeleteEventPrompt(){
        System.out.println("Enter the event name you want to delete (or enter 0 to return to the Sign Up menu): ");
    }

    /**
     * Prints "Sign up success!".
     */
    public void printSignUpSuccess(){
            System.out.println("Sign up success!");
    }

    /**
     * Prints "You cannot sign up for this event. Sign up failed...".
     */
    public void printSignUpFail(){
        System.out.println("You've already signed up for this event. Sign up failed...");
    }

    /**
     * Prints "Event successfully deleted!".
     */
    public void printDeleteEventSuccess() {
        System.out.println("Event successfully deleted!");
    }

    /**
     * Prints "Deletion failed.".
     */
    public void printDeleteEventFail(){
        System.out.println("Deletion failed.");
    }

    /**
     * Prints "You didn't sign up for this event."
     */
    public void printNotInRegisteredEvent(){
        System.out.println("You didn't sign up for this event.");
    }

    /**
     * Prints "End of Sign Up System."
     */
    public void printEndSignUpSystem(){
        System.out.println("You have exited the Sign Up Menu.");
    }

    /**
     * Prints "This event doesn't exist."
     */
    public void printInvalidEventName(){
        System.out.println("This event doesn't exist.");
    }

    /**
     * Prints "Invalid input, please try again."
     */
    public void printInvalidInput(){
        System.out.println("Invalid input, please try again.");
    }
}
