import java.util.ArrayList;
/**
 * A class that represents a conference.
 * @author An Yen
 */
class Conference {
    private Arraylist<Stirng> attendees;
    private Arraylist<Integer> events;

    /**
     * Creates a Conference with an attendee list and an event list.
     * @param attendees
     * @param events
     */
    public Conference(ArrayList<String> attendees, ArrayList<Integer> events){
        this.attendees = attendees;
        this.events = events;
    }

    /**
     * Returns the event list of this conference.
     * @return the event list of thi Conference
     */
    public Arraylist<Integer> getEvents() {
        return events;
    }

    /**
     * Returns the attendee list of this conference.
     * @return the attendee list of this Conference
     */
    public Arraylist<Stirng> getAttendees() {
        return attendees;
    }

    /**
     * Add a new attendee to the attendee list of this conference.
     * @param attendeeName
     */
    public void setAttendees(String attendeeName){
        attendees.add(attendeeName);
    }

    /**
     * Add a new event to the event list of this conference.
     * @param eventId
     */
    public void setEvents (int eventId){
        events.add(eventId);
    }

}