import java.util.ArrayList;
import java.util.Date;

/**
 * A class that represents an event.
 * @author An Yen
 */
class Event {
    private String name; // name of event
    private int Id; // id of event
    private String speaker; // name of speaker
    private Date time; //time of the event
    private Arraylist<String> attendees; // names of the attendees
    private String room; //the room number where this event is held

    /**
     * The name, ID, occuring time, occuring place, and speaker required to create an event.
     * @param name
     * @param id
     * @param speaker
     * @param time
     * @param room
     */
    public Event (String name, int id, String speaker, Date time, String room) {
        this.name = name;
        this.id = id;
        this.speaker = speaker;
        this.time = time;
        this.room = room;
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
        return Id;
    }

    /**
     Return the speaker's name of this event.
     * @return the Speaker's name of this Event
     */
    public String getSpeaker() {
        return speaker;
    }

    /**
     * Returns the occuring time of this event.
     * @return the occuring time of this Event
     */
    public Date getTime() {
        return time;
    }

    /**
     * Returns the occuring room number of this event.
     * @return the occuring room number of this Event
     */
    public String getRoom() {
        return room;
    }

    /**
     * This method sets the attendee list of this event.
     * @param attendees
     */
    public void setAttendees(Arraylist<String> attendees) {
        this.attendees = attendees;
    }

    /**
     * This method sets the ID of this event.
     * @param id
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * This method sets the name of this event.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method sets the room number of where this event will be held.
     * @param room
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * This method assign a speaker to this event by the speaker's name.
     * @param speaker
     */
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    /**
     * This method sets the occuring time of this event.
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }
}