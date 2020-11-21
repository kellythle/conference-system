package Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that represents abstract class user account.
 * @author Qianwen Wu
 */
public abstract class UserAccount implements Serializable {
    private String userName;
    private String passWord;
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
        this.registeredEvents = new ArrayList<>();
        this.isOrganizer = false;
        this.isSpeaker = false;
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