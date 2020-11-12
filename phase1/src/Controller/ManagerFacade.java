package Controller;

import UseCase.EventManager;
import UseCase.MessageManager;
import UseCase.UserManager;

/**
 * This is a facade class that contains all the single instances of the Managers
 *
 * This class should only be initiated once in the main/UI method. And all the other Controllers/Gateways/presenters
 * should pass an object of this class into their constructor. From this, the other classes can call getter to set their
 * own manager fields.
 *
 * Example for main() {
 *     ManagerFacade mf = new ManagerFacade
 *
 *     LoginController logCon = new LoginController(mf);
 *     ScheduleController schCon = new ScheduleController(mf);
 *     SignUpController signCon = new SignUpController(mf);
 * }
 */
public class ManagerFacade {
    private final EventManager eventManager = new EventManager();
    private final UserManager userManager = new UserManager();
    private MessageManager messageManager; // not final as this can be changed along with the logged in user

    // ???????????
    public ManagerFacade(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

    public void setMessageManager(MessageManager messageManager) {
        this.messageManager = messageManager;
    }
}
