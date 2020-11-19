package Controller;

import Gateway.ReadWriteGateway;
import UseCase.EventManager;
import UseCase.MessageManager;
import UseCase.UserManager;
import javafx.util.Pair;

import java.io.*;

import java.util.ArrayList;

/**
 * This is the main controller class that contains all the instances of the Managers and Controllers
 *
 * This class should only be initiated once in the main/UI method.
 *
 * Example for main() {
 *     Convention con = new Convention();
 *     con.run();
 * }
 */
public class ConferenceSystem {
    // Use case class instances
    private EventManager eventManager = new EventManager();
    private UserManager userManager = new UserManager();
    private MessageManager messageManager = new MessageManager();

    // Gateway class instance
    private ReadWriteGateway readWriteGateway = new ReadWriteGateway();

    // Controller class instances
    private LoginController loginController;
    private SignUpController signUpController;
    private ScheduleController scheduleController;
    private MessageController messageController;
    private OrganizerMessageController organizerMessageController;
    private SpeakerMessageController speakerMessageController;

    // File paths
    private final String usersPath = "./phase1/src/users.ser";
    private final String eventsPath = "./phase1/src/events.ser";
    private final String messagesPath = "./phase1/src/messages.ser";

    // Variable for keeping track with user, should be initialized after login.
    String username;

    public ConferenceSystem() {username = null;}

    public void run() {
        readData();

        // After reading in Use Cases from file, instantiate controllers that do not require
        // the username of the logged in person to be instantiated.
        loginController = new LoginController(userManager);
        signUpController = new SignUpController(eventManager, userManager);
        scheduleController = new ScheduleController(eventManager, userManager);

        // add default rooms
        if (eventManager.getRoomList() == null || eventManager.getRoomList().isEmpty()) {
            Pair<Integer, Integer> room1= new Pair<>(1, 2);
            Pair<Integer, Integer> room2= new Pair<>(2, 2);
            Pair<Integer, Integer> room3= new Pair<>(3, 2);
            ArrayList<Pair<Integer, Integer>> rooms = new ArrayList<>();
            rooms.add(room1);
            rooms.add(room2);
            rooms.add(room3);
            eventManager.setRoomList(rooms);
        }

        //Menu for sign in or register
        initialLoginHelper();
        messageManager.setSenderID(username);

        //initialize all classes that need the username
        messageController = new MessageController(username, userManager, messageManager);
        organizerMessageController = new OrganizerMessageController(username, messageManager, userManager);
        speakerMessageController = new SpeakerMessageController(username, messageManager, userManager, eventManager);
        //2. shows Main menu for each user
        switch (loginController.getUserType(username)){
            case "Organizer":
                organizerHelper();
                break;
            case "Speaker":
                speakerHelper();
                break;
            case "Attendee":
                attendeeHelper();
                break;
        }
        //3. return if the user logout. Write out data to files.
        writeData();
    }

    /**
     * Reads in all user, event, and message data from the files.
     *
     * @return Boolean indicating if reading was successful.
     */
    public boolean readData() {
        try {
            File um = new File(usersPath);
            File em = new File(eventsPath);
            File mm = new File(messagesPath);

            um.createNewFile();
            em.createNewFile();
            mm.createNewFile();

            if (um.length() != 0) {
                userManager = (UserManager)readWriteGateway.readFromFile(usersPath);
            }
            if (em.length() != 0) {
                eventManager = (EventManager)readWriteGateway.readFromFile(eventsPath);
            }
            if (mm.length() != 0) {
                messageManager = (MessageManager)readWriteGateway.readFromFile(messagesPath);
            }
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Writes all data to the respective files.
     *
     * @return Boolean indicating if reading was successful.
     */
    private boolean writeData()
    {
        try {
            readWriteGateway.saveToFile(usersPath, userManager);
            readWriteGateway.saveToFile(eventsPath, eventManager);
            readWriteGateway.saveToFile(messagesPath, messageManager);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Helper method at the start that deals with login and account creation
     */
    private void initialLoginHelper() {
        String loginMenuOption;
        do {
            loginMenuOption = loginController.getStartMenu();
            switch (loginMenuOption) {
                case "1":
                    loginController.login();
                    this.username = loginController.getLoggedInUser();
                    break;
                case "2":
                    loginController.createAccount();
                    this.username = loginController.getLoggedInUser();
                    break;
                default:
                    loginController.invalidOption();
            }
        } while (loginController.getLoggedInUser() == null);
    }

    /**
     * organizer menu helper class
     */
    private void organizerHelper(){
        String menuOption;
        do{
            // Organizer start menu
            menuOption = loginController.getOrganizerMenu();
            switch (menuOption) {
                case "1":
                    signUpHelper();
                    break;
                case "2":
                    scheduleHelper();
                    break;
                case "3":
                    organizerMessageHelper();
                    break;
                case "4":
                    loginController.logout();
                    break;
                default:
                    loginController.invalidOption();
            }
        } while (!menuOption.equals("4"));
    }

    /**
     * Speaker menu helper class
     */
    private void speakerHelper(){
        String menuOption;
        do{//Speaker start menu
            menuOption = loginController.getSpeakerMenu();
            switch (menuOption) {
                case "1":
                    speakerMessageHelper();
                    break;
                case "2":
                    loginController.logout();
                    break;
                default:
                    loginController.invalidOption();
            }
        } while (!menuOption.equals("2"));
    }

    /**
     * Attendee menu helper class
     */
    private void attendeeHelper(){
        String menuOption;
        do{
            // Attendee start menu
            menuOption = loginController.getAttendeeMenu();
            switch (menuOption) {
                case "1":
                    signUpHelper();
                    break;
                case "2":
                    attendeeMessageHelper();
                    break;
                case "3":
                    loginController.logout();
                    break;
                default:
                    loginController.invalidOption();
            }
        } while (!menuOption.equals("3"));
    }

    /**
     * Helper method that deals with signing up, deleting events.
     * First, it shows a menu of Sign Up System.
     * User can input a number to do either signing up for event,
     * deleting registered event, see events, see registered events,
     * and exist Sign Up System to main menu.
     */
    private void signUpHelper(){
        String menuOption;
        do{// Sign Up System Menu
            menuOption = signUpController.getMenu();
            switch (menuOption) {
                case "1":
                    signUpController.signUpEvent(username);
                    break;
                case "2":
                    signUpController.deleteEvent(username);
                    break;
                case "3":
                    signUpController.getEventList();
                    break;
                case "4":
                    signUpController.getRegisteredEventList(username);
                    break;
                case "5":
                    signUpController.signUpSystemEnd();
                    break;
                default:
                    signUpController.InvalidInput();
            }
        } while (!menuOption.equals("5"));
    }

    /**
     * Helper method that allows Organizers to either create events, create speakers, or exit this menu by inputting
     * a specific number.
     */
    private void scheduleHelper(){
        String scheduleMenuOption;
        do{
            scheduleMenuOption = scheduleController.getScheduleMenu();
            switch (scheduleMenuOption) {
                case "1":
                    scheduleController.createEvent();
                    break;
                case "2":
                    scheduleController.addNewSpeaker();
                    break;
                case "3":
                    scheduleController.endScheduling();
                    break;
                default:
                    scheduleController.failScheduleMenu();
            }
        } while (!scheduleMenuOption.equals("3"));
    }

    /**
     * Helper method that allows Organizers to either send message to a single attendee or speaker, or all speakers,
     * or all attendees, or exit this menu by inputting  a specific number.
     */
    private void organizerMessageHelper() {
        String organizerMessageMenuOption;
        do {
            organizerMessageMenuOption = organizerMessageController.getMessageMenu();
            switch (organizerMessageMenuOption) {
                case "0":
                    break;
                case "1":
                    organizerMessageController.viewConversations();
                    break;
                case "2":
                    organizerMessageController.sendMessage();
                    break;
                case "3":
                    organizerMessageController.sendMessagesToSpeakers();
                    break;
                case "4":
                    organizerMessageController.sendMessagesToAttendees();
                    break;
                default:
                    organizerMessageController.invalidInput();
            }
        } while (!organizerMessageMenuOption.equals("0"));
    }

    /**
     * Helper method that allows Speakers to either send message to all attendees of their one event or all events,
     * or reply to an attendee, or exit this menu by inputting  a specific number.
     */
    private void speakerMessageHelper(){
        String speakerMessageMenuOption;
        do{
            speakerMessageMenuOption = speakerMessageController.getMessageMenu();
            switch (speakerMessageMenuOption){
                case "0":
                    break;
                case "1":
                    speakerMessageController.viewConversations();
                    break;
                case "2":
                    speakerMessageController.sendMessage();
                    break;
                default:
                    speakerMessageController.invalidInput();
            }
        } while (!speakerMessageMenuOption.equals("0"));
    }

    /**
     * Helper method that allows Attendees to either send a message to another Attendee or Speaker,
     * or view conversation, or exit this menu by inputting  a specific number.
     */
    private void attendeeMessageHelper(){
        String attendeeMessageMenuOption;
        do{
            attendeeMessageMenuOption = messageController.getMessageMenu();
            switch (attendeeMessageMenuOption){
                case "0":
                    break;
                case "1":
                    messageController.viewConversations();
                    break;
                case "2":
                    messageController.sendMessage();
                    break;
                default:
                    messageController.invalidInput();
            }
        } while (!attendeeMessageMenuOption.equals("0"));
    }



}
