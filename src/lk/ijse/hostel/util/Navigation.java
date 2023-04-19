package lk.ijse.hostel.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Navigation {

    public static AnchorPane pneContainer;

    public static void init(AnchorPane pneContainer){
        Navigation.pneContainer=pneContainer;
    }

    public static void navigate(Routes route) throws IOException {
        pneContainer.getChildren().clear();
        Stage container = (Stage)pneContainer.getScene().getWindow();
        URL resource=null;
        switch (route){
            case LOGIN:
                resource= Navigation.class.getResource("lk/ijse/hostel/view/LoginForm.fxml");
                container.setTitle("SYSTEM MAIN LOGIN ");
                break;

            case MAIN_SCREEN:
                resource= Navigation.class.getResource("lk/ijse/hostel/view/MainScreenForm.fxml");
                container.setTitle("WELCOME H24 HOSTEL MANAGEMENT SYSTEM");
                break;

            case DASHBOARD:
                resource= Navigation.class.getResource("lk/ijse/hostel/view/DashboardForm.fxml");
                container.setTitle("H24 MAIN DASHBOARD");
                break;

            case STUDENT:
                resource= Navigation.class.getResource("lk/ijse/hostel/view/StudentForm.fxml");
                container.setTitle("STUDENT MANAGE FORM");
                break;

            case ROOM:
                resource= Navigation.class.getResource("lk/ijse/hostel/view/RoomForm.fxml");
                container.setTitle("ROOM MANAGE FORM");
                break;

            case STUDENT_REGISTRATION:
                resource= Navigation.class.getResource("lk/ijse/hostel/view/StudentRegistrationForm.fxml");
                container.setTitle("STUDENT REGISTRATION FORM");
                break;

            case REGISTER_DETAILS:
                resource= Navigation.class.getResource("lk/ijse/hostel/view/RegisterDetailForm.fxml");
                container.setTitle("MANAGE REGISTER DETAILS FORM");
                break;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        AnchorPane load = fxmlLoader.load();
        pneContainer.getChildren().addAll(load);
    }
}
