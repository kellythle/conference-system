package Controller;

import Entity.Event;
import Presenter.MessagePresenter;
import UseCase.EventManager;
import UseCase.MessageManager;
import UseCase.UserManager;

import java.util.ArrayList;
import java.util.Scanner;

public class SpeakerMessageController extends MessageController {
    private final EventManager myEventManager;

    /**
     * A controller class that calls MessageManager to manage any
     * command related to Speaker's Messaging System
     *
     * @author Yu Jin Kim
     */

    public SpeakerMessageController(String username, MessageManager myMessageManager, UserManager myUserManager, EventManager myEventManager){
        super(username, myUserManager, myMessageManager);
        this.myEventManager = myEventManager;
        messagePresenter = new MessagePresenter(username);
    }


    /**
     *
     * Sends messages to all attendees enrolled in any Speaker's events.
     *
     * @param messageContent - message of the content
     * @return true if message created
     */
    public boolean sendAllMessageAllEvent(String messageContent) {
        ArrayList<String> usernames = new ArrayList<>();
        for (Event event : myEventManager.getEventList()) {
            if (event.getSpeaker().equals(username)) {
                if (event.getAttendees().isEmpty()) {
                    messagePresenter.printNoAttendees(event.getName());
                }
                for (String userName : event.getAttendees()) {
                    if (!usernames.contains(userName)) {
                        myMessageManager.createMessage(userName, messageContent);
                        usernames.add(userName);
                    }
                }
            }
        }
        return !myEventManager.getEventList().isEmpty();
    }

    /**
     * Prompts user for receiver ID and message content. Sends single message.
     */
    public void sendMessagesToAttendeesOfAllTalks(){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.printEvent(myEventManager, username);
        messagePresenter.printContentPrompt();
        String content = scanner.nextLine();
        if (sendAllMessageAllEvent(content)){
            messagePresenter.printMessageSuccessSpeaker();
        } else {
            messagePresenter.printMessageFailed();
        }
    }

    /**
     *
     * Sends messages to all attendees enrolled in chosen event.
     *
     * @param messageContent - message of the content
     * @return true if message created
     */
    public boolean sendAllMessageAnEvent(String messageContent, String eventName) {
        if (myEventManager.getEventList().isEmpty()) {
            return false;
        }
        for (Event event : myEventManager.getEventList()) {
            if (event.getName().equals(eventName)) {
                if (event.getAttendees().isEmpty()){
                    messagePresenter.printNoAttendees(eventName);
                    return false;
                }
                if (!event.getSpeaker().equals(username)) {
                    return false;
                }
                for (String userName : event.getAttendees()) {
                    myMessageManager.createMessage(userName, messageContent);
                }
            }
        }
        return myEventManager.getEvent(eventName);
    }

    /**
     * Prompts user for receiver ID and message content. Sends single message.
     */
    public void sendMessagesToAttendeesOfATalk(){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.printEvent(myEventManager, username);
        messagePresenter.printEventIDPrompt();
        String eventName = scanner.nextLine();
        messagePresenter.printAttendeesEvent(myEventManager, eventName);
        messagePresenter.printContentPrompt();
        String content = scanner.nextLine();
        if (sendAllMessageAnEvent(content, eventName)) {
            messagePresenter.printMessageSuccess();
        } else {
            messagePresenter.printMessageFailed();
        }
    }

    /**
     * Prompts user for sending message to all of the events or a single event.
     */
    public void sendMessage(){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.printSpeakerMessagePrompt();
        String input = scanner.nextLine();
        boolean loop = false;
        do {
            switch (input) {
                case "0":
                    loop = true;
                    break;
                case "1":
                    loop = true;
                    sendMessagesToAttendeesOfATalk();
                    break;
                case "2":
                    loop = true;
                    sendMessagesToAttendeesOfAllTalks();
                    break;
            }
        } while (!loop);
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
        return myMessageManager.createMessage(receiverID, messageContent);
    }

    /**
     * Displays single message history. Prompts the user to either reply to the conversation,
     * continue browsing conversations, or return to Message menu.
     *
     * @param conversationPartner - Partner who has been messaging with the user
     */
    public void viewSingleConversation(String conversationPartner){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.viewSpeakerSingleConversation(myMessageManager, myUserManager, conversationPartner);
        String input = scanner.nextLine();
        boolean loop = false;
        do {
            switch (input) {
                case "0":
                    loop = true;
                    break;
                case "1":
                    loop = true;
                    replyToConversation(conversationPartner);
                    break;
                case "2":
                    loop = true;
                    viewConversations();
                    break;
            }
        } while (!loop);
    }

    /**
     * Prompts user for reply content and replies to conversation.
     *
     * @param conversationPartner - Partner who has been messaging with the user
     */

    public void replyToConversation(String conversationPartner){
        messagePresenter.printContentPrompt();
        Scanner scanner = new Scanner(System.in);
        String content = scanner.nextLine();
        if (sendSingleMessage(conversationPartner, content)) {
            messagePresenter.printMessageSuccess();
        } else {
            messagePresenter.printMessageFailed();
        }
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


