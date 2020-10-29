import java.util.ArrayList;
import java.util.Date;

/**
 * A class that represents an event.
 * @author An Yen
 */
class Event {
    private static int nextId = 0; // next available id
    private int id; // id of event
    private String name; // name of event
    private String speaker; // name of speaker
    private Date time; //time of the event
    private ArrayList<String> attendees; // names of the attendees
    private String room; //the room number where this event is held

    /**
     * The name, ID, occurring time, occurring place, and speaker required to create an event.
     * @param name- the name of this event
     * @param speaker- the speaker's name
     * @param time- the occurring time of this event
     * @param room- the occurring room number of this event
     */
    public Event (String name, String speaker, Date time, String room) {
        this.name = name;
        this.speaker = speaker;
        this.time = time;
        this.room = room;
        this.attendees = new ArrayList<>();
        this.id = nextId;
        nextId++;
    }

    /**
     * Return the name of this event.
     * @return the name of this Event
     */
    public String getName() {
        return name;
    }

    /**
     Return the ID of this event.
     * @return the id of this Event.
     */
    public int getId() {
        return id;
    }

    /**
     Return the speaker's name of this event.
     * @return the Speaker's name of this Event
     */
    public String getSpeaker() {
        return speaker;
    }

    /**
     * Returns the occurring time of this event.
     * @return the occurring time of this Event
     */
    public Date getTime() {
        return time;
    }

    /**
     * Returns the occurring room number of this event.
     * @return the occurring room number of this Event
     */
    public String getRoom() {
        return room;
    }

    /**
     * This method sets the attendee list of this event.
     * @param attendeeName- the attendee's name
     */
    public void addAttendee(String attendeeName) {
        this.attendees.add(attendeeName);
    }

    /**
     * Return the list of attendees
     * @return the list of attendees
     */
    public ArrayList<String> getAttendees() {return attendees;}

    /**
     * This method sets the name of this event.
     * @param name- the name of this event
     */
    public void setName(String name) {this.name = name; }

    /**
     * This method sets the room number of where this event will be held.
     * @param room- the room number
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * This method assign a speaker to this event by the speaker's name.
     * @param speaker- the name of the speaker
     */
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    /**
     * This method sets the occuring time of this event.
     * @param time- the time of this event
     */
    public void setTime(Date time) {
        this.time = time;
    }
}