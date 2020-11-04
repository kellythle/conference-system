package UseCase;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import Entity.Message;

/**
 * A class that stores, manages, creates messages
 * UseCase.MessageManager stores a map of message IDs to messages
 * stores ID of user sending messages
 * Getters for message sender, receiver, time, and content from messageID
 * Getter for message history of current user
 * Send messages or reply to messages
 *
 * @author Wenying Wu
 */

public class MessageManager {

    private HashMap<Integer, Message> systemMessages;
    private String senderID;

    /**
     * Creates a UseCase.MessageManager
     * @param senderID - ID of the current user logged in
     */

    public MessageManager(String senderID){
        HashMap<Integer, Message> systemMessages = new HashMap<Integer, Message>();
        this.senderID = senderID;
    }

    /**
     *
     * @return ID of current user logged in
     */
    public String getSenderID(){
        return senderID;
    }
    /**
     * Creates a message. Other methods call this method
     * to 'send' a message. This does not check whether or not
     * the recipientID is valid.
     * @param receiverID
     * @param messageContent
     */

    private boolean createMessage(String receiverID, String messageContent){
        Message newMessage = new Message(senderID, receiverID, messageContent);
        return true;
    }

    /**
     * @param messageID
     * @return the message object that corresponds to the messageID
     */
    private Message getMessage(int messageID){
        return systemMessages.get(messageID);
    }

    /**
     * @param messageID
     * @return sender of message
     */
    public String getMessageSender(int messageID){
        return getMessage(messageID).getSender();
    }

    /**
     * @param messageID
     * @return receiver of message
     */
    public String getMessageReceiver(int messageID){
        return getMessage(messageID).getReceiver();
    }

    /**
     * @param messageID
     * @return content of message
     */
    public String getMessageContent(int messageID){
        return getMessage(messageID).getContent();
    }

    /**
     * @param messageID
     * @return time of message
     */
    public LocalTime getMessageTime(int messageID){
        return getMessage(messageID).getTime();
    }

    /**
     * Returns True if user is allowed to send a message to
     * the user with the receiverID
     *
     * Cannot implement until UseCase.StorageManager implemented
     *
     * @param receiverID
     * @return true if the receiver is in the sender's friendlist
     */
    public boolean canSend(String receiverID) {return true;}

    /**
     *
     * @param receiverID
     * @param messageContent
     * @return true if message created
     */
    public boolean sendSingleMessage(String receiverID, String messageContent){
        if (canSend(receiverID)){
            return createMessage(receiverID, messageContent);
        } else {return false;}
    }

    /**
     * Replies to message.
     *
     * @param messageID
     * @param messageContent
     * @return true if message created
     */
    public boolean replyToMessage(int messageID, String messageContent){
        if (getMessageReceiver(messageID) == senderID) {
            return createMessage(getMessageSender(messageID), messageContent);
        } else { return false; }
    }

    /**
     * Adds contact's ID to friendlist
     *
     * Cannot implement until UseCase.StorageManager implemented
     *
     * @param friendID
     * @return true if friend added to user friendlist
     */
    public boolean addToFriendList(String friendID) {return false;}

    /**
     * Helper method, returns message history of single account
     *
     * @param userID
     * @return Arraylist containing all messageIDs in account's message history
     *          as both sender and receiver
     */
    private ArrayList<Integer> getMessages(String userID) {
        ArrayList<Integer> messageHistory = new ArrayList<Integer>();
        for (Message m : systemMessages.values()) {
            if (m.getSender() == userID || m.getReceiver() == userID){
                messageHistory.add(m.getId());}
        }
        return messageHistory;
    }

    /**
     * Get message history of current user
     *
     * @return ArrayList containing all messageIDs of all sent or received
     * messages of current user
     */
    public ArrayList<Integer> getAllSenderMessages(){
        return getMessages(senderID);
    }
}
