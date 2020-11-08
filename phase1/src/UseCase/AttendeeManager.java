package UseCase;

import Entity.Attendee;
import Entity.Speaker;
import Entity.UserAccount;

/**
 * A Class that manages Attendee accounts
 */
public class AttendeeManager extends UserManager {
    /**
     * Creates a new Attendee
     */
    public AttendeeManager(){super();}

    @Override
    public UserAccount createUser(String userName, String password, String type) {
        if (!super.containsUser(userName)) { //checks for duplicates first
            UserAccount newA = new Speaker(userName, password) ;
            userMap.put(userName, newA);
            return newA;
        }
        throw new IllegalArgumentException("Duplicate userName");
    }
}
