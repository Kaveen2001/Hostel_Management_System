package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.CrudDAO;
import lk.ijse.hostel.entity.Reservation;

import java.io.IOException;
import java.util.List;

public interface ReservationDAO extends CrudDAO<Reservation, String> {
    List<Reservation> searchReservedRoomById(String id) throws IOException;
}
