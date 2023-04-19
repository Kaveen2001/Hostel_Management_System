package lk.ijse.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomEntity {
    String student_Id;
    String name;
    String address;
    String contact_No;
    LocalDate dob;
    String gender;

    private String room_Type_Id;
    private String room_Type;
    private double key_Money;
    private int room_Qty;

    String reservation_Id;
    LocalDate reservation_Date;
    Student reg_Student_Id;
    Room reg_Room_Id;
    String status;
}
