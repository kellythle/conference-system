package Presenter;

import UseCase.MessageManager;
import UseCase.UserManager;

import java.util.ArrayList;
import java.util.UUID;

public class MessagePresenter {
    private String username;

    /**
     * Create MessagePresenter with given username.
     * @param username
     */
    public MessagePresenter(String username){
        this.username = username;
    }

    /**
     * Generate string representation of a single message from given MessageManager and
     * message ID.
     *
     * @param messageManager
     * @param messageID
     * @return
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
     * @param messageManager
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
     * @param messageManager
     * @param userManager
     * @param recipientID
     */
    public void printSingleConversation(MessageManager messageManager, UserManager userManager, String recipientID){
        String identity = "Attendee";
        if (userManager.isOrganizer(recipientID)) {identity = "Organizer";}
        else if (userManager.isSpeaker(recipientID)) {identity = "Speaker";}
        System.out.println("Your conversation with " + identity + " " + recipientID + ": ");
        ArrayList<UUID> singleConversation = messageManager.getSingleConversationByReceiver(recipientID);
        for (UUID i: singleConversation){
            String messageText = getMessageText(messageManager, i);
            System.out.println(messageText);
        }
    }

    /**
     * Calls method to print single conversation between Attendee or Speaker user and a given recipient.
     * Prints options for Attendees and Speakers to continue browsing, reply to conversation, or return to
     * Message Menu.
     *
     * @param messageManager
     * @param userManager
     * @param recipientID
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
     * @param messageManager
     * @param userManager
     * @param recipientID
     */
    public void viewOrganizerSingleConversation(MessageManager messageManager, UserManager userManager, String
            recipientID) {
        printSingleConversation(messageManager, userManager, recipientID);
        System.out.println("Enter 0 to continue browsing conversations or enter anything else to return " +
                "to the Message Menu: ");
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
     * Print notification that userID does not exist.
     */
    public void printInvalidUsername(){
        System.out.println("This user does not exist, please try again.");
    }

    /**
     * Print notification that current user does not have permission to message recipient.
     */
    public void printCannotSend(){
        System.out.println("You do not have permission to send messages to this user.");
    }

    /**
     * Print list of Speakers in the conference.
     * @param userManager
     */
    public void printSpeakers(UserManager userManager){
        System.out.println("You can message the following Speakers:");
        System.out.println(userManager.getSpeakerList());
    }

    /**
     * Print list of Attendees in the conference.
     * @param userManager
     */
    public void printAttendees(UserManager userManager){
        System.out.println("You can message the following Attendees:");
        ArrayList<String> attendees = userManager.getAttendeeList();
        if (attendees.contains(username)){
            attendees.remove(username);
        }
        System.out.println(attendees);
    }
}
