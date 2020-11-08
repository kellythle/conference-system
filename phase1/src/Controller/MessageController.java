package Controller;

import UseCase.MessageManager;
import UseCase.UserManager;

public class MessageController {
    private MessageManager myMessageManager;
    private UserManager myUserManager;
    private String username;

    public MessageController(String username, UserManager myUserManager){
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

    public boolean replyToMessage(int messageID, String messageContent){
        if (myMessageManager.getMessageReceiver(messageID) == username) {
            return sendSingleMessage(myMessageManager.getMessageSender(messageID), messageContent);
        } else { return false; }
    }

}
