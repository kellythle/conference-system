package UseCase;

import Entity.Room;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class RoomManager implements Serializable {
    private HashMap<Integer, Room> roomMap = new HashMap<>();

    public HashMap<Integer, Room> getRoomMap() {
        return roomMap;
    }

    public void setRoomMap(HashMap<Integer, Room> roomMap) {
        this.roomMap = roomMap;
    }

    public Room getRoom(int roomNumber) {
        return roomMap.getOrDefault(roomNumber, null);
    }

    public ArrayList<Integer> getAvailableRooms(LocalDateTime time) {
        // TODO: Finish this
        return null;
    }

}
