package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.service.ServiceFactory;
import lk.ijse.hostel.service.ServiceTypes;
import lk.ijse.hostel.service.custom.UserService;
import java.io.IOException;
import java.util.ArrayList;

public class LoginFormController {
    private final UserService userService = (UserService) ServiceFactory.getServiceFactory().getService(ServiceTypes.USER);

    public AnchorPane LoginMainFormContext;
    public AnchorPane LoginFormContext;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public Button btnUserLogin;
    public Label lblHide;

    public void showPasswordOnMousePressed(MouseEvent mouseEvent) {
        Image img = new Image("/lk/ijse/hostel/view/assets/images/show.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setFitWidth(20);
        lblHide.setGraphic(view);

        txtPassword.setPromptText(txtPassword.getText());
        txtPassword.setText("");
        txtPassword.setDisable(true);
        txtPassword.requestFocus();
    }

    public void hidePasswordOnMousePressed(MouseEvent mouseEvent) {
        Image img = new Image("/lk/ijse/hostel/view/assets/images/hide.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setFitWidth(20);
        lblHide.setGraphic(view);

        txtPassword.setText(txtPassword.getPromptText());
        txtPassword.setPromptText("");
        txtPassword.setDisable(false);
    }

    public void UserLoginOnAction(ActionEvent actionEvent) throws IOException {

        /*ArrayList<UserDTO> userDTOS = userService.getAllUser();
        attempts++;
        userDTOS.forEach(e -> {
            if (attempts <= 3) {
                if (e.getUser_Id().equals(txtUserName.getText()) && e.getPassword().equals(txtPassword.getText())) {
                    try {
                        UILoader.LoginOnAction(LoginMainFormContext, "DashBoardForm");
                        NotificationUtil.LoginSuccessfulNotification("Admin");
                    } catch (IOException | SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    txtUserName.setEditable(false);
                    txtPassword.setEditable(false);
                    NotificationUtil.LoginUnSuccessfulNotification("Account is Temporarily Disabled or You Did not Sign in Correctly.");
                }
            }
        });*/

        ArrayList<UserDTO> allUser = userService.getAllUser();

        for (UserDTO userDTO : allUser) {

            if(userDTO.getUser_Name().equals(txtUserName.getText()) && userDTO.getPassword().equals(txtPassword.getText())){
                lordWindow();
            }else{
                new Alert(Alert.AlertType.ERROR,"Login Failed..").show();
            }
        }
    }

    private void lordWindow() throws IOException {
        Stage stage = (Stage) LoginMainFormContext.getScene().getWindow();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/lk/ijse/hostel/view/DashboardForm.fxml"));
        Parent root1 = loader1.load();
        Scene scene1 = new Scene(root1);
        stage.setScene(scene1);
        stage.centerOnScreen();
    }
}
