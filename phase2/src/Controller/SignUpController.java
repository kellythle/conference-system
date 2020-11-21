package Controller;

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
        sp.displayEventList(eventManager.getEventList(), eventManager);
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
        boolean validEventName = false;
        String eventName;
        do {
            do {
                Scanner scan1 = new Scanner(System.in);
                //prints the events
                this.getEventList();
                sp.printSignUpEventPrompt();
                eventName = scan1.nextLine();
                //back to Sign Up System Menu
                if (eventName.equals("0"))
                    return; //back to Sign Up System Menu
                if (!eventManager.getEvent(eventName)) {
                    sp.printInvalidEventName(); //invalid event name
                } else {
                    validEventName = true;
                }
            } while (!validEventName);
            if(eventManager.getEvent(eventName)){
                if(eventManager.addUserToEvent(username, eventName)){
                    userManager.addRegisteredEvent(username,eventName);
                    sp.printSignUpSuccess();
                } else{
                    sp.printSignUpFail();
                    validEventName = false;
                }
            }
        }while(!validEventName);
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
        boolean registered = false;
        String eventName;
        do {
            Scanner scan1 = new Scanner(System.in);
            //prints registered events
            this.getRegisteredEventList(username);
            sp.printDeleteEventPrompt();
            eventName = scan1.nextLine();
            //back to Sign Up System Menu
            if (eventName.equals("0"))
                return;
            //check if this user has registered for this event
            if (!userManager.getRegisteredEvents(username).contains(eventName)) {
                sp.printNotInRegisteredEvent();
            }else{
                registered = true;
            }
        }while (!registered);
        //check if this user can delete this event
        if (eventManager.deleteUserFromEvent(username, eventName)){
            userManager.getRegisteredEvents(username).remove(eventName);
            sp.printDeleteEventSuccess();
            return; //back to Sign Up System Menu
        }
        sp.printDeleteEventFail();
        //back to Sign Up System Menu
    }

    /**
     * Shows the message about ending the Sign Up System
     */
    public void signUpSystemEnd(){
        sp.printEndSignUpSystem();
    }

    /**
     * Shows the message about getting invalid input
     */
    public void InvalidInput(){
        sp.printInvalidInput();
    }
}