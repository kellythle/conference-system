package UseCase;

import Entity.Room;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class RoomBuilder {
    private int roomName;
    private int capacity;
    private HashMap<LocalDateTime, String> schedule;
    private int bathrooms;
    private boolean stage;
    private boolean tables;
    private boolean bar;
    private ArrayList<String> technologies;

    public RoomBuilder roomName(int roomName)
    {
        this.roomName = roomName;
        return this;
    }

    public RoomBuilder capacity(int capacity)
    {
        this.capacity = capacity;
        return this;
    }

    public RoomBuilder bathrooms(int bathrooms)
    {
        this.bathrooms = bathrooms;
        return this;
    }

    public RoomBuilder stage(boolean stage)
    {
        this.stage = stage;
        return this;
    }

    public RoomBuilder tables(boolean tables)
    {
        this.tables = tables;
        return this;
    }

    public RoomBuilder bar(boolean bar)
    {
        this.bar = bar;
        return this;
    }

    public RoomBuilder technologies(ArrayList<String> technologies)
    {
        this.technologies = technologies;
        return this;
    }

    public Room build()
    {
        return new Room(roomName, capacity, schedule, bathrooms, stage, tables, bar, technologies);
    }
}
