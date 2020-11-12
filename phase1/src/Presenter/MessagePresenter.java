package Presenter;

import UseCase.MessageManager;

import java.util.ArrayList;
import java.util.Scanner;

public class MessagePresenter {
    private MessageManager messageManager;
    private String username;

    public MessagePresenter(MessageManager manager, String username){
        messageManager = manager;
        this.username = username;
    }

    public String getMessageText(int messageID){
        String sender;
        String receiver;
        if (messageManager.isSender(messageID)){
            sender = "you";
            receiver = messageManager.getMessageReceiver(messageID);
        } else {
            sender = messageManager.getMessageSender(messageID);
            receiver = "you";
        }
        String messageText = sender + "sent to" + receiver + "\n" + messageManager.getMessageContent(messageID);
        return messageText;
    }

    public ArrayList<String> getConversationList(){
        return messageManager.getSenderConversations();
    }

    public int messageMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Message menu. Please select an action:");
        System.out.println("Enter 0 to exit Messages\n " +
                "Enter 1 to view conversations\n" +
                "Enter 2 to send a message");
        int i = scan.nextInt();
        return i;
    }

    public String viewConversations(){
        Scanner scan = new Scanner(System.in);
        System.out.println("You have conversations with these users:");
        System.out.println(getConversationList());
        System.out.println("Enter a username to see your message history\n" +
                "Enter 0 to return to menu");
        String name = scan.next();
        return name;
    }

    public ArrayList<String> sendToSingleUser(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the username of the user you wish to message:");
        String name = scan.next();
        System.out.println("Enter the message you wish to send:");
        String content = scan.next();
        ArrayList<String> toSend = new ArrayList<String>();
        toSend.add(name);
        toSend.add(content);
        return toSend;
    }

    public ArrayList<String> sendToEventAttendees(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the ID of the event you wish to message the attendees of:");
        String event = scan.next();
        System.out.println("Enter the message you wish to send:");
        String content = scan.next();
        ArrayList<String> toSend = new ArrayList<String>();
        toSend.add(event);
        toSend.add(content);
        return toSend;
    }

}
