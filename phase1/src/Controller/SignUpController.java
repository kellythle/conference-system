package Controller;

import Entity.Event;
import UseCase.EventManager;
import UseCase.UserManagaer;

import java.util.ArrayList;

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

    public boolean SignUpEvent(String username, Event event){
        if(eventManager.addUserToEvent(username, event)){
            //Remove the comment notation after merging Storage and UserManager
            //userManager.addRegisteredEvent(username, event.getName());
            return true;
        }
        return false;
    }
}
