package lk.ijse.hostel.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RemainKeyMoneyStudentsTM {
    private String student_Id;
    private String name;
    private String reservation_Id;
    private String room_Type_Id;
    private double paid_KeyMoney_Fee;
    private double remain_KeyMoney_Fee;
    private LocalDate arrival_Date;
    private LocalDate departure_Date;
}
