package Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Room {
    private int roomNumber;
    private int capacity;
    private HashMap<LocalDateTime, String> schedule;
    private int bathrooms;
    private boolean stage;
    private boolean tables;
    private boolean bar;
    private ArrayList<String> technologies;

    public Room(int roomNumber, int capacity, HashMap<LocalDateTime, String> schedule,
                int bathrooms, boolean stage, boolean tables, boolean bar,
                ArrayList<String> technologies) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.schedule = schedule;
        this.bathrooms = bathrooms;
        this.stage = stage;
        this.tables = tables;
        this.bar = bar;
        this.technologies = technologies;
    }

    public int getNumber() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public HashMap<LocalDateTime, String> getSchedule() {
        return schedule;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public boolean hasStage() {
        return stage;
    }

    public boolean hasTables() {
        return tables;
    }

    public boolean hasBar() {
        return bar;
    }

    public ArrayList<String> getTechnologies() {
        return technologies;
    }

}
