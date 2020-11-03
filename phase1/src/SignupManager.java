import java.util.ArrayList;

/**
 * A class that represents a Use Case for signing up for events.
 * @author Filip Jovanovic
 */
public class SignupManager {

    private ArrayList<UserAccount> attendeesList;
    private ArrayList<Event> eventsList;

    /**
     * Returns true if user can be added to the list of attendees
     * for the event.
     *
     * @param user - the user object
     * @param event - the event object
     * @return true if the attendee can be added to the event,
     * false otherwise
     */
    public boolean canAddUserToEvent(UserAccount user, Event event)
    {
        String username = user.getUserName();

        // TODO: Check for room capacity as well
        return !event.hasAttendee(username);
    }

    /**
     * Adds User to an Event's list of attendees.
     *
     * @param user - the user object
     * @param event - the event object
     */
    public void addUserToEvent(UserAccount user, Event event)
    {
        String username = user.getUserName();

        event.addAttendee(username);
    }
}
