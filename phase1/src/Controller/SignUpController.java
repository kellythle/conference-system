package Controller;

import Entity.Event;
import UseCase.EventManager;
import UseCase.UserManagaer;

import java.util.ArrayList;

/**
 * A controller class that calls EventManager and UserManager
 * to manage any command related to signing up events.
 *
 * @author An Yen
 */
public class SignUpController {
    private EventManager eventManager;
    private UserManagaer userManagaer;
    /**
     * Returns an arraylist of the existing events.
     * @return an ArrayList<Event> of the existing events
     */
    public ArrayList<Event> getEventList(){
        return eventManager.getEventList();
    }

    /**
     *
     * @param username- the name of the user
     * @param event- the event the user want to sign up
     * @return true if the user sign up for the event successfully, else false
     */
    public boolean SignUpEvent(String username, Event event){
        if(eventManager.addUserToEvent(username, event)){
            //Remove the comment notation after merging Storage and UserManager
            //userManager.addRegisteredEvent(username, event.getName());
            return true;
        }
        return false;
    }
}
