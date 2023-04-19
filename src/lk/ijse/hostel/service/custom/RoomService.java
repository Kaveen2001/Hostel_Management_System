package lk.ijse.hostel.service.custom;

import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.service.SuperService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface RoomService extends SuperService {
    ArrayList<RoomDTO> getAllRoom() throws IOException;
    boolean saveRoom(RoomDTO dto) throws IOException;
    boolean updateRoom(RoomDTO dto) throws IOException;
    boolean deleteRoom(String id) throws IOException;
    String generateRoomId();
    public List<Room> getRoomDataFromType(String type) throws IOException;
    public Room getRoom(String id) throws IOException;
}
