package Presenter;

/**
 * A presenter that prints different prompts and messages to the UI so that the login system can function as needed
 */
public class LoginPresenter {
    /**
     * This prints the start menu, before anyone has signed in.
     */
    public void printStartMenu(){
        System.out.println("Welcome to the Start Menu! \n" +
                "Options:\n" +
                "1. Sign in\n" +
                "2. Register\n" +
                "Enter 1 or 2: ");
    }

    /**
     * Prompts the client to enter a username.
     */
    public void inputName(){
        System.out.println("Enter your unique username: ");
    }

    /**
     * Prompts the client to enter a password.
     */
    public void inputPassword(){
        System.out.println("Enter your password: ");
    }

    /**
     * Prompts the client to enter a type of user. (Should only be Attendee or Organizer)
     */
    public void inputUserType(){
        System.out.println("Enter the type of user you wish to create (Attendee or Organizer): ");
    }

    /**
     * Prints the error message: "Sorry this username is already taken."
     */
    public void printDuplicateName() {
        System.out.println("Sorry this username is already taken.");
    }

    /**
     * Prints the error message: "Sorry, your username and password cannot contain spaces."
     */
    public void printSpaceError() {
        System.out.println("Sorry, your username and password cannot contain spaces.");
    }
    /**
     * Displays a error message by printing "Invalid input, please try again."
     */
    public void printInvalidInput() {
        System.out.println("Invalid input, please try again.");
    }

    /**
     * Displays an error in the account creation process, depending on the error message of the thrown exception
     * @param exception The exception that was thrown and caught
     */
    public void printInputException(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }

    /**
     * display information when an account is created
     * @param value- boolean value got from login controller
     */
    public void displayAccountCreateInfo(boolean value){
        if (value){
            System.out.println("The user account was created successfully!");
        }
        else
            System.out.println("Failed to create account, please try again.");
    }

    /**
     * Display information when a user is logging in
     * @param value- boolean value got from login controller
     */
    public void displayLoginInfo(boolean value){

        if (value){
            System.out.println("Correct Password.");
        }
        else {
            System.out.println("Incorrect username or password.");
        }
    }

    /**
     * Display information about current login user
     * @param info- user name got from login controller
     */
    public void displayLoginUser(String info){
        System.out.println("Current user is " + info + ".");
    }

    /**
     * Display a successful log gout message.
     */
    public void displayLogoutUser(){
        System.out.println("Logged out successfully!");
    }

    /**
     * Prints out the main menu for the Organizer. This contains all the actions that an Organizer can take.
     */
    public void printOrganizerMenu(){
        System.out.println("Welcome to the Organizer Menu! \n" +
                "Options:\n" +
                "1. Sign up\n" +
                "2. Create a New Event/Speaker\n" +
                "3. Send a message\n"+
                "4. Log out\n"+
                "Enter 1, 2, 3, or 4: ");
    }

    /**
     * Prints out the main menu for the Speaker. This contains all the actions that a Speaker can take.
     */
    public void printSpeakerMenu(){
        System.out.println("Welcome to the Speaker Menu! \n" +
                "Options:\n" +
                "1. Send a message\n"+
                "2. Log out\n"+
                "Enter 1 or 2: ");
    }

    /**
     * Prints out the main menu for the Attendee. This contains all the actions that an Attendee can take.
     */
    public void printAttendeeMenu(){
        System.out.println("Welcome to the Attendee Menu! \n" +
                "Options:\n" +
                "1. Sign up\n" +
                "2. Send a message\n"+
                "3. Log out\n"+
                "Enter 1, 2, or 3: ");
    }
}

