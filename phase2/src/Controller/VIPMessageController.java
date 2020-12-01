package Controller;

import UseCase.MessageManager;
import UseCase.UserManager;

import java.util.Scanner;

public class VIPMessageController extends MessageController{

    /**
     * Constructor for VIPMessageController
     * Creates a new instance of messagePresenter.
     *
     * @param username       String username of current user
     * @param myUserManager  instance of UserManager
     * @param messageManager instance of MessageManager
     */
    public VIPMessageController(String username, UserManager myUserManager, MessageManager messageManager) {
        super(username, myUserManager, messageManager);
    }

    /**
     * Calls MessagePresenter to print VIP message menu. Prompts for a response to a list of menu options.
     *
     * @return the String input from the User.
     */
    public String getVIPMessageMenu() {
        Scanner scan = new Scanner(System.in);
        messagePresenter.printVIPMessageMenu();
        return scan.nextLine();
    }

    /**
     * Prompts user for receiver ID and message content. Sends single message.
     * Can message any speaker/organizer/VIP, or attendee in your friend list
     */
    @Override
    public void sendMessage(){
        messagePresenter.printAttendees(myUserManager);
        messagePresenter.printSpeakers(myUserManager);
        messagePresenter.printVIPs(myUserManager);
        messagePresenter.printOrganizers(myUserManager);
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
}
