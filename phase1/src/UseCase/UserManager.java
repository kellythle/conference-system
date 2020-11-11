package UseCase;

import Entity.UserAccount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * A Class that manages UserAccounts by creating new accounts and checking login information
 */
public class UserManager {

    private HashMap<String, UserAccount> userMap;

    /**
     * Constructor of UserManager
     */
    public UserManager() {}

    /**
     * Creates a new user, given the type of user and account information.
     *
     * @param userName The new user's username, *cannot be a duplicate within the system
     * @param password The new user's password
     * @param type The type of user added, can only be 3 strings: "Attendee", "Speaker",
     *             or ""Organizer"
     * @throws IllegalArgumentException when a type is unrecognized
     */
    public void createUser(String userName, String password, String type) {
        if (userMap.containsKey(userName)){
            throw new IllegalArgumentException("Duplicate name");
        }
        switch (type) {
            case "Attendee":
                userMap.put(userName, new AttendeeFactory().createAccount(userName, password));
            case "Speaker":
                userMap.put(userName, new SpeakerFactory().createAccount(userName, password));
            case "Organizer":
                userMap.put(userName, new OrganizerFactory().createAccount(userName, password));
            default:
                throw new IllegalArgumentException("Unknown type of user");
        }
    }

    /**
     * Gets the HashMap of all UserAccounts.
     *
     * @return the user HashMap with usernames as keys and UserAccount objects as values
     */
    public HashMap<String, UserAccount> getUserMap() {
        return userMap;
    }

    /**
     * Sets the HashMap of all UserAccounts.
     *
     * @param userMap - Hashmap with usernames as keys and UserAccount objects as values
     *
     */
    public void setUserMap(HashMap<String, UserAccount> userMap) {
        this.userMap = userMap;
    }

    /**
     *
     * @param username - name of the user
     * @return the user account got by name search
     */
    public UserAccount getUserByName(String username){
        return userMap.get(username);
    }

    /**
     * Returns true is the user exists.
     *
     * @param username - the username of this user
     * @return true if the user exists
     */
    public boolean containsUser(String username)
    {
        return userMap.containsKey(username);
    }

    /**
     * Returns the password of this user.
     *
     * @param username - the username of this user
     * @return a String of this user's password
     */
    public String getPassword(String username)
    {
        return userMap.get(username).getPassWord();
    }

    /**
     * Sets the password of this user's account.
     *
     * @param username - the username of this user
     * @param password - the password wanted to be set
     */
    public void setPassword(String username, String password)
    {
        UserAccount user = userMap.get(username);
        user.setPassWord(password);
    }

    /**
     * Returns the friend list of this user.
     *
     * @param username - the username of this user
     * @return the friend list of the user
     */
    public ArrayList<String> getFriendList(String username)
    {
        return userMap.get(username).getFriendList();
    }

    /**
     * Adds one username to the user's friend list.
     *
     * @param username - the username of this user
     * @param friendUsername - the username that this user want to add to its friend list.
     */
    public void addFriend(String username, String friendUsername)
    {
        UserAccount user = userMap.get(username);
        user.addFriend(friendUsername);
    }

    /**
     * Returns an arraylist of message IDs this user sent.
     *
     * @param username - the username of this user
     * @return an ArrayList of message ids
     */
    public ArrayList<Integer> getMessagesSent(String username)
    {
        return userMap.get(username).getMessageSent();
    }

    /**
     * Adds a message id to the list of sent message ids of this user.
     *
     * @param username - the username of this user
     * @param messageId - the id of this message
     */
    public void addMessageSent(String username, int messageId)
    {
        UserAccount user = userMap.get(username);
        user.addNewMessageSent(messageId);
    }

    /**
     * Returns an arraylist of message ids this user received.
     *
     * @param username - the username of this user
     * @return an ArrayList of message ids
     */
    public ArrayList<Integer> getMessagesReceived(String username)
    {
        return userMap.get(username).getMessageReceived();
    }

    /**
     * Adds a message id to the list of received message ids of this user.
     *
     * @param username - the username of this user
     * @param messageId - the id of this message
     */
    public void addMessageReceived(String username, int messageId)
    {
        UserAccount user = userMap.get(username);
        user.addNewMessageReceived(messageId);
    }

    /**
     * Returns a arraylist of event names this user registered.
     *
     * @param username - the username of this user
     * @return - an Arraylist of event names
     */
    public ArrayList<String> getRegisteredEvents(String username)
    {
        return userMap.get(username).getRegisteredEvents();
    }

    /**
     * Adds this event's name to this user's registered event list.
     *
     * @param username - the username of this user
     * @param eventName - the name of this event
     */
    public void addRegisteredEvent(String username, String eventName)
    {
        UserAccount user = userMap.get(username);
        user.registerEvent(eventName);
    }

    /**
     * Returns true if this user is an organizer.
     *
     * @param username - the username of this user
     * @return true if this user is an Organizer
     */
    public boolean isOrganizer(String username)
    {
        return userMap.get(username).isOrganizer();
    }

    /**
     * Returns true if this user is a speaker.
     *
     * @param username - the username of this user
     * @return true if this user is a Speaker
     */
    public boolean isSpeaker(String username)
    {
        return userMap.get(username).isSpeaker();
    }

    /**
     * Return a list of speakers' usernames.
     *
     * @return an ArrayList of String representing the usernames of speakers
     */
    public ArrayList<String> getSpeakerList(){
        Set<String> usernames = userMap.keySet();
        ArrayList<String> speakers = new ArrayList<>();
        for(int i = 0; i < usernames.size(); i++){
            if (userMap.get(usernames.toArray()[i]).isSpeaker())
            speakers.add((String)usernames.toArray()[i]);
        }
        return speakers;
    }
    /**
     * Return a list of attendees' usernames.
     *
     * @return an ArrayList of String representing the usernames of speakers
     */
    public ArrayList<String> getAttendeeList(){
        Set<String> usernames = userMap.keySet();
        ArrayList<String> attendees = new ArrayList<>();
        for(int i = 0; i < usernames.size(); i++){
            if (!userMap.get(usernames.toArray()[i]).isSpeaker() && !userMap.get(usernames.toArray()[i]).isOrganizer())
                attendees.add((String)usernames.toArray()[i]);
        }
        return attendees;
    }
    /**
     * Checks whether or not a person can login with given account information.
     *
     * @param userName The userName id of the user
     * @param password The password of the user
     * @return true if the id is in the system and the password matches, false otherwise
     */
    public boolean canLogin(String userName, String password) { // login checker
        if (!idChecker(userName)) {
            return false;
        }
        return getPassword(userName).equals(password);
    }

    /**
     * This checks if an id is in the list of users or not
     *
     * @param id the userName / id to be checked
     * @return true if this id is in the list
     */
    public boolean idChecker(String id) {
        return containsUser(id);
    }

    public boolean canSend(String username, String receiver){
        if (isOrganizer(username)){
            return true;
        } else if (isSpeaker(username)){
            if (isOrganizer(receiver)||isSpeaker(receiver)) {return false;}
            return true;
        } else{
            if (isOrganizer(receiver)) {return false;}
            return true;
        }
    }
}

