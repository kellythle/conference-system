package UseCase;

import Entity.Conference;
import Entity.Event;

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
     * Returns true if event is added to the list of events.
     *
     * @param event - the event object
     * @param conference - the conference the event takes place in
     *
     * @return true if event was added to the list, false otherwise
     */
    public boolean addEvent(Event event) {
        if (canAddEvent(conference, event)) {
            eventList.add(event);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns an event that is created.
     *
     * @param conference - the conference the event takes place in
     * @param name - the name of this event
     * @param speaker - the speaker's name
     * @param time - the occurring time of this event
     * @param room - the occurring room number of this event
     *
     * @return event if event was created
     */
    public boolean createEvent(Conference conference, String name, String speaker, Date time, String room) {
        if (time.compareTo(conference.getLegalStartHour()) >= 0 && conference.getLegalEndHour().compareTo(time) == -1) {
            Event newEvent = new Event(name, speaker, time, room);
        }
        return newEvent;
    }

    /**
     * Returns if an event can be added to the list of events.
     *
     * @param conference - the conference the event takes place in
     * @param event - the event object that wants to join the list of events
     *
     * @return true if the event can be added
     */
    public boolean canAddEvent(Conference conference, Event event) {
        if (time.before(conference.getLegalStartHour()) || conference.getLegalEndHour().after(time)) {
            return false;
        }

        for (Event e : eventList) {
            if ((e.getTime() == event.getTime() && e.getRoom() == event.getRoom()) || (e.getTIme() == event.getTime()
                    && e.getSpeaker() == event.getSpeaker())) {
                return false;
            }
        }
        return true;
    }

}