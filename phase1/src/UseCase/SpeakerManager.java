package UseCase;

import Entity.Speaker;
import Entity.UserAccount;

/**
 * A Class that manages Speaker accounts
 */
public class SpeakerManager extends UserManager {

    public SpeakerManager() {
        super();
    }

    @Override
    public UserAccount createUser(String userName, String password, String type) {
        if (!super.containsUser(userName)) { //checks for duplicates first
            UserAccount newS = new Speaker(userName, password) ;
            userMap.put(userName, newS);
            return newS;
        }
        throw new IllegalArgumentException("Duplicate userName");
    }

}
