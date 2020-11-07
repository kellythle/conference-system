package UseCase;

import Entity.Speaker;
import Entity.UserAccount;

/**
 * A Class that manages Speaker accounts
 */
public class SpeakerManager extends UserManager {
    /**
     * Creates a new Speaker
     *
     * @param userName The new user's username, *cannot be a duplicate within the system
     * @param password The new user's password
     * @return The newly created Speaker
     * @throws IllegalArgumentException If there is a duplicate userName
     */
    public UserAccount createSpeaker(String userName, String password) {
        if (!super.idChecker(userName)) { //checks for duplicates first
            return new Speaker(userName, password);
        }
        throw new IllegalArgumentException("Duplicate userName");
        //TODO: Maybe add the new user to the list?
    }
}
