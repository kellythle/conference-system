package UseCase;

import Entity.Organizer;
import Entity.Speaker;
import Entity.UserAccount;

/**
 * A Class that manages Organizer accounts
 */
public class OrganizerManager extends UserManager {

    public OrganizerManager(){super();}

    @Override
    /**
     * Creates a new Organizer
     *
     * @param userName The new user's username, *cannot be a duplicate within the system
     * @param password The new user's password
     * @return The newly created Organizer
     * @throws IllegalArgumentException If there is a duplicate userName
     */
    public UserAccount createUser(String userName, String password, String type) {
        if (!super.containsUser(userName)) { //checks for duplicates first
            UserAccount newO = new Speaker(userName, password) ;
            userMap.put(userName, newO);
            return newO;
        }
        throw new IllegalArgumentException("Duplicate userName");
    }




}
