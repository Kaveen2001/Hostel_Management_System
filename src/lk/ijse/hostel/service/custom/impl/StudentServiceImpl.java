package lk.ijse.hostel.service.custom.impl;

import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.DAOTypes;
import lk.ijse.hostel.service.custom.StudentService;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Student;

import java.io.IOException;
import java.util.ArrayList;

public class StudentServiceImpl implements StudentService {
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.STUDENT);

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
    public boolean saveStudent(StudentDTO dto) throws IOException {
        return studentDAO.save(new Student(dto.getStudent_Id(), dto.getName(), dto.getAddress(), dto.getContact_No(), dto.getDob(), dto.getGender()));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws IOException {
        return studentDAO.update(new Student(dto.getStudent_Id(), dto.getName(), dto.getAddress(), dto.getContact_No(), dto.getDob(), dto.getGender()));
    }


    @Override
    public boolean deleteStudent(String id) throws IOException {
        return studentDAO.delete(id);
    }

    @Override
    public String generateStudentId() {
        return null;
    }
}
