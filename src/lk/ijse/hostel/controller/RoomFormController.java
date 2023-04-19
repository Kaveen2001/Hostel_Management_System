package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.service.custom.RoomService;
import lk.ijse.hostel.service.custom.impl.RoomServiceImpl;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.Routes;
import lk.ijse.hostel.view.tm.RoomTM;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class RoomFormController {
    public AnchorPane RoomFormContext;

    public ImageView txtburger;
    public JFXComboBox<String> cmbRoomID;
    public JFXComboBox<String> cmbRoomType;
    public JFXCheckBox checkRoomID;
    public JFXCheckBox checkRoomType;
    public TextField txtRoomID;
    public TextField txtRoomType;
    public Button btnAddRoomType;
    public Button btnAddRoomID;
    public TextField txtRoomKeyMoney;
    public TextField txtRoomQty;

    public TableView<RoomTM> tblRoom;
    public TableColumn colRoomID;
    public TableColumn colRoomType;
    public TableColumn colRoomKeyMoney;
    public TableColumn colRoomQty;

    public TextField txtSearchDetail;
    public Button btnAddRoom;
    public Button btnDeleteRoom;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    RoomService roomService = new RoomServiceImpl();

    public void initialize() throws IOException {

        tblRoom.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("room_Type_Id"));
        tblRoom.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("room_Type"));
        tblRoom.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("key_Money"));
        tblRoom.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("room_Qty"));

        Pattern roomIdPattern = Pattern.compile("^(RM)[0-9,-]{3,5}$");
        Pattern roomTypePattern = Pattern.compile("^[A-z0-9 ,/ ,-]{2,30}$");
        Pattern keyMoneyPattern = Pattern.compile("^[0-9]+[.]?[0-9]*$");
        Pattern roomQtyPattern = Pattern.compile("^\\d+$");

        map.put(txtRoomID,roomIdPattern);
        map.put(txtRoomType,roomTypePattern);
        map.put(txtRoomKeyMoney,keyMoneyPattern);
        map.put(txtRoomQty,roomQtyPattern);

        cmbRoomID.setEditable(false);
        btnAddRoomType.setDisable(true);
        txtRoomType.setDisable(true);
        checkRoomType.setDisable(true);
        btnAddRoomID.setDisable(true);
        txtRoomID.setDisable(true);
        checkRoomID.setDisable(true);
        txtRoomKeyMoney.setEditable(false);

        loadAllRooms();
        setCmbRoomTypes();
        addRoomTypeSelectListener();
        setCmbRoomIds();

        enableDisableCheckBox(checkRoomType, btnAddRoomType, txtRoomType,cmbRoomType);
        enableDisableCheckBox(checkRoomID, btnAddRoomID, txtRoomID,cmbRoomID);
    }

    private void setCmbRoomIds() throws IOException {
        cmbRoomID.getItems().clear();
        for (RoomDTO roomDTO : roomService.getAllRoom()) {
            cmbRoomID.getItems().add(roomDTO.getRoom_Type_Id());
        }
    }

    private void setCmbRoomTypes() throws IOException {
        cmbRoomType.getItems().clear();
        for (RoomDTO roomDTO : roomService.getAllRoom()) {
            cmbRoomType.getItems().add(roomDTO.getRoom_Type());
        }
    }

    private void enableDisableCheckBox(JFXCheckBox checkBox, Button btnAdd, TextField txtRoom, JFXComboBox combo) {
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                btnAdd.setDisable(false);
                txtRoom.setDisable(false);
                txtRoomKeyMoney.setEditable(true);
                combo.setValue(null);

                if(checkBox.getText().equals("Add new Room Type")){
                    txtRoomKeyMoney.clear();
                }

            } else {
                btnAdd.setDisable(true);
                txtRoom.setDisable(true);
                txtRoomKeyMoney.setEditable(false);
            }
        });
    }

    private void addRoomTypeSelectListener() {
        
        cmbRoomType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                try {
                    for (Room room : roomService.getRoomDataFromType(newValue)) {cmbRoomID.getSelectionModel().select(room.getRoom_Type_Id());txtRoomKeyMoney.setText(String.valueOf(room.getKey_Money()));}
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void loadAllRooms() throws IOException {
        
        tblRoom.getItems().clear();
        for (RoomDTO roomDTO : roomService.getAllRoom()) {tblRoom.getItems().add(new RoomTM(roomDTO.getRoom_Type_Id(), roomDTO.getRoom_Type(), roomDTO.getKey_Money(), roomDTO.getRoom_Qty()));}
    }

    public void AddRoomIDOnAction(ActionEvent actionEvent) {

        cmbRoomID.getItems().add(txtRoomID.getText());
        cmbRoomID.getSelectionModel().select(txtRoomID.getText());
        cmbRoomID.setDisable(false);
        checkRoomID.selectedProperty().setValue(false);
    }

    public void AddRoomTypeOnAction(ActionEvent actionEvent) {

        cmbRoomType.getItems().add(txtRoomType.getText());
        cmbRoomType.getSelectionModel().select(txtRoomType.getText());
        cmbRoomID.setValue(null);
        cmbRoomID.setDisable(true);
        checkRoomID.selectedProperty().setValue(true);
        checkRoomType.selectedProperty().setValue(false);
    }

    private void clear(){
        cmbRoomType.setValue(null);
        txtRoomType.clear();
        txtRoomKeyMoney.clear();
        cmbRoomID.setValue(null);
        txtRoomID.clear();
        txtRoomQty.clear();
    }

    public void AddRoomOnAction(ActionEvent actionEvent) throws IOException {

        if(btnAddRoom.getText().equalsIgnoreCase("ADD ROOM")){
            boolean b=true;
            for (RoomDTO roomDTO : roomService.getAllRoom()) {
                if(roomDTO.getRoom_Type_Id().equals(cmbRoomID.getValue())){
                    b=false;
                }
            }

            if(b){
                roomService.saveRoom(new RoomDTO(cmbRoomID.getValue(), cmbRoomType.getValue(), Double.parseDouble(txtRoomKeyMoney.getText()), Integer.parseInt(txtRoomQty.getText())));
                clear();
                loadAllRooms();
            }else{
                //getQTY
                Room room = roomService.getRoom(cmbRoomID.getValue());
                roomService.updateRoom(new RoomDTO(cmbRoomID.getValue(), cmbRoomType.getValue(), Double.parseDouble(txtRoomKeyMoney.getText()), (room.getRoom_Qty()+(Integer.parseInt(txtRoomQty.getText())))));
                clear();
                loadAllRooms();
            }
        }else{
            roomService.updateRoom(new RoomDTO(cmbRoomID.getValue(), cmbRoomType.getValue(), Double.parseDouble(txtRoomKeyMoney.getText()), Integer.parseInt(txtRoomQty.getText())));
            clear();
            loadAllRooms();
            txtRoomKeyMoney.setEditable(false);
            btnAddRoom.setText("ADD ROOM");
        }
    }

    public void searchDetails(KeyEvent keyEvent) {
        cmbRoomID.setDisable(true);
    }

    public void DeleteRoomOnAction(ActionEvent actionEvent) throws IOException {
        RoomTM selectedItem = tblRoom.getSelectionModel().getSelectedItem();

        if (roomService.deleteRoom(selectedItem.getRoom_Type_Id())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted SuccessFully..!").show();
            loadAllRooms();
            setCmbRoomTypes();
            setCmbRoomIds();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something Went Wring..!!").show();
        }
    }

    public void GoBackOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD);
    }
}
