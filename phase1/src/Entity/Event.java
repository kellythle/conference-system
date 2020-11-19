package Entity;

import javafx.util.Pair;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A class that represents an event.
 * @author An Yen
 */
public class Event implements Serializable {
    private String name; // name of event
    private String speaker; // name of speaker
    private LocalDateTime time; //time of the event
    private final ArrayList<String> attendees; // names of the attendees
    private final Pair<Integer, Integer> room; //<room number, capacity>

    /**
     * The name, ID, occurring time, occurring place, and speaker required to create an event.
     * @param name- the name of this event
     * @param speaker- the speaker's name
     * @param time- the occurring time of this event
     * @param room- the occurring room number of this event
     */
    public Event (String name, String speaker, LocalDateTime time, Pair<Integer, Integer> room) {
        this.name = name;
        this.speaker = speaker;
        this.time = time;
        this.room = room;
        this.attendees = new ArrayList<>();
    }

    /**
     * Return the name of this event.
     * @return the name of this Event
     */
    public String getName() {
        return name;
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
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Returns the occurring room number of this event.
     * @return the occurring room number of this Event
     */
    public Integer getRoomNum() {
        return room.getKey();
    }

    /**
     * Returns the capacity of the room where this event is hold.
     * @return the occurring room's capacity of this Event
     */
    public Integer getRoomCapacity() {
        return room.getValue();
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
     * This method assign a speaker to this event by the speaker's name.
     * @param speaker- the name of the speaker
     */
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    /**
     * This method sets the occurring time of this event.
     * @param time- the time of this event
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}