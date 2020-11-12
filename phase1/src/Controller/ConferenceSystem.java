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
    private final EventManager eventManager = new EventManager();
    private final UserManager userManager = new UserManager();
    private MessageManager messageManager; // not final as this can be changed along with the logged in user

    public ConferenceSystem() {

    }

    // TODO
    public static void run() {

    }

}
