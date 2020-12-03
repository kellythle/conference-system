package Controller;

import UseCase.MessageManager;
import UseCase.UserManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

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
        switch (input) {
            case "0":
                archiveSingleMessage(conversationPartner);
                break;
            case "1":
                archiveConversation(conversationPartner);
                break;
            case "2":
                deleteSingleMessage(conversationPartner);
                break;
            case "3":
                deleteConversation(conversationPartner);
                break;
            case "4":
                viewConversations();
                break;
        }
    }

    /**
     * Deletes a single message of a conversation, for organizer and conversation partner both.
     *
     * @param conversationPartner - username of the conversation to delete a single message from.
     */
    @Override
    public void deleteSingleMessage(String conversationPartner){
        ArrayList<UUID> conversation = myMessageManager.getSingleConversationByReceiver(conversationPartner);
        int toBeDeleted = checkDeleteSingleMessage(conversation);
        if (toBeDeleted > -1){
            myMessageManager.deleteSingleMessageBothSides(conversation.get(toBeDeleted));
            messagePresenter.printMessageUserReceiverDelete();
        }
    }

    /**
     * Deletes entire conversation, for organizer and conversation partner both.
     *
     * @param conversationPartner - username partner of the conversation to be deleted.
     */
    @Override
    public void deleteConversation(String conversationPartner){
        Scanner scan = new Scanner(System.in);
        messagePresenter.printConfirmationPrompt();
        String confirm = scan.nextLine();
        if (confirm.equals("Y")){
            myMessageManager.deleteConversationBothSides(conversationPartner);
            messagePresenter.printConversationUserReceiverDelete();
        }
    }

    /**
     * Displays messages deleted from the inbox of both sender and receiver.
     */
    public void viewDeletedMessagesBin(){
        messagePresenter.viewFullyDeletedMessages(myMessageManager);
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if (myMessageManager.getDeletedMessagesSenderReceivers().contains(input)){
            viewUserDeletedMessagesBin(username);
        }
    }

    private void viewUserDeletedMessagesBin(String username){
        messagePresenter.viewUserFullyDeletedMessage(myMessageManager,username);
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if (input.equals("0")){
            viewDeletedMessagesBin();
        }
    }
}
