package Presenter;

import UseCase.MessageManager;
import UseCase.UserManager;
import UseCase.EventManager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * A presenter class that prints values that will be displayed in the UI
 * for Messaging System.
 *
 * @author Wenying Wu, Yu Jin Kim
 */
public class MessagePresenter {
    private final String username;

    /**
     * Create MessagePresenter with given username.
     * @param username - username of the user
     */
    public MessagePresenter(String username){
        this.username = username;
    }

    /**
     * Generate string representation of a single message from given MessageManager and
     * message ID.
     *
     * @param messageManager - current messageManager
     * @param messageID - key value of the wanted message in the hashmap
     * @return a basic String representation of this message
     */
    public String getMessageText(MessageManager messageManager, UUID messageID){
        String sender;
        String receiver;
        if (messageManager.isSender(messageID)){
            sender = "you";
            receiver = messageManager.getMessageReceiver(messageID);
        } else {
            sender = messageManager.getMessageSender(messageID);
            receiver = "you";
        }
        return sender + " sent to " + receiver + ":\n" + messageManager.getMessageContent(messageID) +
                "\n           at " + messageManager.getMessageTime(messageID);
    }

    /**
     * Generate string representation of single message that includes which message number it is out of all
     * the messages in its conversation.
     *
     *
     * @param messageManager - current messageManager
     * @param messageID - key value of the wanted message in the hashmap
     * @param messageNumber - numerical location of message in its conversation
     * @param conversationLength - size of its conversation
     * @return - a String representation of this message, contextualized by its numerical place in its
     * conversation.
     */
    public String getMessageTextWithMessageNumber(MessageManager messageManager, UUID messageID, int messageNumber,
                                                  int conversationLength){
        String messageText = getMessageText(messageManager, messageID);
        return messageText + "\n           (" + messageNumber + " out of " + conversationLength + " messages)";
    }

    /**
     * Print Message Menu for Attendees.
     */
    public void printMessageMenu(){
        System.out.println("Welcome to the Attendee Message Menu\n" +
                "Options:\n" +
                "0. Exit Message Menu\n" +
                "1. View conversations and reply to messages\n" +
                "2. Send a message:\n"+
                "Enter 0, 1, or 2: ");
    }

    /**
     * Print Message Menu for Organizers.
     */
    public void printOrganizerMessageMenu(){
        System.out.println("Welcome to the Organizer Message Menu.\n" +
                "Options:\n" +
                "0. Exit Message Menu\n" +
                "1. View conversations\n" +
                "2. Send a message to a single user\n" +
                "3. Send a message to all Speakers\n" +
                "4. Send a message to all Attendees:\n" +
                "Enter 0, 1, 2, 3, or 4: ");
    }


    /**
     * Print Message Menu for Speakers.
     */
    public void printSpeakerMessageMenu(){
        System.out.println("Welcome to the Speaker Message Menu.\n" +
                "Options:\n" +
                "0. Exit Message Menu\n" +
                "1. View conversations and reply to messages\n" +
                "2. Send a message to Attendees of your Talks:\n" +
                "Enter 0, 1, or 2: ");
    }

    /**
     * Print list of users that logged in user has messages with.
     *
     * @param messageManager - a MessageManager instance
     */
    public void viewConversations(MessageManager messageManager){
        System.out.println("You have conversations with these users: ");
        System.out.println(messageManager.getSenderConversations());
        System.out.println("Enter a username to see your message history or enter anything else to return " +
                "to the Message Menu: ");
    }

    /**
     *
     *
     * @param messageManager - a MessageManager instance
     * @param userManager - an UserManager instance
     * @param recipientID - username of the user receiving the message
     */
    public void printSingleConversation(MessageManager messageManager, UserManager userManager, String recipientID){
        String identity = userManager.getUserType(username);
        System.out.println("Your conversation with " + identity + " " + recipientID + ": ");
        ArrayList<UUID> singleConversation = messageManager.getSingleConversationByReceiver(recipientID);
        int conversationLength = singleConversation.size();
        System.out.println("(This conversation has " + conversationLength + " messages.)");
        for (int i = 0; i < conversationLength; i++){
            String messageText;
            messageText = getMessageTextWithMessageNumber(messageManager, singleConversation.get(i),
                    i+1, conversationLength);
            System.out.println(messageText);
        }
    }

    /**
     * Calls method to print single conversation between Attendee or Speaker user and a given recipient.
     * Prints options for Attendees and Speakers to continue browsing, reply to conversation, or return to
     * Message Menu.
     *
     * @param messageManager - a MessageManager instance
     * @param userManager - an UserManager instance
     * @param recipientID - username of the user receiving the message
     */
    public void viewSingleConversation(MessageManager messageManager, UserManager userManager, String recipientID) {
        printSingleConversation(messageManager, userManager, recipientID);
        System.out.println("Options:\n" +
                "0: Reply to this conversation\n" +
                "1. Continue browsing conversations\n" +
                "Enter 0, 1, or anything else to return to the Message Menu: ");
    }

    /**
     * Calls method to print single conversation between Organizer user and given recipient. Prints options
     * for Organizer to continue browsing or return to Message Menu.
     *
     * @param messageManager - a MessageManager instance
     * @param userManager - an UserManager instance
     * @param recipientID - username of the user receiving the message
     */
    public void viewOrganizerSingleConversation(MessageManager messageManager, UserManager userManager, String
            recipientID) {
        printSingleConversation(messageManager, userManager, recipientID);
        System.out.println("Options:\n" +
                "0: Continue browsing conversations\n" +
                "Delete a message\n" +
                "Enter 0 or anything else to return to the Message Menu: ");
    }

    /**
     * Calls method to print single conversation between Speaker user and given recipient. Prints options
     * for Organizer to continue browsing or return to Message Menu.
     *
     * @param messageManager - a MessageManager instance
     * @param userManager - an UserManager instance
     * @param recipientID - username of the user receiving the message
     */
    public void viewSpeakerSingleConversation(MessageManager messageManager, UserManager userManager, String
            recipientID) {
        printSingleConversation(messageManager, userManager, recipientID);
        System.out.println("Options:\n" +
                "0: Return to previous menu\n" +
                "1: Reply to this conversation\n" +
                "2. Continue browsing conversations\n" +
                "Enter 0, 1, or anything else to return to the Message Menu: ");
    }

    /**
     * Prints prompt for ID of message recipient.
     */
    public void printReceiverIDPrompt(){
        System.out.println("Enter the username of the user you wish to message: ");
    }

    /**
     * Prints prompt for ID of Event.
     */
    public void printEventIDPrompt(){
        System.out.println("Enter the name of the event whose attendees you wish to message: ");
    }

    /**
     * Prints options for a Speaker to send messages.
     */
    public void printSpeakerMessagePrompt(){
        System.out.println("Options:\n" +
                "0. Return to previous menu\n" +
                "1. Choose a Talk\n" +
                "2. Send to all of your Talks\n" +
                "Enter 0, 1, or 2: ");
    }

    /**
     * Print prompt for content of message.
     */
    public void printContentPrompt(){
        System.out.println("Enter the message text: ");
    }

    /**
     * Print notification of message success.
     */
    public void printMessageSuccess(){
        System.out.println("Message successfully sent!");
    }

    /**
     * Print notification of message success.
     */
    public void printMessageSuccessSpeaker(){
        System.out.println("Message successfully sent excluding events without attendees.");
    }


    /**
     * Print notification of message failure.
     */
    public void printMessageFailed(){
        System.out.println("Message failed.");
    }

    /**
     * Print notification of invalid input.
     */
    public void printInvalidInput(){
        System.out.println("Input invalid, please try again.");
    }

    /**
     * Print notification that no attendees are signed up
     */
    public void printNoAttendees(String eventName){
        System.out.println("There are no attendees signed up in " + eventName);
    }

    /**
     * Print notification that current user does not have permission to message recipient.
     */
    public void printCannotSend(){
        System.out.println("You do not have permission to send messages to this user.");
    }

    /**
     * Print list of Speakers in the conference.
     * @param userManager - an UserManager instance
     */
    public void printSpeakers(UserManager userManager){
        System.out.println("You can message the following Speakers:");
        System.out.println(userManager.getSpeakerList());
    }

    /**
     * Print list of Attendees in the conference.
     * @param userManager - an UserManager instance
     */
    public void printAttendees(UserManager userManager){
        System.out.println("You can message the following Attendees:");
        ArrayList<String> attendees = userManager.getAttendeeList();
        attendees.remove(username);
        System.out.println(attendees);
    }

    /**
     * Print list of users attending the event
     * @param eventManager - an EventManager instance
     */
    public void printAttendeesEvent(EventManager eventManager, String eventName){
        System.out.println("The users enrolled in " + eventName + " are:");
        ArrayList<String> attendees = eventManager.getEventAttendees(eventName);
        System.out.println(attendees);
    }

    /**
     * Print list of all the events that Speaker is giving
     * @param eventManager - an EventManager instance
     * @param username - username of the user
     */
    public void printEvent(EventManager eventManager, String username){
        System.out.println("You are hosting following events:");
        ArrayList<String> events = eventManager.getEventListBySpeaker(username);
        System.out.println(events);
    }
}
