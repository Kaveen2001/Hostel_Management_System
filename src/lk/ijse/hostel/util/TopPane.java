package lk.ijse.hostel.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.text.SimpleDateFormat;

public class TopPane {
    static Thread thread;
    public static void setDateTime(Label lblTime, Label lblDate) {
        thread=new Thread(()->{


            while (true){
                SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
                SimpleDateFormat date=new SimpleDateFormat("yyyy/MM/dd");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final
                String time=sdf.format(new java.util.Date());
                String date1=date.format(new java.util.Date());
                Platform.runLater(()->{
                    lblTime.setText(time);
                    lblDate.setText(date1);

                });
            }

        });
        thread.start();
    }
    public static void setCloseButton(StackPane rootpane){

        JFXDialogLayout dialogLayout=new JFXDialogLayout();
        dialogLayout.setHeading((new Text("Worning")));
        dialogLayout.setBody(new Text("You Want Exit ?"));
        JFXButton yesbtn=new JFXButton("YES");
        JFXButton nobtn=new JFXButton("NO");
        yesbtn.setOnAction(event -> {
            System.exit(0);
//            thread.stop();
//            Platform.exit();
        });

        yesbtn.setButtonType(JFXButton.ButtonType.RAISED);
        dialogLayout.setActions(yesbtn,nobtn);
        JFXDialog dialog=new JFXDialog(rootpane,dialogLayout, JFXDialog.DialogTransition.BOTTOM);
        nobtn.setOnAction(event -> {
            dialog.close();
        });
        dialog.show();
    }
}
