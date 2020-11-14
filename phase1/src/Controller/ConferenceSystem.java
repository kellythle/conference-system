package Controller;

import Entity.Event;
import Entity.Message;
import Entity.UserAccount;
import Gateway.ReadWriteGateway;
import UseCase.EventManager;
import UseCase.MessageManager;
import UseCase.UserManager;
import javafx.util.Pair;

import java.io.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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
    // (1) Filip: Gateways read in userMap, eventList, roomList, and MessagesMap
    // After gateways are done, please initialize ^^^ into the corresponding classes.
    // Use case class instances
    private final EventManager eventManager = new EventManager();
    private final UserManager userManager = new UserManager();
    private MessageManager messageManager; // not final as this can be changed along with the logged in user

    private ReadWriteGateway readWriteGateway = new ReadWriteGateway();

    // Controller class instances
    private final LoginController loginController = new LoginController(userManager);
    private final SignUpController signUpController = new SignUpController(eventManager, userManager);
    private final ScheduleController scheduleController = new ScheduleController(eventManager, userManager);
    // MessageCs needs parameter username, maybe initialize after login in.
    private MessageController messageController;
    private OrganizerMessageController organizerMessageController;
    private SpeakerMessageController speakerMessageController;

    // File paths
    private final String usersPath = "../../users.ser";
    private final String eventsPath = "../../events.ser";
    private final String messagesPath = "../../messages.ser";
    private final String roomsPath = "../../rooms.ser";

    // Variable for keeping track with user, should be initialized after login.
    String username;

    public ConferenceSystem() {username = null;}


    public void run() {
        //William and Richard vvvvv
        //1. calls LoginController >>> register or login menu
        initialLoginHelper();
        //initialize all classes that need the username
        messageController = new MessageController(username, userManager);
        organizerMessageController = new OrganizerMessageController(username, userManager);
        speakerMessageController = new SpeakerMessageController(username, userManager, eventManager);
        //2. shows Main menu >>> sign up or (schedule) or message
        // after login (register), show the corresponding menu >>> for attendee, organizer, speaker
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
        //3. return if the user logout.
        }

    /**
     * Reads in all user, event, and message data from the files.
     *
     * @return Boolean indicating if reading was successful.
     * @throws ClassNotFoundException if class type of data read in is not defined in program.
     */
    public boolean readData() throws ClassNotFoundException {
        try {
            File users = new File(usersPath);
            File events = new File(eventsPath);
            File messages = new File(messagesPath);
            File rooms = new File(roomsPath);

            users.createNewFile();
            events.createNewFile();
            messages.createNewFile();
            rooms.createNewFile();

            HashMap<String, UserAccount> userMap = (HashMap<String, UserAccount>)
                    readWriteGateway.readFromFile(usersPath);
            ArrayList<Event> eventList = (ArrayList<Event>)
                    readWriteGateway.readFromFile(eventsPath);
            HashMap<Integer, Message> messageMap = (HashMap<Integer, Message>)
                    readWriteGateway.readFromFile(messagesPath);
            ArrayList<Pair<Integer, Integer>> roomList = (ArrayList<Pair<Integer, Integer>>)
                    readWriteGateway.readFromFile(roomsPath);

            userManager.setUserMap(userMap);
            eventManager.setEventList(eventList);
            messageManager.setSystemMessages(messageMap);
            eventManager.setRoomList(roomList);

        } catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * Writes all user, event, and message data to their respective files.
     *
     * @return Boolean indicating if reading was successful.
     */
    private boolean writeData()
    {
        HashMap<String, UserAccount> userMap = userManager.getUserMap();
        ArrayList<Event> eventList = eventManager.getEventList();
        HashMap<Integer, Message> messageMap = messageManager.getSystemMessages();
        ArrayList<Pair<Integer, Integer>> roomList = eventManager.getRoomList();

        try {
            readWriteGateway.saveToFile(usersPath, userMap);
            readWriteGateway.saveToFile(eventsPath, eventList);
            readWriteGateway.saveToFile(messagesPath, messageMap);
            readWriteGateway.saveToFile(roomsPath, roomList);
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
                    organizerMessageHelper();
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
        } while (!organizerMessageMenuOption.equals("4"));
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
        } while (!speakerMessageMenuOption.equals("2"));
    }

    /**
     * Helper method that allows Speakers to either send message to all attendees of their one event or all events,
     * or reply to an attendee, or exit this menu by inputting  a specific number.
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
        } while (!attendeeMessageMenuOption.equals("2"));
    }



}
