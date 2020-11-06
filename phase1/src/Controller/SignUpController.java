package Controller;

import Entity.Event;
import UseCase.EventManager;

import java.util.ArrayList;

public class SignUpController {
    private EventManager eventManager;
    /**
     * Returns an arraylist of the existing events.
     * @return an ArrayList<Event> of the existing events
     */
    public ArrayList<Event> getEventList(){
        return eventManager.getEventList();
    }
}
