package lk.ijse.hostel.service.custom.impl;

import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.DAOTypes;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.service.custom.RoomService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceImpl implements RoomService {

    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.ROOM);

    @Override
    public ArrayList<RoomDTO> getAllRoom() throws IOException {
        ArrayList<Room> all = roomDAO.getAll();
        ArrayList<RoomDTO> allRoom = new ArrayList<>();

        for (Room room : all) {
            allRoom.add(new RoomDTO(room.getRoom_Type_Id(), room.getRoom_Type(), room.getKey_Money(), room.getRoom_Qty()));
        }
        return allRoom;
    }

    @Override
    public boolean saveRoom(RoomDTO roomDTO) throws IOException {
        return roomDAO.save(new Room(roomDTO.getRoom_Type_Id(), roomDTO.getRoom_Type(), roomDTO.getKey_Money(), roomDTO.getRoom_Qty()));
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws IOException {
        return roomDAO.update(new Room(roomDTO.getRoom_Type_Id(), roomDTO.getRoom_Type(), roomDTO.getKey_Money(), roomDTO.getRoom_Qty()));
    }

    @Override
    public boolean deleteRoom(String id) throws IOException {
        return roomDAO.delete(id);
    }

    @Override
    public String generateRoomId() {
        return null;
    }

    @Override
    public List<Room> getRoomDataFromType(String type) throws IOException {
        return roomDAO.getRoomDataFromType(type);
    }

    @Override
    public Room getRoom(String id) throws IOException {
        return roomDAO.getRoom(id);
    }
}
