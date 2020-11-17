package UseCase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;

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
     * Gets the HashMap of all messages.
     *
     * @return the systemMessages HashMap, with message IDs as keys and Message objects as values
     */
    public HashMap<Integer, Message> getSystemMessages() {
        return systemMessages;
    }

    /**
     * Sets the HashMap of all messages.
     *
     * @param systemMessages - Hashmap with message IDs as keys and Message objects as values
     *
     */
    public void setSystemMessages(HashMap<Integer, Message> systemMessages) {
        this.systemMessages = systemMessages;
    }

    /**
     *
     * @return ID of current user logged in
     */
    public String getSenderID(){
        return senderID;
    }

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
     * @param receiverID
     * @param messageContent
     */
    public boolean createMessage(String receiverID, String messageContent){
        Message newMessage = new Message(senderID, receiverID, messageContent);
        if (systemMessages.keySet().size() != 0) {
            while (systemMessages.containsKey(newMessage.getId())) {
                newMessage.setId(generatingRandomID());
            }
        }
        systemMessages.put(newMessage.getId(), newMessage);
        return true;
    }

    private Integer generatingRandomID(){
        double max = 100000000;
        double min = 0;
        return (Integer) (int) (Math.random() * (max - min));
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
    public String getMessageTime(int messageID){
        LocalDateTime messageTime = getMessage(messageID).getTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
        String dateTimeString = messageTime.format(formatter);
        return dateTimeString;
    }

    /**
     * Helper method, returns message history of single account
     *
     * @param userID
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

    public boolean isSender(int messageID) {
        if (senderID.equals(getMessageSender(messageID))){
            return true;
        } else {return false;}
    }

    /**
     * Returns list of String usernames of users that current user has conversations with.
     */
    public ArrayList<String> getSenderConversations(){
        ArrayList<Message> messages = getAllSenderMessages();
        ArrayList<String> conversations = new ArrayList<String>();
        for (Message i: messages){
            if (isSender(i.getId())){
                conversations.add(i.getReceiver());
            } else {conversations.add(i.getSender());}
        }
        LinkedHashSet<String> hashSet = new LinkedHashSet<>(conversations);
        ArrayList<String> conversationsWithoutDuplicates = new ArrayList<>(hashSet);
        return conversationsWithoutDuplicates;
    }

    /**
     * Return list of message IDs that comprise the message history between
     * the current user of the program and a given user.
     *
     * @param otherID
     * @return list of integer message IDs.
     */
    public ArrayList<Integer> getSingleConversationByReceiver(String otherID){
        ArrayList<Message> conversation = getAllSenderMessages();
        Collections.sort(conversation);
        ArrayList<Integer> singleConversation = new ArrayList<>();
        for (Message i: conversation){
            if (otherID.equals(i.getReceiver()) || otherID.equals(i.getSender())) {
                singleConversation.add(i.getId());
            }
        } return singleConversation;
    }
}
