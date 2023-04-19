package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.service.custom.StudentService;
import lk.ijse.hostel.service.custom.impl.StudentServiceImpl;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.Routes;
import lk.ijse.hostel.view.tm.StudentTM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class StudentFormController {
    public AnchorPane StudentFormContext;
    public TextField txtStudentID;
    public TextField txtStudentName;
    public TextField txtStudentAddress;
    public TextField txtStudentMobile_No;
    public JFXDatePicker dateDOB;
    public JFXComboBox<String> cmbGender;
    
    public TableView<StudentTM> tblStudent;
    public TableColumn colStudentID;
    public TableColumn colStudentName;
    public TableColumn colStudentAddress;
    public TableColumn colStudentMobileNo;
    public TableColumn colStudentDOB;
    public TableColumn colStudentGender;
    
    public Button btnAddStudent;
    public Button btnDeleteStudent;
    public ImageView txtburger;
    public Button btnUpdateStudent;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    StudentService studentService = new StudentServiceImpl();

    public void initialize() throws IOException {

        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact_No"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));
        cmbGender.getItems().addAll("Male", "Female");

        getAllStudent();

        Pattern studentIdPattern = Pattern.compile("^(S)[0-9]{3,5}$");
        Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 ,/]{2,30}$");
        Pattern contactNoPattern = Pattern.compile("^[\\+](94)[0-9]{9}$");

        map.put(txtStudentID,studentIdPattern);
        map.put(txtStudentName,namePattern);
        map.put(txtStudentAddress,addressPattern);
        map.put(txtStudentMobile_No,contactNoPattern);
    }

    public void getAllStudent() throws IOException {

        ArrayList<StudentDTO> allStudent = studentService.getAllStudent();
        tblStudent.getItems().clear();

        for (StudentDTO studentDTO : allStudent) {
            tblStudent.getItems().add(new StudentTM(studentDTO.getStudent_Id(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getContact_No(), studentDTO.getDob(), studentDTO.getGender()));
        }
    }

    public void AddStudentOnAction(ActionEvent actionEvent) throws IOException {
        
        if(btnAddStudent.getText().equals("ADD STUDENT")){
            boolean b = studentService.saveStudent(new StudentDTO(txtStudentID.getText(), txtStudentName.getText(), txtStudentAddress.getText(), txtStudentMobile_No.getText(), dateDOB.getValue(), cmbGender.getValue()));

            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Added SuccessFully..!").show();
                tblStudent.getItems().add(new StudentTM(txtStudentID.getText(), txtStudentName.getText(), txtStudentAddress.getText(), txtStudentMobile_No.getText(), dateDOB.getValue(), cmbGender.getValue()));
                clearFields();

            } else {
                new Alert(Alert.AlertType.WARNING, "Something Went Wring !!").show();
            }
        }else{
            studentService.updateStudent(new StudentDTO(txtStudentID.getText(), txtStudentName.getText(), txtStudentAddress.getText(), txtStudentMobile_No.getText(), dateDOB.getValue(), cmbGender.getValue()));
            btnAddStudent.setText("ADD STUDENT");
            txtStudentID.setEditable(true);
            clearFields();
            getAllStudent();
        }
    }

    private void clearFields() {
        txtStudentID.clear();
        txtStudentName.clear();
        txtStudentAddress.clear();
        txtStudentMobile_No.clear();
        dateDOB.setValue(null);
        cmbGender.setValue(null);
    }

    public void DeleteStudentOnAction(ActionEvent actionEvent) throws IOException {

        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        if (studentService.deleteStudent(selectedItem.getStudent_Id())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted SuccessFully").show();
            getAllStudent();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something Went Wring !!").show();
        }
    }

    public void UpdateStudentOnAction(ActionEvent actionEvent) {

        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();

        txtStudentID.setText(selectedItem.getStudent_Id());
        txtStudentID.setEditable(false);
        txtStudentName.setText(selectedItem.getName());
        txtStudentAddress.setText(selectedItem.getAddress());
        txtStudentMobile_No.setText(selectedItem.getContact_No());
        dateDOB.setValue(selectedItem.getDob());
        cmbGender.setValue(selectedItem.getGender());
        btnUpdateStudent.setText("UPDATE STUDENT");
    }

    public void GoBackOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD);
    }
}
