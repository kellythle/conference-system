package Controller;

import Entity.Event;
import Presenter.MessagePresenter;
import UseCase.EventManager;
import UseCase.MessageManager;
import UseCase.UserManager;

import java.util.Scanner;

public class SpeakerMessageController {
    private MessageManager myMessageManager;
    private UserManager myUserManager;
    private EventManager myEventManager;
    private String username;
    public MessagePresenter messagePresenter;


    public SpeakerMessageController(String username, UserManager myUserManager, EventManager myEventManager){
        myMessageManager = new MessageManager(username);
        this.myUserManager = myUserManager;
        this.myEventManager = myEventManager;
        this.username = username;
        messagePresenter = new MessagePresenter(username);
    }


    /**
     *
     *
     * @param messageContent
     * @return true if message created
     */
    public boolean sendAllMessageAllEvent(String messageContent) {
        for (Event event : myEventManager.getEventList()) {
            if (event.getSpeaker().equals(username)) {
                for (String userName : event.getAttendees()) {
                    myMessageManager.createMessage(userName, messageContent);
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
     * @param messageContent
     * @return true if message created
     */
    public boolean sendAllMessageAnEvent(String messageContent, int eventID) {
        for (Event event : myEventManager.getEventList()) {
            if (event.getId() == eventID) {
                for (String userName : event.getAttendees()) {
                    myMessageManager.createMessage(userName, messageContent);
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
        while (true) {
            messagePresenter.printSpeakerMessageMenu();
            String input = scanner.nextLine();
            if (input.equals("0")){
                sendMessagesToAttendeesOfATalk();
            } else if (input.equals("1")){
                sendMessagesToAttendeesOfAllTalks();
            } else {
                messageMenu();
            }
        }
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
        } else {
            return;
        }
    }

    /**
     * Creates single message.
     *
     * @param receiverID
     * @param messageContent
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
     * @param conversationPartner
     */
    public void viewSingleConversation(String conversationPartner){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.viewSingleConversation(myMessageManager, myUserManager, conversationPartner);
        String input = scanner.nextLine();
        if (input == "0"){
            replyToConversation(conversationPartner);
        } else if (input == "1"){
            viewConversations();
        } else {
            return;
        }
    }

    /**
     * Prompts user for reply content and replies to conversation.
     *
     * @param conversationPartner
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
     * Displays message menu. The user can select an action. Once that action is completed
     * the user will return to the message menu, unless the action is to exit messages
     * and return to the main menu.
     */
    public void messageMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            messagePresenter.printSpeakerMessageMenu();
            String input = scanner.nextLine();
            if (input.equals("0")){
                break;
            } else if (input.equals("1")){
                viewConversations();
            } else if (input.equals("2")){
                sendMessage();
            } else {
                messagePresenter.printInvalidInput();
            }
        }
    }

    /**
     * Calls MessagePresenter to print out the Message Menu.
     */
    public String getMessageMenu(){
        Scanner scan = new Scanner(System.in);
        messagePresenter.printSpeakerMessageMenu();
        String input = scan.nextLine();
        return input;
    }

    public void invalidInput(){
        messagePresenter.printInvalidInput();
    }


}


