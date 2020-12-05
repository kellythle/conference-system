package UseCase;

import Entity.Room;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class RoomBuilder {
    private TreeMap<LocalDateTime, LocalDateTime> schedule = new TreeMap<>();
    private int roomNumber;
    private int capacity;
    private int squareFootage;
    private int screens;
    private boolean soundSystem;
    private boolean stage;
    private boolean accessible;
    private boolean wifi;
    private String specialFeatures;
    private String description;

    public RoomBuilder roomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public RoomBuilder capacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public RoomBuilder squareFootage(int squareFootage) {
        this.squareFootage = squareFootage;
        return this;
    }

    public RoomBuilder screens(int screens) {
        this.screens = screens;
        return this;
    }

    public RoomBuilder soundSystem(boolean soundSystem) {
        this.soundSystem = soundSystem;
        return this;
    }

    public RoomBuilder stage(boolean stage) {
        this.stage = stage;
        return this;
    }

    public RoomBuilder accessible(boolean accessible) {
        this.accessible = accessible;
        return this;
    }

    public RoomBuilder wifi(boolean wifi) {
        this.wifi = wifi;
        return this;
    }

    public RoomBuilder specialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
        return this;
    }

    public RoomBuilder description(String description) {
        this.description = description;
        return this;
    }

    public Room build() {
        Room room = new Room();

        room.setRoomNumber(roomNumber);
        room.setCapacity(capacity);
        room.setSquareFootage(squareFootage);
        room.setScreens(screens);
        room.setSoundSystem(soundSystem);
        room.setStage(stage);
        room.setAccessible(accessible);
        room.setWifi(wifi);
        room.setSpecialFeatures(specialFeatures);
        room.setDescription(description);

        return room;
    }
}
