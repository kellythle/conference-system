package Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.TreeMap;

public class Room implements Serializable {
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

    public TreeMap<LocalDateTime, LocalDateTime> getSchedule() {
        return schedule;
    }

    public void setSchedule(TreeMap<LocalDateTime, LocalDateTime> schedule) {
        this.schedule = schedule;
    }

    public void addToSchedule(LocalDateTime startTime, int duration) {
        LocalDateTime endTime = startTime.plusHours(duration);
        schedule.put(startTime, endTime);
    }

    public void removeFromSchedule(LocalDateTime startTime) {
        schedule.remove(startTime);
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(int squareFootage) {
        this.squareFootage = squareFootage;
    }

    public int getScreens() {
        return screens;
    }

    public void setScreens(int screens) {
        this.screens = screens;
    }

    public boolean hasSoundSystem() {
        return soundSystem;
    }

    public void setSoundSystem(boolean soundSystem) {
        this.soundSystem = soundSystem;
    }

    public boolean hasStage() {
        return stage;
    }

    public void setStage(boolean stage) {
        this.stage = stage;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public boolean hasWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // public void
}
