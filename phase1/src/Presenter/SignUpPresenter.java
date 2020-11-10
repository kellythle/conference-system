package Presenter;

import Entity.Event;
import UseCase.EventManager;

import java.util.ArrayList;

/**
 * A presenter class that contain methods that contains methods
 * that returns values which should be displayed in the UI.
 *
 * @author An Yen
 */
public class SignUpPresenter {

    private EventManager eventManager = new EventManager();

    /**
     * Returns a string with all events with its detail in the format:
     * Name, Time, Speaker, Room Number, Full/Available for each event.
     *
     * @param eventList - the list of existing events
     * @return a string of event details
     */
    public String displayEventList(ArrayList<Event> eventList){
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
                    ", Room Number: " + e.getRoomNum().toString() +
                    available + "\n";
        }
        return events;
    }

    /**
     * Returns a string with all events an user registered with its detail in the format:
     * Name, Time, Speaker, Room Number, Full/Available for each event.
     *
     * @param registeredEventNames - a list of event names an user registered
     * @return a string of registered event details
     */
    public String displayRegisteredEvents(ArrayList<String> registeredEventNames) {
        ArrayList<Event> registeredEvents = new ArrayList<>();
        for (Event e: eventManager.getEventList()) {
            if (registeredEventNames.contains(e.getName())){
                registeredEvents.add(e);
            }
        }
        return displayEventList(registeredEvents);
    }

    /**
     * Returns a message to show whether an user signed up for an event successfully.
     *
     * @param signUpSuccess - a boolean value, true if sign up succeed
     * @return a string to show whether the user signed up for an event successfully
     */
    public String signUpResult(boolean signUpSuccess){
        if (signUpSuccess) {
            return "Sign up success!";
        }
        return "You cannot register this event. Sign up failed...";

    }

    /**
     * Returns a message to show whether an user dropped an event successfully.
     * @param deleteSuccess - a boolean value, true if deletion succeed.
     *                      (Dropped an event successfully)
     * @return a string to show whether the user dropped an event successfully
     */
    public String deleteEventResult(boolean deleteSuccess) {
        if (deleteSuccess) {
            return "Deletion success!";
        }
        return "Deletion fail";

    }
}
