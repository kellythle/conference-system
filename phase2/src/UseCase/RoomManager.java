package UseCase;

import Entity.Room;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

public class RoomManager implements Serializable {
    private HashMap<Integer, Room> roomMap = new HashMap<>();

    public boolean doesRoomExist(int roomNumber) {
        return roomMap.containsKey(roomNumber);
    }

    public int getRoomCapacity(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        return room.getCapacity();
    }

    public HashMap<LocalDateTime, String> getRoomSchedule(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        return room.getSchedule();
    }

    public int getRoomBathrooms(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        return room.getBathrooms();
    }

    public boolean roomHasStage(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        return room.hasStage();
    }

    public boolean roomHasTables(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        return room.hasTables();
    }

    public boolean roomHasBar(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        return room.hasBar();
    }

    public void addRoom(RoomBuilder roomBuilder)
    {
        Room room = roomBuilder.build();
    }
}
