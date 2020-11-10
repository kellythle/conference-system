package Controller;

import Entity.Event;
import UseCase.EventManager;
import UseCase.MessageManager;
import UseCase.UserManager;

public class SpeakerMessageController {
    private MessageManager myMessageManager;
    private UserManager myUserManager;
    private EventManager myEventManager;
    private String username;

    public SpeakerMessageController(String username, UserManager myUserManager, EventManager myEventManager){
        myMessageManager = new MessageManager(username);
        this.myUserManager = myUserManager;
        this.myEventManager = myEventManager;
        this.username = username;
    }

    /**
     *
     *
     * @param messageContent
     * @return true if message created
     */
    public boolean sendAllMessageAllEvent(String messageContent) {
        for (Event event : myEventManager.getEventList()) {
            if (event.getSpeaker().equals(username)) {
                for (String userName : event.getAttendees()) {
                    myMessageManager.createMessage(userName, messageContent);
                }
            }
        }
        return !myUserManager.getSpeakerList().isEmpty();
    }


    }


