package lk.ijse.hostel.service.custom.impl;

import lk.ijse.hostel.dao.custom.ReservationDAO;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hostel.dto.CustomDTO;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.service.custom.ReservationDetailService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDetailServiceImpl implements ReservationDetailService {

    ReservationDAO reservationDAO = new ReservationDAOImpl();
    RoomDAO roomDAO=new RoomDAOImpl();
    StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public ArrayList<CustomDTO> getAllReservationDetails() throws IOException {
        ArrayList<Reservation> all = reservationDAO.getAll();
        ArrayList<CustomDTO>arrayList=new ArrayList<>();

        for (Reservation reservation : all) {
            arrayList.add(new CustomDTO(reservation.getStudent().getStudent_Id(),reservation.getStudent().getName(),reservation.getStudent().getAddress(),reservation.getStudent().getContact_No(),reservation.getStudent().getDob(),reservation.getStudent().getGender(), reservation.getReservation_Id(),reservation.getReservation_Date(),reservation.getStudent(),reservation.getRoom(),reservation.getStatus(),reservation.getRoom().getRoom_Type_Id(),reservation.getRoom().getRoom_Type(),reservation.getRoom().getKey_Money(),reservation.getRoom().getRoom_Qty()));
        }
        return arrayList;
    }

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
    public ArrayList<StudentDTO> getAllStudent() throws IOException {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudent = new ArrayList<>();

        for (Student student : all) {
            allStudent.add(new StudentDTO(student.getStudent_Id(), student.getName(), student.getAddress(), student.getContact_No(), student.getDob(), student.getGender()));
        }
        return allStudent;
    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) throws IOException {
        return reservationDAO.update(new Reservation(reservationDTO.getReservation_Id(), reservationDTO.getReservation_Date(), reservationDTO.getStudent_Id(), reservationDTO.getRoom_Type_Id(), reservationDTO.getStatus()));
    }

    @Override
    public List<ReservationDTO> searchReservedRoomById(String id) throws IOException {
        List<Reservation> all = reservationDAO.searchReservedRoomById(id);
        List<ReservationDTO> reserveDTO = new ArrayList<>();

        for (Reservation reservation : all) {
            reserveDTO.add(new ReservationDTO(reservation.getReservation_Id(), reservation.getReservation_Date(), reservation.getStudent(), reservation.getRoom(), reservation.getStatus()));
        }
        return reserveDTO;
    }
}
