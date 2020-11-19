package UseCase;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

public class MessageManager implements Serializable {

    private HashMap<UUID, Message> systemMessages = new HashMap<>();
    private transient String senderID;

    /**
     * Sets sender ID.
     * @param senderID - message sender's ID
     */
    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    /**
     * Creates a message. Other methods call this method
     * to 'send' a message. This does not check whether or not
     * the recipientID is valid.
     * @param receiverID - Username of the message receiver.
     * @param messageContent - The message text.
     */
    public boolean createMessage(String receiverID, String messageContent){
        Message newMessage = new Message(senderID, receiverID, messageContent);
        systemMessages.put(newMessage.getId(), newMessage);
        return true;
    }

    /**
     * @param messageID - ID of the message.
     * @return the message object that corresponds to the messageID
     */
    private Message getMessage(UUID messageID){
        return systemMessages.get(messageID);
    }

    /**
     * @param messageID - ID of the message.
     * @return sender of message
     */
    public String getMessageSender(UUID messageID){
        return getMessage(messageID).getSender();
    }

    /**
     * @param messageID - ID of the message.
     * @return receiver of message
     */
    public String getMessageReceiver(UUID messageID){
        return getMessage(messageID).getReceiver();
    }

    /**
     * @param messageID - ID of the message.
     * @return content of message
     */
    public String getMessageContent(UUID messageID){
        return getMessage(messageID).getContent();
    }

    /**
     * @param messageID - ID of the message.
     * @return time of message
     */
    public String getMessageTime(UUID messageID){
        LocalDateTime messageTime = getMessage(messageID).getTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
        return messageTime.format(formatter);
    }

    /**
     * Helper method, returns message history of single account
     *
     * @param userID - Username of the user.
     * @return Arraylist containing all messageIDs in account's message history
     *          as both sender and receiver
     */
    private ArrayList<Message> getMessages(String userID) {
        ArrayList<Message> messageHistory = new ArrayList<>();
        for (Message m : systemMessages.values()) {
            if (m.getSender().equals(userID) || m.getReceiver().equals(userID)){
                messageHistory.add(m);}
        }
        return messageHistory;
    }

    /**
     * Get message history of current user
     *
     * @return ArrayList containing all messageIDs of all sent or received
     * messages of current user
     */
    private ArrayList<Message> getAllSenderMessages(){
        return getMessages(senderID);
    }

    /**
     * Checks if current user is sender of message
     *
     * @return true if sender is sender of the message
     */

    public boolean isSender(UUID messageID) {
        return senderID.equals(getMessageSender(messageID));
    }

    /**
     * Returns list of String usernames of users that current user has conversations with.
     */
    public ArrayList<String> getSenderConversations(){
        ArrayList<Message> messages = getAllSenderMessages();
        ArrayList<String> conversations = new ArrayList<>();
        for (Message i: messages){
            if (isSender(i.getId())){
                conversations.add(i.getReceiver());
            } else {conversations.add(i.getSender());}
        }
        LinkedHashSet<String> hashSet = new LinkedHashSet<>(conversations);
        return new ArrayList<>(hashSet);
    }

    /**
     * Return list of message IDs that comprise the message history between
     * the current user of the program and a given user.
     *
     * @param otherID - Username of the other user.
     * @return list of integer message IDs.
     */
    public ArrayList<UUID> getSingleConversationByReceiver(String otherID){
        ArrayList<Message> conversation = getAllSenderMessages();
        Collections.sort(conversation);
        ArrayList<UUID> singleConversation = new ArrayList<>();
        for (Message i: conversation){
            if (otherID.equals(i.getReceiver()) || otherID.equals(i.getSender())) {
                singleConversation.add(i.getId());
            }
        } return singleConversation;
    }
}
