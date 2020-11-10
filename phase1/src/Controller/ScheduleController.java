package Controller;

import UseCase.EventManager;
import UseCase.UserManager;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Date;

/**
 * A controller class that calls EventManager to manage any
 * command related to adding events.
 *
 * @author An Yen, Kelly Le
 */
public class ScheduleController {
    private EventManager eventManager = new EventManager();
    private UserManager userManager;
    //remove comment after Richard done with UserManager Factory
    //private UserManager userManager = new UserManager();

    /**
     * Returns a list of available speaker usernames.
     *
     * @return an arraylist of speaker usernames
     */
    public ArrayList<String> getSpeakerList(){
        return userManager.getSpeakerList();
    }

    /**
     * Checks if this username already exists. Returns true if
     * this username is not an existed username of this conference.
     *
     * @param speakerName - name of the speaker
     * @return true if this speaker name is not an existed username of this conference
     */
    private boolean canCreateSpeaker(String speakerName){
        return !userManager.userMap.containsKey(speakerName);
    }

    /**
     * Returns true if the new speaker account is added to this conference.
     *
     * @param speakerName - name of the speaker
     * @param password - password set by the organizer who creates this speaker account
     * @return true if this speaker account is added to this conference
     */
    public boolean addNewSpeaker(String speakerName, String password) {
        if(canCreateSpeaker(speakerName)) {
            userManager.createUser(speakerName, password, "Speaker");
            return true;
        }
        return false;
    }

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
        // create a speaker account if this speaker haven't have an account yet.
        if(!userManager.isSpeaker(speaker)){
            return false;
        }
        return eventManager.addEvent(name, speaker, time, room);
    }

    /**
     * Returns a pair of the legal start time and end time that an event can take place.
     *
     * @return a Pair<startTime, endTime>
     */
    public Pair<Integer, Integer> getLegalTime(){
        return new Pair<>(eventManager.getStartTime(), eventManager.getEndTime());
    }
}

