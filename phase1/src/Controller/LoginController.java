package Controller;
import UseCase.UserManager;
import Presenter.LoginPresenter;

import java.util.Scanner;

/**
 * A controller that deals with account creation and login information
 *
 * @author Chaolin Wang
 */
public class LoginController {
    private String loggedInUser;
    private final UserManager um;
    private final LoginPresenter lp;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * A constructor for Login controller.
     * Takes in a MangerFacade, call the getter for userManager
     * @param userManager The ManagerFacade object where we get the managers from
     */
    public LoginController(UserManager userManager) {
        this.loggedInUser = null;
        this.um = userManager;
        lp = new LoginPresenter();
    }


    /**
     * Creates a new user, given the type of user and account information.
     *
     * ***This should only create Attendees and Organizers
     *
     * @param userName The new user's username, *cannot be a duplicate within the system
     * @param password The new user's password
     * @param type The type of user added, can only be 2 strings: "Attendee" or "Organizer"
     */
    public void createAccount(String userName, String password, String type) {
        //null edge case
        if (type == null) {
            lp.displayAccountCreateInfo(false);
            return;
        }
        // Since UserManager's create user can make Speakers, we need to exclude this edge case:
        else if (type.equals("Speaker")) {
            lp.displayAccountCreateInfo(false);
            return;
        }

        // Two additional constraints to username and password: no spaces and case-sensitivity to duplicates
        if (um.isDuplicate(userName)) {
            lp.printDuplicateName();
            lp.displayAccountCreateInfo(false);
            return;
        }
        else if (userName.contains(" ") || password.contains(" ")) {
            lp.printSpaceError();
            lp.displayAccountCreateInfo(false);
            return;
        }

        // below deals with any input other than the ones listed above
        try {
            this.um.createUser(userName, password, type);
            lp.displayAccountCreateInfo(true); // successfully created account
        }
        catch (IllegalArgumentException ex) {
            lp.printInputException(ex);
            lp.displayAccountCreateInfo(false); // unrecognized input or duplicate user
        }
    }

    /**
     * Overloaded createAccount to use in initial account creation, should NOT allow speakers
     */
    public void createAccount() {
        lp.inputName();
        String userName = scanner.nextLine();
        lp.inputPassword();
        String password = scanner.nextLine();
        lp.inputUserType();
        String type = scanner.nextLine();
        createAccount(userName, password, type);
    }

    /**
     * Logs in a user if the account information provided is correct
     * @param userName The user's username
     * @param password The user's password
     */
    public void login(String userName, String password) {
        boolean okay = um.canLogin(userName, password);
        lp.displayLoginInfo(okay);
        if(okay) {
            this.loggedInUser = userName;
            lp.displayLoginUser(loggedInUser);
        }
        else {
            this.loggedInUser = null;
        }
    }

    /**
     * Overloaded login that takes in user input from scanner, to be used in ConferenceSystem
     */
    public void login() {
        lp.inputName();
        String userName = scanner.nextLine();
        lp.inputPassword();
        String password = scanner.nextLine();
        this.login(userName, password);
    }

    /**
     * Prints a list of options for the start menu and takes in an option using a scanner.
     * @return The option entered
     */
    public String getStartMenu() {
        lp.printStartMenu();
        return scanner.nextLine();
    }

    /**
     * Prints a list of options for the Organizer's main menu and takes in an option using a scanner.
     * @return The option entered
     */
    public String getOrganizerMenu(){
        lp.printOrganizerMenu();
        return scanner.nextLine();
    }

    /**
     * Prints a list of options for the Speaker's main menu and takes in an option using a scanner.
     * @return The option entered
     */
    public String getSpeakerMenu(){
        lp.printSpeakerMenu();
        return scanner.nextLine();
    }

    /**
     * Prints a list of options for the Attendee's main menu and takes in an option using a scanner.
     * @return The option entered
     */
    public String getAttendeeMenu(){
        lp.printAttendeeMenu();
        return scanner.nextLine();
    }
    /**
     * Logs out any existing user
     */
    public void logout() {
        this.loggedInUser = null;
        lp.displayLogoutUser();
    }

    /**
     * Getter for the currently logged in user
     * @return a string of the user id if there is a user logged in, or null if there is not a logged in in user
     */
    public String getLoggedInUser() {
        return this.loggedInUser;
    }

    /**
     * Prints a message indicating an invalid option
     */
    public void invalidOption() {
        lp.printInvalidInput();
    }


    /**
     * Getter of user type by name
     * @param username - User name
     * @return String of user type
     */
    public String getUserType(String username){
        return um.getUserType(username);
    }
}
