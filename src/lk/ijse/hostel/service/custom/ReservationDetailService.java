package lk.ijse.hostel.service.custom;

import lk.ijse.hostel.dto.CustomDTO;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.service.SuperService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ReservationDetailService extends SuperService {
    ArrayList<CustomDTO> getAllReservationDetails() throws IOException;
    public ArrayList<RoomDTO> getAllRoom() throws IOException;
    public ArrayList<StudentDTO> getAllStudent() throws IOException;
    boolean updateReservation(ReservationDTO dto) throws IOException;
    public List<ReservationDTO> searchReservedRoomById(String id) throws IOException;
}
