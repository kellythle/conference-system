package Controller;
import UseCase.UserManager;

/**
 * A controller that deals with account creation and login information
 *
 * @author Chaolin Wang
 */
public class LoginController {
    private String loggedInUser;
    private final UserManager um;

    /**
     * A constructor for Login controller.
     * Takes in a MangerFacade, call the getter for userManager
     * @param managerFacade The ManagerFacade object where we get the managers from
     */
    public LoginController(ManagerFacade managerFacade) {
        this.loggedInUser = null;
        this.um = managerFacade.getUserManager();
    }

    /**
     * Creates a new user, given the type of user and account information.
     *
     * @param userName The new user's username, *cannot be a duplicate within the system
     * @param password The new user's password
     * @param type The type of user added, can only be 3 strings: "Attendee", "Speaker",
     *             or ""Organizer"
     * @throws IllegalArgumentException when a type is unrecognized
     */
    public void createAccount(String userName, String password, String type) {
        this.um.createUser(userName, password, type);
    }

    /**
     * Logs in a user if the account information provided is correct
     * @param userName The user's username
     * @param password The user's password
     * @return true if user successfully logged in, false otherwise
     */
    public boolean login(String userName, String password) {
        if(um.canLogin(userName, password)) {
            this.loggedInUser = userName;
            return true;
        }
        else {
            this.loggedInUser = null;
            return false;
        }
    }

    /**
     * Logs out any existing user
     */
    public void logout() {
        this.loggedInUser = null;
    }

    /**
     * Getter for the currently logged in user
     * @return a string of the user id if there is a user logged in, or null if there is not a logged in in user
     */
    public String getLoggedInUser() {
        return this.loggedInUser;
    }
}
