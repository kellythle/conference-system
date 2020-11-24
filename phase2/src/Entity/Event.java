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
    private ArrayList<String> speaker; // an arraylist of name(s) of speaker(s)
    private LocalDateTime time; //time of the event
    private final ArrayList<String> attendees; // names of the attendees
    private final Pair<Integer, Integer> room; //<room number, capacity>
    private int duration;// the duration of this event

    /**
     * The name, ID, occurring time, occurring place, and speaker required to create an event.
     * @param name- the name of this event
     * @param speaker- the name(s) of the speaker(s)
     * @param time- the occurring time of this event
     * @param room- the occurring room number of this event
     * @param duration - the duration of this event
     */
    public Event (String name, ArrayList<String> speaker, LocalDateTime time, Pair<Integer, Integer> room, int duration) {
        this.name = name;
        this.speaker = speaker;
        this.time = time;
        this.room = room;
        this.attendees = new ArrayList<>();
        this.duration = duration;
    }

    /**
     * Return the name of this event.
     * @return the name of this Event
     */
    public String getName() {
        return name;
    }

    /**
     Return the name(s) of the speaker(s) of this event.
     * @return the name(s) of the Speaker(s) of this Event
     */
    public ArrayList<String> getSpeaker() {
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
     * Returns the duration in hours of this event
     * @return the duration of this event
     */
    public int getDuration() {
        return duration;
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
     * @param speaker- the arraylist of the name(s) of the speaker(s)
     */
    public void setSpeaker(ArrayList<String> speaker) {
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