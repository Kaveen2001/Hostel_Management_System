package lk.ijse.hostel.service.custom.impl;

import lk.ijse.hostel.dao.custom.ReservationDAO;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.service.custom.ReservationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationServiceImpl implements ReservationService {
    RoomDAO roomDAO = new RoomDAOImpl();
    StudentDAO studentDAO = new StudentDAOImpl();
    ReservationDAO reservationDAO = new ReservationDAOImpl();

    @Override
    public Room getRoom(String id) throws IOException {
        return roomDAO.getRoom(id);
    }

    @Override
    public Student getStudent(String id) throws IOException {
        return studentDAO.getStudent(id);
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
    public ArrayList<RoomDTO> getAllRoom() throws IOException {
        ArrayList<Room> all = roomDAO.getAll();
        ArrayList<RoomDTO> allRoom = new ArrayList<>();

        for (Room room : all) {
            allRoom.add(new RoomDTO(room.getRoom_Type_Id(), room.getRoom_Type(), room.getKey_Money(), room.getRoom_Qty()));
        }
        return allRoom;
    }

    @Override
    public List<ReservationDTO> searchReservedRoomById(String id) throws IOException {
        List<Reservation> all = reservationDAO.searchReservedRoomById(id);
        List<ReservationDTO> allReservation = new ArrayList<>();

        for (Reservation reservation : all) {
            allReservation.add(new ReservationDTO(reservation.getReservation_Id(), reservation.getReservation_Date(), reservation.getStudent(), reservation.getRoom(), reservation.getStatus()));
        }
        return allReservation;
    }

    @Override
    public boolean registerStudent(ReservationDTO reservationDTO) throws IOException {
        return reservationDAO.save(new Reservation(reservationDTO.getReservation_Id(), reservationDTO.getReservation_Date(), reservationDTO.getStudent_Id(), reservationDTO.getRoom_Type_Id(), reservationDTO.getStatus()));
    }
}
