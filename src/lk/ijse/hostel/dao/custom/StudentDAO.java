package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.CrudDAO;
import lk.ijse.hostel.entity.Student;

import java.io.IOException;

public interface StudentDAO extends CrudDAO<Student,String> {
    public Student getStudent(String id) throws IOException;
}
