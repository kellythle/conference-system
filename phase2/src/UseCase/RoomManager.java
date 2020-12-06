package UseCase;

import Entity.Room;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RoomManager implements Serializable {
    private TreeMap<Integer, Room> roomMap = new TreeMap<>();

    public boolean doesRoomExist(int roomNumber) {
        return roomMap.containsKey(roomNumber);
    }

    public boolean isRoomMapEmpty() {
        return roomMap.isEmpty();
    }

    public ArrayList<Integer> getRoomNumbers() {
        return new ArrayList<>(roomMap.keySet());
    }

    public TreeMap<LocalDateTime, LocalDateTime> getRoomSchedule(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        return room.getSchedule();
    }

    public int getRoomCapacity(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        return room.getCapacity();
    }

    public int getSquareFootage(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        return room.getSquareFootage();
    }

    public int getScreens(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        return room.getScreens();
    }

    public boolean roomHasSoundSystem(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        return room.hasSoundSystem();
    }

    public boolean roomHasStage(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        return room.hasStage();
    }

    public boolean roomIsAccessible(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        return room.isAccessible();
    }

    public boolean roomHasWifi(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        return room.hasWifi();
    }

    public String getSpecialFeatures(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        return room.getSpecialFeatures();
    }

    public String getDescription(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        return room.getDescription();
    }

    public void addRoom(RoomBuilder roomBuilder)
    {
        Room room = roomBuilder.build();

        int roomNumber = room.getRoomNumber();
        roomMap.put(roomNumber, room);
    }

    public boolean canDeleteRoom(int roomNumber) {
        Room room = roomMap.get(roomNumber);

        return room.getSchedule().isEmpty();
    }

    public void deleteRoom(int roomNumber) {
        roomMap.remove(roomNumber);
    }

    public boolean hasTimeConflict(int room, LocalDateTime checkStartTime, int duration) {
        TreeMap<LocalDateTime, LocalDateTime> schedule = getRoomSchedule(room);
        LocalDateTime checkEndTime = checkStartTime.plusHours(duration);

        for (Map.Entry<LocalDateTime, LocalDateTime> otherTimeInterval : schedule.entrySet()) {
            LocalDateTime otherStartTime = otherTimeInterval.getKey();
            LocalDateTime otherEndTime = otherTimeInterval.getValue();

            if (checkStartTime.isBefore(otherEndTime) && checkEndTime.isAfter(otherStartTime))
                return true;
        }

        return false;
    }

    public ArrayList<Integer> getAvailableRooms(LocalDateTime newStartTime, int duration) {
        ArrayList<Integer> availableRooms = new ArrayList<>();

        for (Map.Entry<Integer, Room> rooms : roomMap.entrySet()) {
            int roomNumber = rooms.getKey();
            boolean conflictExist = this.hasTimeConflict(roomNumber, newStartTime, duration);

            if (!conflictExist) {
                availableRooms.add(roomNumber);
            }
        }

        return availableRooms;
    }

    public void addScheduleTime(int roomNumber, LocalDateTime startTime, int duration) {
        Room room = roomMap.get(roomNumber);
        room.addToSchedule(startTime, duration);
    }

    public void removeScheduleTime(int roomNumber, LocalDateTime startTime) {
        Room room = roomMap.get(roomNumber);
        room.removeFromSchedule(startTime);
    }

    public boolean hasCapacityConflict(int roomNumber, int capacity) {
        return capacity > getRoomCapacity(roomNumber);
    }

    public void removePastTimes(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        TreeMap<LocalDateTime, LocalDateTime> schedule = room.getSchedule();

        for (LocalDateTime startDateTime : schedule.keySet()) {
            if (startDateTime.isBefore(LocalDateTime.now())) {
                room.removeFromSchedule(startDateTime);
            }
        }
    }
}

