package Controller;

import Presenter.MessagePresenter;
import UseCase.MessageManager;
import UseCase.UserManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

/**
 * Controller that deals with viewing and sending messages for Attendees.
 *
 * @author Wenying Wu, Yu Jin Kim
 */
public class MessageController {
    final MessageManager myMessageManager;
    UserManager myUserManager;
    final String username;
    MessagePresenter messagePresenter;

    /**
     * Constructor for MessageController
     * Creates a new instance of messagePresenter.
     *
     * @param username String username of current user
     * @param myUserManager instance of UserManager
     * @param messageManager instance of MessageManager
     */
    public MessageController(String username, UserManager myUserManager, MessageManager messageManager){
        myMessageManager = messageManager;
        this.myUserManager = myUserManager;
        this.username = username;
        messagePresenter = new MessagePresenter(username);
    }

    /**
     * Creates single message.
     *
     * @param receiverID String username of the recipient of the message
     * @param messageContent String content of the message
     * @return true if message created
     */
    public boolean sendSingleMessage(String receiverID, String messageContent){
        if (myUserManager.canSend(username, receiverID)){
            return myMessageManager.createMessage(receiverID, messageContent);
        } else {return false;}
    }

    /**
     * Displays all conversations of user. Prompts the user to view a specific message history.
     */
    public void viewConversations(){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.viewConversations(myMessageManager);
        String input = scanner.nextLine();
        if (input.equals("1")){
            markAsRead();
        } else if (input.equals("2")){
            markAsUnread();
        } else if (myMessageManager.getSenderConversations().contains(input)){
            viewSingleConversation(input);
        }
    }

    /**
     * Prompts for username, marks conversation as read.
     */
    public void markAsRead(){
        messagePresenter.printConversationPrompt();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (myMessageManager.getSenderConversations().contains(input)){
            if (myMessageManager.getUnreadSenderMessages().contains(input)){
                myMessageManager.markConversationAsRead(input);
            } messagePresenter.printMarkedAsRead();
        } else {
            messagePresenter.printConversationDoesNotExist();
        }
    }

    /**
     * Prompts for username, marks conversation as unread.
     */
    public void markAsUnread(){
        messagePresenter.printConversationPrompt();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (myMessageManager.getSenderConversations().contains(input)){
            if (!myMessageManager.getUnreadSenderMessages().contains(input)){
                myMessageManager.markConversationAsUnread(input);
            } messagePresenter.printMarkedAsUnread();
        } else {
            messagePresenter.printConversationDoesNotExist();
        }
    }

    /**
     * Displays all archived conversations of user. Prompts the user to view a specific message history.
     * Or delete all conversations in the archive.
     */

    public void viewArchivedConversations(){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.viewArchivedConversations(myMessageManager);
        String input = scanner.nextLine();
        if (input.equals("1")){
            deleteAllArchivedConversations();
        } else if (myMessageManager.getSenderArchivedConversations().contains(input)){
            viewSingleArchiveConversation(input);
        }
    }

    /**
     * Displays single message history. Prompts the user to either reply to the conversation,
     * continue browsing conversations, or return to Message menu.
     *
     * @param conversationPartner String username of the other participant in this conversation history.
     */
    public void viewSingleConversation(String conversationPartner){
        Scanner scanner = new Scanner(System.in);
        messagePresenter.viewSingleConversation(myMessageManager, myUserManager, conversationPartner);
        String input = scanner.nextLine();
        if (input.equals("0")){
            replyToConversation(conversationPartner);
        } else if (input.equals("1")){
            archiveSingleMessage(conversationPartner);
        } else if (input.equals("2")){
            archiveConversation(conversationPartner);
        } else if (input.equals("3")){
            deleteSingleMessage(conversationPartner);
        } else if (input.equals("4")){
            deleteConversation(conversationPartner);
        } else if (input.equals("5")){
            viewConversations();
        }
    }

    /**
     * Displays single archived message history.
     *
     * @param conversationPartner - username of other participant in conversation history.
     */
    public void viewSingleArchiveConversation(String conversationPartner){
        messagePresenter.viewArchivedSingleConversation(myMessageManager, myUserManager, conversationPartner);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("0")){
            unarchiveConversation(conversationPartner);
        } else if (input.equals("1")){
            deleteArchivedConversation(conversationPartner);
        }
    }

    /**
     * Prompts user for reply content and replies to conversation.
     *
     * @param conversationPartner String username that the user is replying to.
     */
    public void replyToConversation(String conversationPartner){
        if (myUserManager.canSend(username, conversationPartner)) {
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
     * Prompts user for receiver ID and message content. Sends single message.
     */
    public void sendMessage(){
        messagePresenter.printAttendees(myUserManager);
        messagePresenter.printSpeakers(myUserManager);
        Scanner scanner = new Scanner(System.in);
        messagePresenter.printReceiverIDPrompt();
        String receiver = scanner.nextLine();
        messagePresenter.printContentPrompt();
        String content = scanner.nextLine();
        if (sendSingleMessage(receiver, content)){
            messagePresenter.printMessageSuccess();
        } else {
            messagePresenter.printMessageFailed();
        }
    }

    /**
     * Calls MessagePresenter to print Attendee message menu. Prompts for a response to a list of menu options.
     *
     * @return the String input from the User.
     */
    public String getMessageMenu(){
        Scanner scan = new Scanner(System.in);
        messagePresenter.printMessageMenu();
        return scan.nextLine();
    }

    /**
     * Calls MessagePresenter to print a notification of invalid input.
     */
    public void invalidInput(){
        messagePresenter.printInvalidInput();
    }

    public void deleteSingleMessage(String conversationPartner) {
        messagePresenter.printMessageNumberPrompt();
        ArrayList<UUID> conversation = myMessageManager.getSingleConversationByReceiver(conversationPartner);
        Scanner scan = new Scanner(System.in);
        int next = scan.nextInt();
        if (next > conversation.size()){
            messagePresenter.printMessageDoesNotExist();
        } else {
            messagePresenter.printConfirmationPrompt();
            String confirm = scan.nextLine();
            if (confirm.equals("Y")) {
                myMessageManager.deleteSingleMessage(conversation.get(next-1));
                messagePresenter.printMessageDeleted();
            }
        }
    }

    public void deleteConversation(String conversationPartner){
        Scanner scan = new Scanner(System.in);
        messagePresenter.printConfirmationPrompt();
        String confirm = scan.nextLine();
        if (confirm.equals("Y")){
            myMessageManager.deleteConversation(conversationPartner);
            messagePresenter.printConversationDeleted();
        }
    }

    public void archiveSingleMessage(String conversationPartner){
        messagePresenter.printMessageNumberPrompt();
        ArrayList<UUID> conversation = myMessageManager.getSingleConversationByReceiver(conversationPartner);
        Scanner scan = new Scanner(System.in);
        int next = scan.nextInt();
        if (next > conversation.size()){
            messagePresenter.printMessageDoesNotExist();
            return;
        }
        myMessageManager.archiveSingleMessage(conversation.get(next-1));
        messagePresenter.printMessageArchived();
    }

    public void archiveConversation(String conversationPartner){
        myMessageManager.archiveConversation(conversationPartner);
        messagePresenter.printConversationArchived();
    }

    public void unarchiveConversation(String conversationPartner){
        myMessageManager.unArchiveConversation(conversationPartner);
        messagePresenter.printMessageUnarchived();
    }

    public void deleteArchivedConversation(String conversationPartner){
        Scanner scan = new Scanner(System.in);
        messagePresenter.printConfirmationPrompt();
        String confirm = scan.nextLine();
        if (confirm.equals("Y")){
            myMessageManager.deleteArchivedConversation(conversationPartner);
            messagePresenter.printConversationDeleted();
        }
    }

    public void deleteAllArchivedConversations(){
        Scanner scan = new Scanner(System.in);
        messagePresenter.printConfirmationPrompt();
        String confirm = scan.nextLine();
        if (confirm.equals("Y")){
            myMessageManager.deleteAllArchivedConversations();
            messagePresenter.printArchiveDeletion();
        }
    }
}
