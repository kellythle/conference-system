package UseCase;


import Entity.UserAccount;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * A Class that manages UserAccounts by creating new accounts and checking login information
 */
public class UserManager implements Serializable {

    private HashMap<String, UserAccount> userMap = new HashMap<>();


    /**
     * Creates a new user, given the type of user and account information.
     *
     * @param userName The new user's username, *cannot be a duplicate within the system
     * @param password The new user's password
     * @param type The type of user added, can only be 3 strings: "Attendee", "Speaker",
     *             or ""Organizer"
     * @throws IllegalArgumentException is thrown when any input doesn't meet the requirements
     */
    public void createUser(String userName, String password, String type) throws IllegalArgumentException {
        // Makes sure the strings passed in are not null or empty
        if(userName == null || password == null){throw new IllegalArgumentException("Invalid inputs: null");}
        else if(userName.isEmpty() || password.isEmpty()){throw new IllegalArgumentException("Invalid inputs: Empty");}

        if (containsUser(userName)){
            throw new IllegalArgumentException("The user already exists");
        }
        switch (type) {
            case "Attendee":
                userMap.put(userName, new AttendeeFactory().createAccount(userName, password));
                break;
            case "Speaker":
                userMap.put(userName, new SpeakerFactory().createAccount(userName, password));
                break;
            case "Organizer":
                userMap.put(userName, new OrganizerFactory().createAccount(userName, password));
                break;
            case "VIP":
                userMap.put(userName, new VIPFactory().createAccount(userName, password));
                break;
            default:
                throw new IllegalArgumentException("Invalid input: unknown type");
        }
    }

    /**
     * Check for duplicate userNames, this also counts case-sensitive usernames as duplicates.
     * ie: "Joe" and "JOE" are duplicates
     * This method is to be used at the controller level.
     * Important Note: this is different then the other method containsUser(String) as that checks for exact usernames
     *
     * @param userName The username entered
     * @return true iff userName entered matches any names in the system, disregarding case
     */
    public boolean isDuplicate(String userName) {
        for (String key : userMap.keySet()) {
            if (key.toLowerCase().equals(userName.toLowerCase())){
                return true;
            }
        }
        return false;
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
     *  Gets the type of the user as a String
     *
     * @param username - name of the user
     * @return the user type
     */
    public String getUserType(String username){
        return getUserByName(username).getUserType();
    }

    /**
     * Checks if a user matches a given type
     *
     * @param username The username of the user to be checked
     * @param wantedType The type of user to compare with
     * @return true iff the user is of the given type
     */
    public boolean isOfType(String username, String wantedType){
        return getUserType(username).equals(wantedType);
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
     * Return a list of speakers' usernames.
     *
     * @return an ArrayList of String representing the usernames of speakers
     */
    public ArrayList<String> getSpeakerList(){
        Set<String> usernames = userMap.keySet();
        ArrayList<String> speakers = new ArrayList<>();
        for(int i = 0; i < usernames.size(); i++){
            if (isOfType((String)usernames.toArray()[i], "Speaker"))
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
            if (isOfType((String)usernames.toArray()[i], ("Attendee")))
                attendees.add((String)usernames.toArray()[i]);
        }
        return attendees;
    }
    /**
     * Return a list of VIPs' usernames.
     *
     * @return an ArrayList of String representing the usernames of VIP's
     */
    public ArrayList<String> getVIPList(){
        Set<String> usernames = userMap.keySet();
        ArrayList<String> attendees = new ArrayList<>();
        for(int i = 0; i < usernames.size(); i++){
            if (isOfType((String)usernames.toArray()[i], ("VIP")))
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
    public boolean canLogin(String userName, String password) {
        if (containsUser(userName)) {
            return getPassword(userName).equals(password);
        }
        return false;
    }

    /**
     * This checks if a user has permission to send messages to a receiver.
     *
     * @param username - username of user sending a message
     * @param receiver - username of user receiving the message
     *
     * @return true if the user can send to the receiver, fails silently otherwise
     */
    public boolean canSend(String username, String receiver){
        if (!containsUser(receiver)){ // receiver not found
            return false;
        } else if (receiver.equals(username)){ // send to self
            return false;
        } else if (isOfType(username, "Organizer")){ // organizers can send to any non organizers
            return !isOfType(receiver, "Organizer");
        } else if (isOfType(username, "Speaker")){ // speakers can send to attendees
            return isOfType(receiver, "Attendee");
        } else if (isOfType(username, "VIP")) {
            return true;
        } else{
            return true;
        }
    }

    public boolean isFriendRequestSent(String username, String receiver) {
        return getUserByName(username).getFriendRequest().contains(receiver);
    }

    public boolean isFriend(String username, String receiver) {
        return getUserByName(username).getFriendlist().contains(receiver);
    }

    public boolean addFriendRequest(String username, String receiver){
        if (!isFriend(username, receiver) && !isFriendRequestSent(username, receiver)){
            getUserByName(receiver).addFriendRequest(username);
            return true;
        }
        else {
            return false;
        }
    }

    public void addFriend(String username, String receiver, boolean response) {
        if (isFriend(username, receiver)) {
            return;
        }
        if (response) {
            getUserByName(receiver).addFriend(username);
            getUserByName(username).addFriend(receiver);
            getUserByName(username).removeFriendRequest(receiver);
        }
        else {
            getUserByName(username).removeFriendRequest(receiver);
        }
    }

    public ArrayList<String> getFriendList(String username) {
        return getUserByName(username).getFriendlist();
    }

    public ArrayList<String> getFriendRequest(String username) {
        return getUserByName(username).getFriendRequest();
    }

}

