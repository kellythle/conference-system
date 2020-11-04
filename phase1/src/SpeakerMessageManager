import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class that stores, manages, creates messages
 * MessageManager stores a map of message IDs to messages
 * stores ID of user sending messages
 * Getters for message sender, receiver, time, and content from messageID
 * Getter for message history of current user
 * Send messages or reply to messages
 *
 * @author Yu Jin Kim
 */

public class SpeakerMessageManager extends MessageManager{

    /**
     * Creates an OrganizerMessageManager
     * @param senderID - ID of the current user logged in
     */

    public SpeakerMessageManager(String senderID){
        super(senderID);
    }


    /**
     * Returns True if Speaker sent message to all Attendees of the Event
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
     * Returns True if Speaker sent message to all Attendees of all of its Event
     *
     * @param speaker
     * @param messageContent
     * @return boolean
     */
    //public boolean sendAllMessageAllEvent(String speakerId, String messageContent, StorageManager storage){
        //for (attendee : event.attendees) {
            //if (sendSingleMessage(receiverID, messageContent) == false) {
               // return false;
        //    }
        //return true;
    //}


}