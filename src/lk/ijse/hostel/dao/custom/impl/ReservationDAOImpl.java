package lk.ijse.hostel.dao.custom.impl;

import lk.ijse.hostel.dao.custom.ReservationDAO;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public ArrayList<Reservation> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Reservation.class);
        List reserve = criteria.list();

        ArrayList<Reservation> custom = new ArrayList<>(reserve);

        transaction.commit();
        session.close();
        return custom;
    }

    @Override
    public boolean save(Reservation entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reservation entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws IOException {
        return false;
    }

    @Override
    public String generateNewID() throws IOException {
        return null;
    }

    @Override
    public Reservation search(String id) {
        return null;
    }

    @Override
    public List<Reservation> searchReservedRoomById(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Reservation WHERE room = :room_Type_Id";
        Query query = session.createQuery(hql);

        Room room = new Room();
        room.setRoom_Type_Id(id);

        query.setParameter("room_Type_Id", room);
        List<Reservation> reservation = query.list();

        transaction.commit();
        session.close();
        return reservation;
    }
}
