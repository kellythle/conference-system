package Controller;

import Entity.Event;
import UseCase.EventManager;
import UseCase.UserManager;

import java.util.ArrayList;

/**
 * A controller class that calls EventManager and UserManager
 * to manage any command related to signing up events.
 *
 * @author An Yen, Kelly Le, Filip Jovanovic
 */
public class SignUpController {
    private EventManager eventManager = new EventManager();
    private UserManager userManager;
    //remove comment after Richard done with UserManager Factory
    //private UserManager userManager = new UserManager();

    /**
     * Returns an arraylist of the existing events.
     *
     * @return an ArrayList<Event> of the existing events
     */
    public ArrayList<Event> getEventList(){
        return eventManager.getEventList();
    }

    /**
     * Returns an arraylist of the event names this user has signed up for.
     *
     * @param username - the name of the user
     *
     * @return an ArrayList<Event> of the events this user has signed up for.
     */
    public ArrayList<String> getRegisteredEventList(String username){
        return userManager.getRegisteredEvents(username);
    }

    /**
     * Returns true if the user signs up for this event.
     *
     * @param username- the name of the user
     * @param event- the event the user want to sign up
     * @return true if the user sign up for the event successfully, else false
     */
    public boolean signUpEvent(String username, Event event){
        if (eventManager.addUserToEvent(username, event)){
            userManager.addRegisteredEvent(username, event.getName());
            return true;
        }
        return false;
    }

    /**
     * Returns true is the user successfully cancels their enrollment for this event.
     *
     * @param username- the name of the user
     * @param eventName- the event's name the user want to sign up
     * @return true if the user cancels their enrollment for this event, else false
     */
    public boolean deleteEvent(String username, String eventName){
        if (eventManager.deleteUserFromEvent(username, eventName)){
            userManager.getRegisteredEvents(username).remove(eventName);
            return true;
        }
        return false;
    }
}
