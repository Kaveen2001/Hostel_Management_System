package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.dto.CustomDTO;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.service.custom.ReservationDetailService;
import lk.ijse.hostel.service.custom.impl.ReservationDetailServiceImpl;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.Routes;
import lk.ijse.hostel.view.tm.ReservationDetailTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegisterDetailFormController {

    public AnchorPane RegisterDetailFormContext;

    public ImageView txtburger;
    public TextField txtReservedID;
    public JFXComboBox<String> cmbUpdateSelectStudent;
    public JFXComboBox<String> cmbUpdateSelectRoom;
    public TextField txtUpdateRoomStatus;
    public TextField txtSearchDetail;
    public JFXComboBox<String> cmbRoomID;
    public TextField txtRoomType;
    public JFXCheckBox checkPaid;
    public JFXCheckBox checkNonPaid;
    public JFXCheckBox checkOtherPayment;

    public TableView<ReservationDetailTM> tblRegistationDetail;
    public TableColumn colReservedID;
    public TableColumn colReservedDate;
    public TableColumn colStudentID;
    public TableColumn colRoomID;
    public TableColumn colStatus;
    public TableColumn colRemainKeyMoney;

    public Button btnUpdateDetail;
    public Button btnClearDetail;

    LocalDate date;

    ReservationDetailService reservationDetailService = new ReservationDetailServiceImpl();

    private ObservableSet<JFXCheckBox> selectedCheckBoxes = FXCollections.observableSet();
    private ObservableSet<JFXCheckBox> unselectedCheckBoxes = FXCollections.observableSet();

    private IntegerBinding numCheckBoxesSelected = Bindings.size(selectedCheckBoxes);

    private final int maxNumSelected =  1;


    public void initialize() throws IOException {

        loadAllReservation();

        tblRegistationDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("reservation_Id"));
        tblRegistationDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("reservation_Date"));
        tblRegistationDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("student_Id"));
        tblRegistationDetail.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("room_Type_Id"));
        tblRegistationDetail.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("status"));
        tblRegistationDetail.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("remain_keyMoney_fee"));

        loadCmbData();
        loadSearchReserve();
        addCheckBoxListener();

        configureCheckBox(checkPaid);
        configureCheckBox(checkNonPaid);
        configureCheckBox(checkOtherPayment);

        numCheckBoxesSelected.addListener((obs, oldSelectedCount, newSelectedCount) -> {
            if (newSelectedCount.intValue() >= maxNumSelected) {
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(true));
            } else {
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(false));
                try {
                    loadAllReservation();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cmbRoomID.setValue(null);
                cmbUpdateSelectRoom.setValue(null);
            }
        });

        txtReservedID.setDisable(true);
        cmbUpdateSelectStudent.setDisable(true);
        cmbUpdateSelectRoom.setDisable(true);
        txtUpdateRoomStatus.setDisable(true);
    }

    private void loadAllReservation() throws IOException {

        tblRegistationDetail.getItems().clear();
        ArrayList<CustomDTO> allRes = reservationDetailService.getAllReservationDetails();

        for (CustomDTO all : allRes) {

            String remain = "";
            String status = all.getStatus();

            if (status.equalsIgnoreCase("PAID")) {
                remain = "---";
            } else if (status.equalsIgnoreCase("NON-PAID")) {
                remain = String.valueOf(all.getKey_Money());
            } else {
                if (!status.equals("")) {
                    double paid = Double.parseDouble(status);
                    remain = String.valueOf(all.getKey_Money() - paid);
                }
            }
            tblRegistationDetail.getItems().add(new ReservationDetailTM(all.getReservation_Id(), all.getReservation_Date(), all.getReg_Student_Id().getStudent_Id(), all.getReg_Room_Id().getRoom_Type_Id(), all.getStatus(),all.getKey_Money()));
        }
    }

    private void loadCmbData() throws IOException {

        for (RoomDTO roomDTO : reservationDetailService.getAllRoom()) {
            cmbUpdateSelectRoom.getItems().add(roomDTO.getRoom_Type_Id());
            cmbRoomID.getItems().add(roomDTO.getRoom_Type_Id());
            cmbUpdateSelectRoom.getItems().add(roomDTO.getRoom_Type());
        }

        for (StudentDTO dto : reservationDetailService.getAllStudent()) {
            cmbUpdateSelectStudent.getItems().add(dto.getStudent_Id());
        }
    }

    private void loadSearchReserve() {

    }

    private void configureCheckBox(JFXCheckBox checkPaid) {
        if (checkPaid.isSelected()) {
            selectedCheckBoxes.add(checkPaid);
        } else {
            unselectedCheckBoxes.add(checkPaid);
        }

        checkPaid.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                unselectedCheckBoxes.remove(checkPaid);
                selectedCheckBoxes.add(checkPaid);
            } else {
                selectedCheckBoxes.remove(checkPaid);
                unselectedCheckBoxes.add(checkPaid);
            }
        });
    }

    public void checkOnAction(ActionEvent actionEvent) {
    }

    public void UpdateDetailOnAction(ActionEvent actionEvent) throws IOException {

        txtReservedID.setDisable(true);
        cmbUpdateSelectStudent.setDisable(true);
        cmbUpdateSelectRoom.setDisable(true);
        txtUpdateRoomStatus.setDisable(true);

        Student student = new Student();
        student.setStudent_Id(cmbUpdateSelectStudent.getValue());

        Room room = new Room();
        room.setRoom_Type_Id(cmbUpdateSelectRoom.getValue());

        boolean b = reservationDetailService.updateReservation(new ReservationDTO(txtReservedID.getText(),date,student,room,txtUpdateRoomStatus.getText()));

        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Reservation Updated!!").show();
            loadAllReservation();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Something Went Wrong").show();

        }
    }

    public void ClearDetailOnAction(ActionEvent actionEvent) throws IOException {

        loadAllReservation();
        cmbRoomID.setValue(null);
        cmbUpdateSelectRoom.setValue(null);

        checkPaid.selectedProperty().setValue(false);
        checkNonPaid.selectedProperty().setValue(false);
        checkOtherPayment.selectedProperty().setValue(false);
    }

    private void addCheckBoxListener() {

        checkPaid.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Paid clicked");

                //======Select CheckBox with Filtered Room======

                //is NOt Selected
                if(cmbUpdateSelectRoom.getSelectionModel().isEmpty()){

                    try {
                        ArrayList<CustomDTO> allReservationDetails = reservationDetailService.getAllReservationDetails();
                        tblRegistationDetail.getItems().clear();

                        for (CustomDTO all : allReservationDetails) {

                            if(all.getStatus().equalsIgnoreCase("Paid")){
                                String remain = "";
                                String status = all.getStatus();

                                if (status.equalsIgnoreCase("Paid")) {
                                    remain = "---";
                                } else if (status.equalsIgnoreCase("Non-Paid")) {
                                    remain = String.valueOf(all.getKey_Money());
                                } else {
                                    if (!status.equals("")) {
                                        double paid = Double.parseDouble(status);
                                        remain = String.valueOf(all.getKey_Money() - paid);
                                    }
                                }
                                tblRegistationDetail.getItems().add(new ReservationDetailTM(all.getReservation_Id(), all.getReservation_Date(), all.getStudent_Id(), all.getRoom_Type_Id(), all.getStatus(),all.getKey_Money()));
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{

                    //is Selected
                    try {
                        ArrayList<CustomDTO> allReservationDetails = reservationDetailService.getAllReservationDetails();
                        tblRegistationDetail.getItems().clear();

                        for (CustomDTO all : allReservationDetails) {

                            if(all.getStatus().equalsIgnoreCase("Paid") && all.getReg_Room_Id().getRoom_Type_Id().equals(cmbUpdateSelectRoom.getValue())){

                                String remain = "";
                                String status = all.getStatus();

                                if (status.equalsIgnoreCase("Paid")) {
                                    remain = "---";
                                } else if (status.equalsIgnoreCase("Non-Paid")) {
                                    remain = String.valueOf(all.getKey_Money());
                                } else {
                                    if (!status.equals("")) {
                                        double paid = Double.parseDouble(status);
                                        remain = String.valueOf(all.getKey_Money() - paid);
                                    }
                                }
                                tblRegistationDetail.getItems().add(new ReservationDetailTM(all.getReservation_Id(), all.getReservation_Date(), all.getStudent_Id(), all.getRoom_Type_Id(), all.getStatus(), all.getKey_Money()));
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                System.out.println("Paid clicked oFF");

            }
        });

        //=====NonPaid Listener=====
        checkNonPaid.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Non-Paid clicked");

                //======Select CheckBox with Filtered Room======

                //is NOt Selected
                if(cmbUpdateSelectRoom.getSelectionModel().isEmpty()) {

                    try {
                        ArrayList<CustomDTO> allReservationDetails = reservationDetailService.getAllReservationDetails();
                        tblRegistationDetail.getItems().clear();
                        for (CustomDTO all : allReservationDetails) {

                            if(all.getStatus().equalsIgnoreCase("Non-Paid")){
                                String remain = "";
                                String status = all.getStatus();

                                if (status.equalsIgnoreCase("Paid")) {
                                    remain = "---";
                                } else if (status.equalsIgnoreCase("Non-Paid")) {
                                    remain = String.valueOf(all.getKey_Money());
                                } else {
                                    if (!status.equals("")) {
                                        double paid = Double.parseDouble(status);
                                        remain = String.valueOf(all.getKey_Money() - paid);
                                    }
                                }
                                tblRegistationDetail.getItems().add(new ReservationDetailTM(all.getReservation_Id(), all.getReservation_Date(), all.getStudent_Id(), all.getRoom_Type_Id(), all.getStatus(), all.getKey_Money()));
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{

                    //is Selected
                    try {
                        ArrayList<CustomDTO> allReservationDetails = reservationDetailService.getAllReservationDetails();
                        tblRegistationDetail.getItems().clear();

                        for (CustomDTO all : allReservationDetails) {

                            if(all.getStatus().equalsIgnoreCase("Non-Paid") && all.getReg_Room_Id().getRoom_Type_Id().equals(cmbUpdateSelectRoom.getValue())){

                                String remain = "";
                                String status = all.getStatus();

                                if (status.equalsIgnoreCase("Paid")) {
                                    remain = "---";
                                } else if (status.equalsIgnoreCase("Non-Paid")) {
                                    remain = String.valueOf(all.getKey_Money());
                                } else {
                                    if (!status.equals("")) {
                                        double paid = Double.parseDouble(status);
                                        remain = String.valueOf(all.getKey_Money() - paid);
                                    }
                                }
                                tblRegistationDetail.getItems().add(new ReservationDetailTM(all.getReservation_Id(), all.getReservation_Date(), all.getStudent_Id(), all.getRoom_Type_Id(), all.getStatus(), all.getKey_Money()));
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                System.out.println("Non-Paid clicked oFF");

            }
        });

        //=====OtherPayed Listener=====
        checkOtherPayment.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Other-Paid clicked");

                //======Select CheckBox with Filtered Room======

                //is NOt Selected
                if(cmbUpdateSelectRoom.getSelectionModel().isEmpty()) {

                    try {
                        ArrayList<CustomDTO> allReservationDetails = reservationDetailService.getAllReservationDetails();
                        tblRegistationDetail.getItems().clear();
                        for (CustomDTO all : allReservationDetails) {

                            if(!all.getStatus().equalsIgnoreCase("Paid") && !all.getStatus().equalsIgnoreCase("Non-Paid"))  {
                                String remain = "";
                                String status = all.getStatus();

                                if (status.equalsIgnoreCase("Paid")) {
                                    remain = "---";
                                } else if (status.equalsIgnoreCase("Non-Paid")) {
                                    remain = String.valueOf(all.getKey_Money());
                                } else {
                                    if (!status.equals("")) {
                                        double paid = Double.parseDouble(status);
                                        remain = String.valueOf(all.getKey_Money() - paid);
                                    }
                                }

                                tblRegistationDetail.getItems().add(new ReservationDetailTM(all.getReservation_Id(), all.getReservation_Date(), all.getStudent_Id(), all.getRoom_Type_Id(), all.getStatus(), all.getKey_Money()));
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else{

                    //is Selected
                    try {
                        ArrayList<CustomDTO> allReservationDetails = reservationDetailService.getAllReservationDetails();
                        tblRegistationDetail.getItems().clear();

                        for (CustomDTO all : allReservationDetails) {

                            if(!all.getStatus().equalsIgnoreCase("Paid") && !all.getStatus().equalsIgnoreCase("Non-Paid") && all.getReg_Room_Id().getRoom_Type_Id().equals(cmbUpdateSelectRoom.getValue())){

                                String remain = "";
                                String status = all.getStatus();

                                if (status.equalsIgnoreCase("Paid")) {
                                    remain = "---";
                                } else if (status.equalsIgnoreCase("Non-Paid")) {
                                    remain = String.valueOf(all.getKey_Money());
                                } else {
                                    if (!status.equals("")) {
                                        double paid = Double.parseDouble(status);
                                        remain = String.valueOf(all.getKey_Money() - paid);
                                    }
                                }

                                tblRegistationDetail.getItems().add(new ReservationDetailTM(all.getReservation_Id(), all.getReservation_Date(), all.getStudent_Id(), all.getRoom_Type_Id(), all.getStatus(), all.getKey_Money()));
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                System.out.println("Other clicked oFF");
            }
        });
    }

    public void GoBackOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD);
    }
}
