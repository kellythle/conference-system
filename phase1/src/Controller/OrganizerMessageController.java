package Controller;

import Presenter.MessagePresenter;
import UseCase.MessageManager;
import UseCase.UserManager;

import java.util.Scanner;

public class OrganizerMessageController {
    private final MessageManager myMessageManager;
    public UserManager myUserManager;
    private final String username;
    public MessagePresenter messagePresenter;

    public OrganizerMessageController(String username, UserManager myUserManager){
        myMessageManager = new MessageManager(username);
        this.myUserManager = myUserManager;
        this.username = username;
        messagePresenter = new MessagePresenter(username);
    }

    /**
     *
     * @param receiverID - username of user receiving the message
     * @param messageContent - content of the message
     * @return true if message created
     */
    public boolean sendSingleMessage(String receiverID, String messageContent){
        if (myUserManager.canSend(username, receiverID)){
            return myMessageManager.createMessage(receiverID, messageContent);
        } else {return false;}
    }


    /**
     * Prompts user for receiver ID and message content. Sends single message.
     */
    public void sendMessage(){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.printReceiverIDPrompt();
        String receiver = scanner.nextLine();
        messagePresenter.printContentPrompt();
        String content = scanner.nextLine();
        if (sendSingleMessage(receiver, content)){
            messagePresenter.printMessageSuccess();
        } else {
            messagePresenter.printInvalidUsername();
        }
    }


    /**
     *
     *
     * @param messageContent - content of the message
     * @return true if message created
     */
    public boolean sendAllSpeakersMessage(String messageContent){
        for (String userName: myUserManager.getSpeakerList()) {
            if (myUserManager.canSend(this.username, userName)) {
                return false;
            }
            else {
               myMessageManager.createMessage(userName, messageContent);
            }
        }
        return !myUserManager.getSpeakerList().isEmpty();
    }

    /**
     * Prompts user for receiver ID and message content. Sends single message.
     */
    public void sendMessagesToSpeakers(){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.printContentPrompt();
        String content = scanner.nextLine();
        if (sendAllSpeakersMessage(content)){
            messagePresenter.printMessageSuccess();
        } else {
            messagePresenter.printMessageFailed();
        }
    }


    /**
     *
     * @param messageContent - content of the message
     * @return true if message created
     */
    public boolean sendAllAttendeesMessage(String messageContent){
        for (String userName: myUserManager.getAttendeeList()) {
            if (myUserManager.canSend(this.username, userName)) {
                return false;
            }
            else {
                myMessageManager.createMessage(userName, messageContent);
            }
        }
        return !myUserManager.getAttendeeList().isEmpty();
    }

    /**
     * Prompts user for receiver ID and message content. Sends single message.
     */
    public void sendMessagesToAttendees(){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.printContentPrompt();
        String content = scanner.nextLine();
        if (sendAllAttendeesMessage(content)){
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
        messagePresenter.printOrganizerMessageMenu();
        return scan.nextLine();
    }

    /**
     * Displays all conversations of user. Prompts the user to view a specific message history.
     */
    public void viewConversations(){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.viewConversations(myMessageManager);
        String input = scanner.nextLine();
        if (myMessageManager.getSenderConversations().contains(input)){
            viewOrganizerSingleConversation(input);
        }
    }

    /**
     * Displays single message history.
     * continue browsing conversations, or return to Message menu.
     *
     * @param conversationPartner - Partner who has been messaging with the user
     */
    public void viewOrganizerSingleConversation(String conversationPartner){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.viewOrganizerSingleConversation(myMessageManager, myUserManager, conversationPartner);
        String input = scanner.nextLine();
        if (input.equals("0")){
            viewConversations();
        }
    }

    public void invalidInput(){
        messagePresenter.printInvalidInput();
    }


}
