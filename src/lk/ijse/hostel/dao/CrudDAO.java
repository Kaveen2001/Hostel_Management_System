package lk.ijse.hostel.dao;

import java.io.IOException;
import java.util.ArrayList;

public interface CrudDAO<T, ID> extends SuperDAO {
    ArrayList<T> getAll() throws IOException;

    boolean save(T entity) throws IOException;

    boolean update(T entity) throws IOException;

    boolean delete(ID id) throws IOException;

    String generateNewID() throws IOException;

    T search(String id) ;
}