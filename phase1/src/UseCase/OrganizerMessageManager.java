package UseCase;

import Entity.Event;
import UseCase.MessageManager;

import java.util.ArrayList;

/**
 * A class that stores, manages, creates messages
 * UseCase.MessageManager stores a map of message IDs to messages
 * stores ID of user sending messages
 * Getters for message sender, receiver, time, and content from messageID
 * Getter for message history of current user
 * Send messages or reply to messages
 *
 * @author Yu Jin Kim
 */

public class OrganizerMessageManager extends MessageManager {

    /**
     * Creates an UseCase.OrganizerMessageManager
     * @param senderID - ID of the current user logged in
     */

    public OrganizerMessageManager(String senderID){
        super(senderID);
    }

    /**
     * Return True if Organizer sent message to all Attendees of the Event
     *
     * @param event
     * @param messageContent
     * @return boolean
     */
    public boolean sendAllMessageEvent(Event event, String messageContent){
        for (String userName : event.getAttendees()) {
            sendSingleMessage(userName, messageContent);
        }
        return true;
    }

    /**
     * Returns True if Organizer sent message to an Attendee of the Event
     *
     * @param event
     * @param messageContent
     * @param receiverID
     * @return boolean
     */
    public boolean sendSingleMessageEvent(Event event, String messageContent, String receiverID){
        if (event.getAttendees().contains(receiverID)) {
            sendSingleMessage(receiverID, messageContent);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns True if Organizer sent message to all Speakers in the system
     *
     * Cannot implement until UseCase.StorageManager implemented
     *
     * @param messageContent
     * @return boolean
     */
    public boolean sendAllMessageSpeaker(String messageContent, UserManager um){
        for (String userName: um.userMap.keySet()) {
            sendSingleMessage(userName, messageContent);
        }
        return true;
    }

    /**
     * Returns True if Organizer sent message to one speaker in the system
     *
     * Cannot implement until UseCase.StorageManager implemented
     *
     * @param event
     * @param messageContent
     * @param receiverID
     * @return boolean
     */
    public boolean sendSingleMessageSpeaker(Event event, String messageContent, String receiverID){
        if (event.getAttendees().contains(receiverID)) {
            sendSingleMessage(receiverID, messageContent);
            return true;
        }
        else {
            return false;
        }
    }
}
