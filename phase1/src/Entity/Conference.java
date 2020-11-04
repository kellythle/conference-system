package Entity;

import java.util.ArrayList;
/**
 * A class that represents a conference.
 * @author An Yen
 */
public class Conference {
    private final ArrayList<String> attendees;
    private final ArrayList<Integer> events;

    /**
     * Creates a Conference with an attendee list and an event list.
     * @param attendees-
     * @param events-
     */
    public Conference(ArrayList<String> attendees, ArrayList<Integer> events){
        this.attendees = attendees;
        this.events = events;
    }

    /**
     * Returns the event list of this conference.
     * @return the event list of thi Conference
     */
    public ArrayList<Integer> getEvents() {
        return events;
    }

    /**
     * Returns the attendee list of this conference.
     * @return the attendee list of this Conference
     */
    public ArrayList<String> getAttendees() {
        return attendees;
    }

    /**
     * Add a new attendee to the attendee list of this conference.
     * @param attendeeName-
     */
    public void setAttendees(String attendeeName){
        attendees.add(attendeeName);
    }

    /**
     * Add a new event to the event list of this conference.
     * @param eventId-
     */
    public void setEvents (int eventId){
        events.add(eventId);
    }

}