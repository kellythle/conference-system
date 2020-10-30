import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * A class that represents a conference.
 * The conference stores the earliest legal starting time and the
 * last legal ending time for events of a day.
 * The conference also stores a map, Map<Date, Integer>, that represents
 * the scheduled times and room numbers.
 *
 * @author An Yen
 */
class Conference {
    private int legalStartHour = 9; //earliest legal starting time for events of a day
    private int legalEndHour = 17; //last legal ending time for events of a day
    private ArrayList<Integer> legalRoomNumbers; // a list of existing room numbers
    private Map<Date, Integer> scheduledSpot; // a map of time to room number that has been scheduled

    /**
     * Creates a conference with the list of room numbers.
     * This conference has default start hour and end hour. (from 9am to 5 pm)
     * @param RoomNumbers- an Arraylist of room numbers
     */
    public Conference(ArrayList<Integer> RoomNumbers){
        this.legalRoomNumbers = RoomNumbers;
    }

    /**
     * Creates a conference by the given start hour, end hour and a list of room numbers.
     * @param startHour- earliest legal starting time for events of a day
     * @param endHour- last legal ending time for events of a day
     * @param RoomNumbers- a list of existing room numbers
     */
    public Conference(int startHour, int endHour, ArrayList<Integer> RoomNumbers){
        this(RoomNumbers);
        this.legalStartHour = startHour;
        this.legalEndHour = endHour;
    }

    /**
     * Returns the legal start hour of the conference.
     * @return the earliest legal starting time for events of a day
     */
    public int getLegalStartHour() {
        return legalStartHour;
    }

    /**
     * Returns the legal end hour of the conference.
     * @return the last legal ending time for events of a day
     */
    public int getLegalEndHour() {
        return legalEndHour;
    }

    /**
     * Returns a map of scheduled spots tha maps from time to room number.
     * @return a Map of the scheduled spots.
     */
    public Map<Date, Integer> getScheduledSpot() {
        return scheduledSpot;
    }

    /**
     * Returns a list of legal room numbers.
     * @return an Arraylist of legal room numbers.
     */
    public ArrayList<Integer> getLegalRoomNumbers() {
        return legalRoomNumbers;
    }

    /**
     * Add a new scheduled spot to the list of scheduled spots.
     * @param date- occurring time of the event
     * @param roomNumber- occurring room number of the event
     */
    public void addScheduledSpot(Date date, int roomNumber){
        this.scheduledSpot.putIfAbsent(date, roomNumber);
    }

}