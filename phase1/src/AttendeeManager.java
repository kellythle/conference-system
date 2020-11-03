/**
 * A Class that manages Attendee accounts
 */
public class AttendeeManager extends UserManagaer {
    /**
     * Creates a new Attendee
     *
     * @param userName The new user's username, *cannot be a duplicate within the system
     * @param password The new user's password
     * @return The newly created Attendee
     * @throws IllegalArgumentException If there is a duplicate userName
     */
    public UserAccount createAttendee(String userName, String password) {
        if (!super.idChecker(userName)) { //checks for duplicates first
            return new Attendee(userName, password);
        }
        throw new IllegalArgumentException("Duplicate userName");
        //TODO: Maybe add the new user to the list?
    }
}
