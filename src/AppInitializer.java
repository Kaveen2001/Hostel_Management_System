import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/lk/ijse/hostel/view/LoginForm.fxml")))));
        primaryStage.setResizable(false);
        /*primaryStage.initStyle(StageStyle.UNDECORATED);*/
        primaryStage.setTitle("H24 Hostel Management System");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
