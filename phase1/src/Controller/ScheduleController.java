package Controller;

import Entity.Event;
import UseCase.EventManager;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Date;

/**
 * A controller class that calls EventManager to manage any
 * command related to adding events.
 *
 * @author Ann Yen
 */
public class ScheduleController {
    private EventManager eventManager;

    /**
     * Calls the addEvent() method of EventManager and returns
     * true if a event is successfully created. Returns false otherwise.
     *
     * @param name - name of the event wanted to be created (receive from UI)
     * @param speaker - name of the speaker of the event wanted to be created (receive from UI)
     * @param time - the occurring time of the event wanted to be created (receive from UI)
     * @param room - the occurring room of the event wanted to be created (receive from UI)
     * @return true if the event is successfully created, false otherwise.
     */
    public boolean callAddEvent(String name, String speaker,
                               Date time, Pair<Integer, Integer> room){
        return eventManager.addEvent(name, speaker, time, room);
    }

    /**
     * Returns a pair of the legal start time and end time that an event can take place.
     * @return a Pair<startTime, endTime>
     */
    public Pair<Integer, Integer> getLegalTime(){
        return new Pair<>(eventManager.getStartTime(), eventManager.getEndTime());
    }

    /**
     * MOVE THIS TO SIGNUPCONTROLLER!!!
     * Returns an arraylist of the existing events.
     * @return an ArrayList<Event> of the existing events
     */
    public ArrayList<Event> getEventList(){
        return eventManager.getEventList();
    }
}

