package UseCase;

import Entity.Organizer;
import Entity.UserAccount;

/**
 * A Class that manages Organizer accounts
 */
public class OrganizerManager extends UserManager {
    /**
     * Creates a new Organizer
     *
     * @param userName The new user's username, *cannot be a duplicate within the system
     * @param password The new user's password
     * @return The newly created Organizer
     * @throws IllegalArgumentException If there is a duplicate userName
     */
    public UserAccount createOrganizer(String userName, String password) {
        if (!super.idChecker(userName)) { //checks for duplicates first
            return new Organizer(userName, password);
        }
        throw new IllegalArgumentException("Duplicate userName");
        // now adds user to the map in the superclass method
    }
}
