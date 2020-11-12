package Presenter;

import UseCase.MessageManager;

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
        String messageText = sender + "sent to" + receiver + "\n" + messageManager.getMessageContent(messageID) +
                "\n           at " + messageManager.getMessageTime(messageID);
        return messageText;
    }

    public void printMessageMenu(){
        System.out.println("Welcome to Message menu. Please select an action:");
        System.out.println("Enter 0 to exit Messages\n " +
                "Enter 1 to view conversations\n" +
                "Enter 2 to send a message" +
                "Enter 3 to return to the main menu.");
        }

    public void viewConversations(MessageManager messageManager){
        System.out.println("You have conversations with these users:");
        System.out.println(messageManager.getSenderConversations());
        System.out.println("Enter a username to see your message history\n" +
                "Enter 0 to return to Message menu:");
    }

    public void viewSingleConversation(MessageManager messageManager, String recipientID) {
        System.out.println("Your conversation with " + recipientID + ": ");
        ArrayList<Integer> singleConversation = messageManager.getSingleConversation(recipientID);
        for (int i: singleConversation){
            String messageText = getMessageText(messageManager, i);
            System.out.println(messageText);
        }
    }

    public void printReceiverIDPrompt(){
        System.out.println("Enter the username of the user you wish to message:");
    }

    public void printEventIDPrompt(){
        System.out.println("Enter the ID of the event whose attendees you wish to message:");
    }

    public void printSpeakerMessagePrompt(){
        System.out.println("Enter the ID of your talk whose attendees you wish to message \n" +
                "or enter nothing to message all your talks:");
    }

    public void printContentPrompt(){
        System.out.println("Enter the message text:");
    }

    public void printMessageSuccess(){
        System.out.println("Message successfully sent.");
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

    public void printReplyPrompt(){
        System.out.println("If you want to reply to a message, ");
    }
}
