package Presenter;

import UseCase.MessageManager;
import UseCase.UserManager;

import java.util.ArrayList;
import java.util.UUID;

public class MessagePresenter {
    private String username;

    public MessagePresenter(String username){
        this.username = username;
    }

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

    public void printMessageMenu(){
        System.out.println("Welcome to the Attendee Message Menu\n" +
                "Options:\n" +
                "0. Exit Message Menu\n" +
                "1. View conversations and reply to messages\n" +
                "2. Send a message:\n"+
                "Enter 0, 1, or 2: ");
        }

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

    public void printSpeakerMessageMenu(){
        System.out.println("Welcome to the Speaker Message Menu.\n" +
                "Options:\n" +
                "0. Exit Message Menu\n" +
                "1. View conversations and reply to messages\n" +
                "2. Send a message to Attendees of your Talks:\n" +
                "Enter 0, 1, or 2: ");
    }

    public void viewConversations(MessageManager messageManager){
        System.out.println("You have conversations with these users: ");
        System.out.println(messageManager.getSenderConversations());
        System.out.println("Enter a username to see your message history or enter anything else to return " +
                "to the Message Menu: ");
    }

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

    public void viewSingleConversation(MessageManager messageManager, UserManager userManager, String recipientID) {
        printSingleConversation(messageManager, userManager, recipientID);
        System.out.println("Options:\n" +
                "0: Reply to this conversation\n" +
                "1. Continue browsing conversations\n" +
                "Enter 0, 1, or anything else to return to the Message Menu: ");
    }

    public void viewOrganizerSingleConversation(MessageManager messageManager, UserManager userManager, String recipientID) {
        printSingleConversation(messageManager, userManager, recipientID);
        System.out.println("Enter 0 to continue browsing conversations or enter anything else to return " +
                "to the Message Menu: ");
    }


    public void printReceiverIDPrompt(){
        System.out.println("Enter the username of the user you wish to message: ");
    }

    public void printEventIDPrompt(){
        System.out.println("Enter the name of the event whose attendees you wish to message: ");
    }

    public void printSpeakerMessagePrompt(){
        System.out.println("Options:\n" +
                "0. Return to previous menu\n" +
                "1. Choose a Talk\n" +
                "2. Send to all of your Talks\n" +
                "Enter 0, 1, or 2: ");
    }

    public void printContentPrompt(){
        System.out.println("Enter the message text: ");
    }

    public void printMessageSuccess(){
        System.out.println("Message successfully sent!");
    }

    public void printMessageFailed(){
        System.out.println("Message failed.");
    }

    public void printInvalidInput(){
        System.out.println("Input invalid, please try again.");
    }

    public void printInvalidUsername(){
        System.out.println("This user does not exist, please try again.");
    }

    public void printCannotSend(){
        System.out.println("You do not have permission to send messages to this user.");
    }


}
