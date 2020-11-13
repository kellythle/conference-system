package Controller;

import UseCase.EventManager;
import UseCase.MessageManager;
import UseCase.UserManager;

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
    // Gateways read in userMap, eventList, roomList, and MessagesMap
    // After gateways are done, please initialize ^^^ into the corresponding classes.
    // Use case class instances
    private final EventManager eventManager = new EventManager();
    private final UserManager userManager = new UserManager();
    private MessageManager messageManager; // not final as this can be changed along with the logged in user

    // Controller class instances
    private final LoginController loginController = new LoginController(userManager);
    private final SignUpController signUpController = new SignUpController(eventManager, userManager);
    private final ScheduleController scheduleController = new ScheduleController(eventManager, userManager);
    // MessageCs needs parameter username, maybe initialize after login in.
    private MessageController messageController;
    private OrganizerMessageController organizerMessageController;
    private SpeakerMessageController speakerMessageController;

    // Variable for keeping track with user, should be initialized after login.
    String username;

    public ConferenceSystem() {}

    // TODO
    public void run() {

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
        do{
            menuOption = signUpController.getMenu();
            switch (menuOption){
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
                    System.out.println("End of Sign Up System.");
                    break;
                default:
                    System.out.println("Invalid input,please try again.");
            }
        } while (!menuOption.equals("5"));
    }

}
