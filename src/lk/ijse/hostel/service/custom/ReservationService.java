package lk.ijse.hostel.service.custom;

import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.service.SuperService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ReservationService extends SuperService {
    public Room getRoom(String id) throws IOException ;
    public Student getStudent(String id) throws IOException;
    ArrayList<StudentDTO> getAllStudent() throws IOException;
    ArrayList<RoomDTO> getAllRoom() throws IOException;
    public List<ReservationDTO> searchReservedRoomById(String id) throws IOException;
    public boolean registerStudent(ReservationDTO dto) throws IOException;
}
