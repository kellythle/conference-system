package UseCase;

import Entity.Conference;
import Entity.Event;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Date;

/**
 * A class that stores and creates events.
 * Store a list of events: an ArrayList of variable that stores the existing events
 * Create events: calls the constructor of event
 * Check time conflicts
 *
 * @author Kelly Le
 */

public class ScheduleManager {

    private ArrayList<Event> eventList;

    /**
     * Returns the list of events.
     *
     * @return event list
     */
    public ArrayList<Event> getEventList() {
        return eventList;
    }

    /**
     * Adds the event to the evnt list.
     *
     * @param event - the event object
     */

    private void addEvent(Event event) {
        this.eventList.add(event);
    }

    /**
     * Check if the starting of the event wanted to be created is in the legal starting and ending time of
     * the conference.
     * @param conference - the conference the event takes place in
     * @param time - the occurring time of this event
     * @return true if the event can be created and false if cannot
     */
    private boolean canCreateEvent(Conference conference, Date time){
        if(time.getHours() >= conference.getStartTime() && time.getHours() < conference.getEndTime()){
            return true;
        }
        return false;
    }

    /**
     * Returns an event that is created.
     *
     * @param name - the name of this event
     * @param speaker - the speaker's name
     * @param time - the occurring time of this event
     * @param room - the occurring room of this event
     *
     * @return the Event that is created
     */
    private Event createEvent(String name, String speaker,
                               Date time, Pair<Integer, Integer> room) {
        return new Event(name, speaker, time, room);
    }

    /**
     * Returns true if an event is created and added to the conference successfully.
     * Returns false if the event cannot be created or added.
     * @param conference - the conference the event takes place in
     * @param name - the name of this event
     * @param speaker - the speaker's name
     * @param time - the occurring time of this event
     * @param room - the occurring room of this event
     * @return true if successfully creates and adds the new event to the conference and false otherwise
     */
    public boolean canAddEvent(Conference conference, String name, String speaker,
                               Date time, Pair<Integer, Integer> room) {
        if (!canCreateEvent(conference, time)){
            return false;
        }

        for (Event e : eventList) {
            if ((e.getTime() == time && e.getRoomNum().equals(room.getKey())) || (e.getTime() == time
                    && e.getSpeaker().equals(speaker))) {
                return false;
            }
        }
        Event newEvent = createEvent(name, speaker, time, room);
        this.addEvent(newEvent);
        return true;
    }
}