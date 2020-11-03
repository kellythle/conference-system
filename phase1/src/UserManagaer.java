/**
 * A Class that manages UserAccounts by creating new accounts and checking login information
 */
public class UserManagaer {
    /**
     * Creates a new user, given the type of user and account information.
     *
     * @param userName The new user's username, *cannot be a duplicate within the system
     * @param password The new user's password
     * @param type The type of user added, can only be 3 strings: "Attendee", "Speaker",
     *             or ""Organizer"
     * @return Returns the pointer of the newly created UserAccount, throws
     * @throws IllegalArgumentException when a type is unrecognized
     */
    public UserAccount createUser(String userName, String password, String type) {
        switch (type) {
            case "Attendee":
                return new AttendeeManager().createAttendee(userName, password);
            case "Speaker":
                return new SpeakerManager().createSpeaker(userName, password);
            case "Organizer":
                return new OrganizerManager().createOrganizer(userName, password);
            default:
                throw new IllegalArgumentException("Unknown type of user");
        }
    }

    /**
     * Checks whether or not a person can login with given account information.
     *
     * @param userName The userName id of the user
     * @param password The password of the user
     * @return true if the id is in the system and the password matches, false otherwise
     */
    public boolean canLogin(String userName, String password) { // login checker
        StorageManager sm = new StorageManager();
        if (!idChecker(userName)) {
            return false;
        }
        return sm.getPassword(userName).equals(password);
    }

    /**
     * This checks if an id is in the list of users or not
     *
     * @param id the userName / id to be checked
     * @return true if this id is in the list
     */
    public boolean idChecker(String id) {
        StorageManager sm = new StorageManager();
        return sm.containsUser(id);
    }
}

