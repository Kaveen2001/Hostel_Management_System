package lk.ijse.hostel.dto;

import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomDTO {
    String student_Id;
    String name;
    String address;
    String contact_No;
    LocalDate dob;
    String gender;

    String reservation_Id;
    LocalDate reservation_Date;

    Student reg_Student_Id;
    Room reg_Room_Id;
    String status;

    private String room_Type_Id;
    private String room_Type;
    private double key_Money;
    private int room_Qty;
}
