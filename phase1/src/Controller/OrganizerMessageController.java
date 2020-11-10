package Controller;

import UseCase.MessageManager;
import UseCase.UserManager;

public class OrganizerMessageController {
    private MessageManager myMessageManager;
    private UserManager myUserManager;
    private String username;

    public OrganizerMessageController(String username, UserManager myUserManager){
        myMessageManager = new MessageManager(username);
        this.myUserManager = myUserManager;
        this.username = username;
    }

    /**
     *
     * @param receiverID
     * @param messageContent
     * @return true if message created
     */
    public boolean sendSingleMessage(String receiverID, String messageContent){
        if (myUserManager.canSend(username, receiverID)){
            return myMessageManager.createMessage(receiverID, messageContent);
        } else {return false;}
    }


    /**
     *
     *
     * @param messageContent
     * @return true if message created
     */
    public boolean sendAllSpeakersMessage(String messageContent){
        for (String userName: myUserManager.getSpeakerList()) {
            myMessageManager.createMessage(userName, messageContent);
        }
        return !myUserManager.getSpeakerList().isEmpty();
    }


    /**
     *
     * @param receiverID
     * @param messageContent
     * @return true if message created
     */
    public boolean sendAllAttendeesMessage(String receiverID, String messageContent){
        for (String userName: myUserManager.getAttendeeList()) {
            myMessageManager.createMessage(receiverID, messageContent);
        }
        return !myUserManager.getAttendeeList().isEmpty();
    }
}
