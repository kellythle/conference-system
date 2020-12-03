package Controller;

import UseCase.MessageManager;
import UseCase.UserManager;

import java.util.Scanner;

/**
 * A controller class that calls MessageManager to manage any
 * command related to Organizer's Messaging System
 *
 * @author Yu Jin Kim
 */

public class OrganizerMessageController extends MessageController {

    /**
     * Constructor for OrganizerMessageController
     * Creates a new instance of messagePresenter.
     *
     * @param username String username of current user
     * @param myUserManager instance of UserManager
     * @param myMessageManager instance of MessageManager
     */
    public OrganizerMessageController(String username, MessageManager myMessageManager, UserManager myUserManager){
        super(username, myUserManager, myMessageManager);
    }

    public boolean sendSingleMessage(String receiverID, String messageContent){
        if (myUserManager.canSend(username, receiverID)){
            return myMessageManager.createMessage(receiverID, messageContent);
        } else {return false;}
    }

    /**
     * Sends messages to all Speakers in the system.
     *
     * @param messageContent - content of the message
     * @return true if message created
     */
    public boolean sendAllSpeakersMessage(String messageContent){
        for (String userName: myUserManager.getSpeakerList()) {
            if (!myUserManager.canSend(this.username, userName)) {
                return false;
            }
            else {
               myMessageManager.createMessage(userName, messageContent);
            }
        }
        return !myUserManager.getSpeakerList().isEmpty();
    }

    /**
     * Prompts user for message content. Sends messages to all Speakers.
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
     * Sends messages to all Attendees in the system.
     *
     * @param messageContent - content of the message
     * @return true if message created
     */
    public boolean sendAllAttendeesMessage(String messageContent){
        for (String userName: myUserManager.getAttendeeList()) {
            if (!myUserManager.canSend(this.username, userName)) {
                return false;
            }
            else {
                myMessageManager.createMessage(userName, messageContent);
            }
        }
        return !myUserManager.getAttendeeList().isEmpty();
    }

    /**
     * Prompts user for receiver ID and message content. Sends messages to all Attendees.
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
     * Displays single message history.
     * continue browsing conversations, or return to Message menu.
     *
     * @param conversationPartner - Partner who has been messaging with the user
     */
    public void viewSingleConversation(String conversationPartner){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.viewOrganizerSingleConversation(myMessageManager, myUserManager, conversationPartner);
        String input = scanner.nextLine();
        if (input.equals("0")){
            archiveSingleMessage(conversationPartner);
        } else if (input.equals("1")){
            archiveConversation(conversationPartner);
        } else if (input.equals("2")){
            deleteSingleMessage(conversationPartner);
        } else if (input.equals("3")){
            deleteConversation(conversationPartner);
        } else if (input.equals("4")){
            viewConversations();
        }
    }


}
