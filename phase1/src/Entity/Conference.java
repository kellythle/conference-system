package Entity;


import java.time.LocalDateTime;
import java.util.Date;

/**
 * A class that represents a conference. All events in this conference should
 * occur between the starting time and ending time of the conference.
 * @author An Yen
 */
public class Conference {
    private int startTime = 9; // the opening time of this conference is 9 am.
    private int endTime = 17; // the ending time of this conference is 5 pm.

    /**
     * Returns the starting time of this conference
     * @return the startTime of this Conference
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Returns the ending time of this conference.
     * @return the endTime of this Conference
     */
    public int getEndTime() {
        return endTime;
    }
}