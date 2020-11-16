package Presenter;

import UseCase.MessageManager;
import UseCase.UserManager;

import java.util.ArrayList;

public class MessagePresenter {
    private String username;

    public MessagePresenter(String username){
        this.username = username;
    }

    public String getMessageText(MessageManager messageManager, int messageID){
        String sender;
        String receiver;
        if (messageManager.isSender(messageID)){
            sender = "you";
            receiver = messageManager.getMessageReceiver(messageID);
        } else {
            sender = messageManager.getMessageSender(messageID);
            receiver = "you";
        }
        String messageText = sender + " sent to " + receiver + ":\n" + messageManager.getMessageContent(messageID) +
                "\n           at " + messageManager.getMessageTime(messageID);
        return messageText;
    }

    public void printMessageMenu(){
        System.out.println("Welcome to Attendee Message menu. Please select an action:");
        System.out.println("Enter 0 to exit Messages\n" +
                "Enter 1 to view conversations and reply to messages\n" +
                "Enter 2 to send a message:");
        }

    public void printOrganizerMessageMenu(){
        System.out.println("Welcome to Organizer Message menu. Please select an action:");
        System.out.println("Enter 0 to exit Messages\n" +
                "Enter 1 to view conversations\n" +
                "Enter 2 to send a message to a single user\n" +
                "Enter 3 to send a message to all Speakers\n" +
                "Enter 4 to send a message to all Attendees:");
    }

    public void printSpeakerMessageMenu(){
        System.out.println("Welcome to Speaker Message menu. Please select an action:");
        System.out.println("Enter 0 to exit Messages\n" +
                "Enter 1 to view conversations and reply to messages\n" +
                "Enter 2 to send a message to Attendees of your Talks:");
    }

    public void viewConversations(MessageManager messageManager){
        System.out.println("You have conversations with these users:");
        System.out.println(messageManager.getSenderConversations());
        System.out.println("Enter a username to see your message history\n" +
                "Enter anything else to return to Message Menu:");
    }

    public void printSingleConversation(MessageManager messageManager, UserManager userManager, String recipientID){
        String identity = "Attendee";
        if (userManager.isOrganizer(recipientID)) {identity = "Organizer";}
        else if (userManager.isSpeaker(recipientID)) {identity = "Speaker";}
        System.out.println("Your conversation with " + identity + " " + recipientID + ": ");
        ArrayList<Integer> singleConversation = messageManager.getSingleConversationByReceiver(recipientID);
        for (int i: singleConversation){
            String messageText = getMessageText(messageManager, i);
            System.out.println(messageText);
        }
    }

    public void viewSingleConversation(MessageManager messageManager, UserManager userManager, String recipientID) {
        printSingleConversation(messageManager, userManager, recipientID);
        System.out.println("Enter 0 reply to this conversation\n" +
                "Enter 1 to continue browsing conversations\n" +
                "Enter anything else to return to Message Menu:");
    }

    public void viewOrganizerSingleConversation(MessageManager messageManager, UserManager userManager, String recipientID) {
        printSingleConversation(messageManager, userManager, recipientID);
        System.out.println("Enter 0 to continue browsing conversations\n" +
                "Enter anything else to return to Message Menu:");
    }


    public void printReceiverIDPrompt(){
        System.out.println("Enter the username of the user you wish to message:");
    }

    public void printEventIDPrompt(){
        System.out.println("Enter the ID of the event whose attendees you wish to message:");
    }

    public void printSpeakerMessagePrompt(){
        System.out.println("Enter 0 to return to go back\n"
                + "Enter 1 to choose a Talk\n" +
                "Enter 2 to send to all of your Talks: ");
    }

    public void printContentPrompt(){
        System.out.println("Enter the message text:");
    }

    public void printMessageSuccess(){
        System.out.println("Message successfully sent.");
    }

    public void printMessageFailed(){
        System.out.println("Message failed.");
    }

    public void printInvalidInput(){
        System.out.println("Input invalid. Try again.");
    }

    public void printInvalidUsername(){
        System.out.println("This user does not exist. Try again.");
    }

    public void printCannotSend(){
        System.out.println("You do not have permission to send messages to this user.");
    }


}
