package Presenter;


public class LoginPresenter {

    public void printStartMenu(){
        System.out.println("Welcome to the Start Menu! \n" +
                "Options:\n" +
                "1. Sign in\n" +
                "2. Register\n" +
                "Enter 1 or 2: ");
    }

    public void printEndMenu(){
        System.out.println("This is the Log Out Menu! \n" +
                "Options:\n" +
                "1. Log out \n" +
                "2. Stay logged in\n" +
                "Enter 1 or 2: ");
    }

    public void inputName(){
        System.out.println("Enter your unique username: ");
    }

    public void inputPassword(){
        System.out.println("Enter your password: ");
    }

    public void inputUserType(){
        System.out.println("Enter the type of user you wish to create (Attendee or Organizer): ");
    }

    public void printInvalidInput() {
        System.out.println("Invalid input, please try again.");
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
     * display information when a user is logging in
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
     * display information about current login user
     * @param info- user name got from login controller
     */
    public void displayLoginUser(String info){
        System.out.println("Current user is " + info + ".");
    }

    public void displayLogoutUser(){
        System.out.println("Logged out successfully!");
    }

    public void printOrganizerMenu(){
        System.out.println("Welcome to the Organizer Menu! \n" +
                "Options:\n" +
                "1. Sign up\n" +
                "2. Create a New Event/Speaker\n" +
                "3. Send a message\n"+
                "4. Log out\n"+
                "Enter 1, 2, 3, or 4: ");
    }

    public void printSpeakerMenu(){
        System.out.println("Welcome to the Speaker Menu! \n" +
                "Options:\n" +
                "1. Send a message\n"+
                "2. Log out\n"+
                "Enter 1 or 2: ");
    }

    public void printAttendeeMenu(){
        System.out.println("Welcome to the Attendee Menu! \n" +
                "Options:\n" +
                "1. Sign up\n" +
                "2. Send a message\n"+
                "3. Log out\n"+
                "Enter 1, 2, or 3: ");
    }
}

