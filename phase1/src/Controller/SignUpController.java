package Controller;

import Entity.Event;
import Presenter.SignUpPresenter;
import UseCase.EventManager;
import UseCase.UserManager;

import java.util.Scanner;

/**
 * A controller class that calls EventManager and UserManager
 * to manage any command related to signing up events.
 * And calls SignUpPresenter's printing methods to show information
 * to the user.
 *
 * @author An Yen, Kelly Le, Filip Jovanovic
 */
public class SignUpController {
    private final EventManager eventManager;
    private final UserManager userManager;
    private final SignUpPresenter sp = new SignUpPresenter();

    /**
     * Creates a instance of ScheduleController with EventManager and UserManager as parameters.
     * @param em - EventManager instance
     * @param um - UserManager instance
     */
    public SignUpController(EventManager em, UserManager um){
        this.eventManager = em;
        this.userManager = um;
    }

    /**
     * Calls SignUpPresenter to print out the menu of Sign Up System.
     * Gets the input of the user and returns a string of the option
     * the user entered.
     *
     * @return - String, option the user entered.
     */
    public String getMenu(){
        Scanner scan = new Scanner(System.in);
        sp.printMenu();
        return scan.nextLine();
    }


    /**
     * Calls SignUpPresenter to print out all events.
     */
    public void getEventList(){
        sp.displayEventList(eventManager.getEventList());
    }

    /**
     * Calls SignUpPresenter to print out the registered events
     * of this user.
     * @param username - the user's username
     */
    public void getRegisteredEventList(String username){
        sp.displayRegisteredEvents(userManager.getRegisteredEvents(username), eventManager);
    }

    /**
     * Calls SignUpPresenter to print out all events, then asks the user
     * to enter the name of the event he/she wanted to sign up for,
     * or enter 0 if don't want to sign up any event.
     * After receiving the input from user, SignUpPresenter will be called
     * to print the sign up result on the UI.
     *
     * @param username - the user's username
     */
    public void signUpEvent(String username){
        Scanner scan1 = new Scanner(System.in);
        //prints the events
        this.getEventList();
        String eventName = scan1.nextLine();
        //back to Sign Up System Menu
        if (eventName.equals("0"))
                return; //back to Sign Up System Menu
        for (Event e: eventManager.getEventList()){
            //check is the input is a valid event name
            if (e.getName().equals(eventName)){
                //check if this user can sign up for this event
                if (eventManager.addUserToEvent(username, e)) {
                    userManager.addRegisteredEvent(username, e.getName());
                    sp.printSignUpSuccess();
                    return; //back to Sign Up System Menu
                }
                else{
                    sp.printSignUpFail();
                    return; //back to Sign Up System Menu
                }
            }
        }
        sp.printInvalidInput();
        //back to Sign Up System Menu
    }

    /**
     * Calls SignUpPresenter to print out registered events of this user,
     * then asks the user to enter the name of the event he/she wanted to delete
     * from the registered events, or enter 0 if don't want to delete any event.
     * After receiving the input from user, SignUpPresenter will be called to print
     * the deletion result on the UI.
     *
     * @param username - the user's username
     */
    public void deleteEvent(String username) {
        Scanner scan1 = new Scanner(System.in);
        //prints registered events
        this.getRegisteredEventList(username);
        String eventName = scan1.nextLine();
        //back to Sign Up System Menu
        if (eventName.equals("0"))
            return;
        //check if this user has registered for this event
        if (!userManager.getRegisteredEvents(username).contains(eventName)){
            sp.printNotInRegisteredEvent();
            return; //back to Sign Up System Menu
        }
        //check if this user can delete this event
        if (eventManager.deleteUserFromEvent(username, eventName)){
            userManager.getRegisteredEvents(username).remove(eventName);
            sp.printDeleteEventSuccess();
            return; //back to Sign Up System Menu
        }
        sp.printDeleteEventFail();
        //back to Sign Up System Menu
    }

    public void signUpSystemEnd(){
        sp.printEndSignUpSystem();
    }

    public void InvalidInput(){
        sp.printInvalidInput();
    }
}
