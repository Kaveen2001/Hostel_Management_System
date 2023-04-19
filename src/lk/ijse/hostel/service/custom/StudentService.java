package lk.ijse.hostel.service.custom;

import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.service.SuperService;

import java.io.IOException;
import java.util.ArrayList;

public interface StudentService extends SuperService {
    ArrayList<StudentDTO> getAllStudent() throws IOException;
    boolean saveStudent(StudentDTO dto) throws IOException;
    boolean updateStudent(StudentDTO dto) throws IOException;
    boolean deleteStudent(String id) throws IOException;
    String generateStudentId();
}
