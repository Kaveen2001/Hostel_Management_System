package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.service.custom.ReservationService;
import lk.ijse.hostel.service.custom.impl.ReservationServiceImpl;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.Routes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class StudentRegistrationFormController {
    public AnchorPane RegisterStudentContext;

    public JFXComboBox<String> cmbStudentID;
    public TextField txtStudentName;
    public TextField txtStudentAddress;
    public TextField txtStudentMobile_No;
    public JFXDatePicker dateDOB;

    public JFXComboBox<String> cmbRoomID;
    public TextField txtRoomType;
    public TextField txtRoomKeyMoney;
    public TextField txtRoomQty;
    public TextField txtRoomStatus;

    public Label lblAvailable;
    public Label lblAllRooms;
    public Label lblUsedRooms;
    public Label lblRemainingRooms;

    public TextField txtRegistationID;
    public Button btnRegisterStudent;
    public ImageView txtburger;

    ReservationService reservationService = new ReservationServiceImpl();

    public void initialize() throws IOException {

        lblAvailable.setText("................");

        for (StudentDTO dto : reservationService.getAllStudent()) {
            cmbStudentID.getItems().add(dto.getStudent_Id());
        }

        for (RoomDTO roomDTO : reservationService.getAllRoom()) {
            cmbRoomID.getItems().add(roomDTO.getRoom_Type_Id());
        }

        cmbRoomID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if(newValue!=null){
                Room room = null;
                try {
                    room = reservationService.getRoom(newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                txtRoomType.setText(room.getRoom_Type());
                txtRoomKeyMoney.setText(String.valueOf(room.getKey_Money()));
                txtRoomQty.setText(String.valueOf(room.getRoom_Qty()));
                lblAllRooms.setText(String.valueOf(room.getRoom_Qty()));

                try {
                    List<ReservationDTO> reserveDTOS = reservationService.searchReservedRoomById(newValue);
                    int count=0;
                    for (ReservationDTO reservationDTO : reserveDTOS) {
                        count++;
                    }

                    int remainQty=Integer.parseInt(txtRoomQty.getText())-count;
                    lblUsedRooms.setText(String.valueOf(count));
                    lblRemainingRooms.setText(String.valueOf(remainQty));

                    if(remainQty==0){
                        lblAvailable.setText("UN-AVAILABLE");
                    }else{
                        lblAvailable.setText("AVAILABLE");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        cmbStudentID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if(newValue!=null){
                Student student = null;
                try {
                    student = reservationService.getStudent(newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                txtStudentName.setText(student.getName());
                txtStudentAddress.setText(student.getAddress());
                dateDOB.setValue(student.getDob());
            }
        });
        searchRoomQty();
    }

    private void searchRoomQty() {
        return;
    }

    public void RegisterStudentOnAction(ActionEvent actionEvent) throws IOException {

        if(lblAvailable.getText().equalsIgnoreCase("AVAILABLE")) {
            Student student = new Student();
            student.setStudent_Id(cmbStudentID.getValue());

            Room room = new Room();
            room.setRoom_Type(cmbRoomID.getValue());
            reservationService.registerStudent(new ReservationDTO(
                    txtRegistationID.getText(),
                    LocalDate.now(),
                    student,
                    room,
                    txtRoomStatus.getText()
            ));
            clear();
            lblAllRooms.setText("00");
            lblUsedRooms.setText("00");
            lblRemainingRooms.setText("00");
        }else{
            new Alert(Alert.AlertType.WARNING,"You Can't Register Student for This Room").show();
        }
    }

    private void clear(){
        cmbStudentID.setValue(null);
        cmbRoomID.setValue(null);
        txtRoomStatus.clear();
    }

    public void GoBackOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD);
    }
}
