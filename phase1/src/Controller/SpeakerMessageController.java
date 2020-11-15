package Controller;

import Entity.Event;
import Presenter.MessagePresenter;
import UseCase.EventManager;
import UseCase.MessageManager;
import UseCase.UserManager;

import java.util.Scanner;

public class SpeakerMessageController {
    private final MessageManager myMessageManager;
    private final UserManager myUserManager;
    private final EventManager myEventManager;
    private final String username;
    public MessagePresenter messagePresenter;


    public SpeakerMessageController(String username, MessageManager myMessageManager, UserManager myUserManager, EventManager myEventManager){
        this.myMessageManager = myMessageManager;
        this.myUserManager = myUserManager;
        this.myEventManager = myEventManager;
        this.username = username;
        messagePresenter = new MessagePresenter(username);
    }


    /**
     *
     *
     * @param messageContent - message of the content
     * @return true if message created
     */
    public boolean sendAllMessageAllEvent(String messageContent) {
        for (Event event : myEventManager.getEventList()) {
            if (event.getSpeaker().equals(username)) {
                for (String userName : event.getAttendees()) {
                    if (myUserManager.canSend(this.username, userName)) {
                        return false;
                    }
                    else {
                        myMessageManager.createMessage(userName, messageContent);
                    }
                }
            }
        }
        return !myUserManager.getSpeakerList().isEmpty();
    }

    /**
     * Prompts user for receiver ID and message content. Sends single message.
     */
    public void sendMessagesToAttendeesOfAllTalks(){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.printContentPrompt();
        String content = scanner.nextLine();
        if (sendAllMessageAllEvent(content)){
            messagePresenter.printMessageSuccess();
        } else {
            messagePresenter.printMessageFailed();
        }
    }

    /**
     *
     *
     * @param messageContent - message of the content
     * @return true if message created
     */
    public boolean sendAllMessageAnEvent(String messageContent, int eventID) {
        for (Event event : myEventManager.getEventList()) {
            if (event.getId() == eventID) {
                for (String userName : event.getAttendees()) {
                    if (myUserManager.canSend(this.username, userName)) {
                        return false;
                    }
                    else {
                        myMessageManager.createMessage(userName, messageContent);
                    }
                }
            }
        }
        return !myUserManager.getSpeakerList().isEmpty();
    }

    /**
     * Prompts user for receiver ID and message content. Sends single message.
     */
    public void sendMessagesToAttendeesOfATalk(){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.printContentPrompt();
        String content = scanner.nextLine();
        messagePresenter.printEventIDPrompt();
        int eventID = scanner.nextInt();
        if (sendAllMessageAnEvent(content,eventID)) {
            messagePresenter.printMessageSuccess();
        } else {
            messagePresenter.printMessageFailed();
        }
    }

    public void sendMessage(){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.printSpeakerMessagePrompt();
        String input = scanner.nextLine();
        do {
            switch (input) {
                case "0":
                    break;
                case "1":
                    sendMessagesToAttendeesOfATalk();
                    break;
                case "2":
                    sendMessagesToAttendeesOfAllTalks();
                    break;
            }
        } while (!input.equals("2"));
    }

    /**
     * Displays all conversations of user. Prompts the user to view a specific message history.
     */
    public void viewConversations(){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.viewConversations(myMessageManager);
        String input = scanner.nextLine();
        if (myMessageManager.getSenderConversations().contains(input)){
            viewSingleConversation(input);
        }
    }

    /**
     * Creates single message.
     *
     * @param receiverID - username of user receiving the message
     * @param messageContent - message of the content
     * @return true if message created
     */
    public boolean sendSingleMessage(String receiverID, String messageContent){
        if (myUserManager.canSend(username, receiverID)){
            return myMessageManager.createMessage(receiverID, messageContent);
        } else {return false;}
    }

    /**
     * Displays single message history. Prompts the user to either reply to the conversation,
     * continue browsing conversations, or return to Message menu.
     *
     * @param conversationPartner - Partner who has been messaging with the user
     */
    public void viewSingleConversation(String conversationPartner){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.viewSingleConversation(myMessageManager, myUserManager, conversationPartner);
        String input = scanner.nextLine();
        do {
            switch (input) {
                case "0":
                    replyToConversation(conversationPartner);
                    break;
                case "1":
                    viewConversations();
                    break;
            }
        } while (!input.equals("1"));
    }

    /**
     * Prompts user for reply content and replies to conversation.
     *
     * @param conversationPartner - Partner who has been messaging with the user
     */
    public void replyToConversation(String conversationPartner){
        if (myMessageManager.canSend(conversationPartner)) {
            messagePresenter.printContentPrompt();
            Scanner scanner = new Scanner(System.in);
            String content = scanner.nextLine();
            if (sendSingleMessage(conversationPartner, content)) {
                messagePresenter.printMessageSuccess();
            } else {
                messagePresenter.printMessageFailed();
            }
        } else{messagePresenter.printCannotSend();}
    }

    /**
     * Calls MessagePresenter to print out the Message Menu.
     */
    public String getMessageMenu(){
        Scanner scan = new Scanner(System.in);
        messagePresenter.printSpeakerMessageMenu();
        return scan.nextLine();
    }

    public void invalidInput(){
        messagePresenter.printInvalidInput();
    }


}


