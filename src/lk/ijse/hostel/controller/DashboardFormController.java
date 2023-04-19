package lk.ijse.hostel.controller;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.Routes;

import java.io.IOException;
import java.util.Objects;

public class DashboardFormController {
    public AnchorPane DashboardFormContext;

    public ImageView imgUserLogOut;
    public ImageView imgStudent;
    public ImageView imgRoom;
    public ImageView imgReservation;
    public ImageView imgReservationDetails;
    public Label lblMenu;
    public Label lblDescription;
    public Label lblDate;
    public Label lblTime;

    public void navigate(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root = null;

            switch (icon.getId()) {
                case "imgStudent":
                    root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("lk/ijse/hostel/view/StudentForm.fxml")));
                    break;
                case "imgRoom":
                    root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("lk/ijse/hostel/view/RoomForm.fxml")));
                    break;
                case "imgReservation":
                    root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("lk/ijse/hostel/view/StudentRegistrationForm.fxml")));
                    break;
                case "imgReservationDetails":
                    root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("lk/ijse/hostel/view/RegisterDetailForm.fxml")));
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.DashboardFormContext.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();
            }
        }
    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {

        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()) {
                case "imgStudent":
                    lblMenu.setText("Manage Students");
                    lblDescription.setText("Click to Make Registration and Add, Update, Delete Student.");
                    break;
                case "imgRoom":
                    lblMenu.setText("Manage Rooms");
                    lblDescription.setText("Click to Add, Update, Delete Rooms.");
                    break;
                case "imgReservation":
                    lblMenu.setText("Manage  Reservations");
                    lblDescription.setText("Click to student rooms reservations.");
                    break;
                case "imgReservationDetails":
                    lblMenu.setText("Manage  Reservation Details");
                    lblDescription.setText("Click to student reservation details.");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void playMouseExitAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome H24 Hostel");
            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }

    public void UserlogOutOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN);
    }

    public void getAllData(String text, String text1) {
    }
}
