import java.util.ArrayList;

/**
 * A class that represents abstract class user account.
 * @author Qianwen Wu
 */
abstract class UserAccount {
    private String userName;
    private String passWord;
    private ArrayList<String> friendList; // Stores the userNames
    private ArrayList<Integer> messageSent; // Stores the list of messages id sent to others
    private ArrayList<Integer> messageReceived; // Stores the list of messages id received from others
    private ArrayList<String> registeredEvents; // Stores the list of events that the user have registered
    protected boolean isOrganizer; // Check if the user is an organizer
    protected boolean isSpeaker; // Check if the user is a speaker

    public UserAccount() {}

    /**
     * Create user account by given user name and password
     *
     * @param userName- name of user
     * @param passWord- identify user by password
     */
    public UserAccount(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        this.friendList = new ArrayList<>();
        this.messageReceived = new ArrayList<>();
        this.messageSent = new ArrayList<>();
        this.registeredEvents = new ArrayList<>();
    }

    /**
     * Getter of user name
     *
     * @return the user name
     */
    public String getUserName() {return userName;}

    /**
     * Modify the user name
     *
     */
    public void setUserName(String name) {this.userName = name;}

    /**
     * Return the password
     *
     * @return password
     */
    public String getPassWord() {return passWord;}

    /**
     * Modify the password
     *
     * @param passWord- password
     */
    public void setPassWord(String passWord) {this.passWord = passWord;}

    /**
     * Return the friend list of the user
     *
     * @return friend list of the user
     */
    public ArrayList<String> getFriendList() {return friendList;}

    /**
     * Add a friend to the user's friend list
     *
     * @param friendName- name of a friend
     */
    public void addFriend(String friendName) {this.friendList.add(friendName);}

    /**
     * Return the messages the user have sent
     *
     * @return Id of messages the user have sent
     */
    public ArrayList<Integer> getMessageSent() {return messageSent;}

    /**
     * Add id of a message that the user want to send to another user
     *
     * @param messageSentId- Id of a message sent to another user
     */
    public void addNewMessageSent(int messageSentId) {this.messageSent.add(messageSentId);}

    /**
     * Return list of message id the user have received
     *
     * @return List of message id the user have received
     */
    public ArrayList<Integer> getMessageReceived() {return messageReceived;}

    /**
     * Add id of a message that the user have received
     *
     * @param messageReceivedId- Id of a message received
     */
    public void addNewMessageReceived(int messageReceivedId) {this.messageReceived.add(messageReceivedId);}

    /**
     * Return the list of registered events
     * @return the list of registered events
     */
    public ArrayList<String> getRegisteredEvents() {return registeredEvents;}

    /**
     * Sign up new event
     * @param eventName- registered event name
     */
    public void registerEvent(String eventName) {this.registeredEvents.add(eventName);}

    /**
     * Identify the user whether is an organizer or not
     *
     * @return boolean value
     */
    public boolean isOrganizer() {return isOrganizer;}

    /**
     * Identify the user whether is a speaker or not
     *
     * @return boolean value
     */
    public boolean isSpeaker() {return isSpeaker;}

}