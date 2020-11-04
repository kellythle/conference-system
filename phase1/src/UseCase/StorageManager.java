package UseCase;

import java.util.ArrayList;
import java.util.HashMap;
import Entity.UserAccount;

/**
 * A class that stores a list of users.
 * @author Filip Jovanovic
 */
public class StorageManager {

    // TODO: Write javadoc
    public HashMap<String, UserAccount> userMap;

    public boolean containsUser(String username)
    {
        return userMap.containsKey(username);
    }

    public String getPassword(String username)
    {
        return userMap.get(username).getPassWord();
    }

    public void setPassword(String username, String password)
    {
        UserAccount user = userMap.get(username);
        user.setPassWord(password);
    }

    public ArrayList<String> getFriendList(String username)
    {
        return userMap.get(username).getFriendList();
    }

    public void addFriend(String username, String friendUsername)
    {
        UserAccount user = userMap.get(username);
        user.addFriend(friendUsername);
    }

    public ArrayList<Integer> getMessagesSent(String username)
    {
        return userMap.get(username).getMessageSent();
    }

    public void addMessageSent(String username, int messageId)
    {
        UserAccount user = userMap.get(username);
        user.addNewMessageSent(messageId);
    }

    public ArrayList<Integer> getMessagesReceived(String username)
    {
        return userMap.get(username).getMessageReceived();
    }

    public void addMessageReceived(String username, int messageId)
    {
        UserAccount user = userMap.get(username);
        user.addNewMessageReceived(messageId);
    }

    public ArrayList<String> getRegisteredEvents(String username)
    {
        return userMap.get(username).getRegisteredEvents();
    }

    public void addRegisteredEvent(String username, String eventName)
    {
        UserAccount user = userMap.get(username);
        user.registerEvent(eventName);
    }

    public boolean isOrganizer(String username)
    {
        return userMap.get(username).isOrganizer();
    }

    public boolean isSpeaker(String username)
    {
        return userMap.get(username).isSpeaker();
    }
}
