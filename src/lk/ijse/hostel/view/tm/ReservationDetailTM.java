package lk.ijse.hostel.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDetailTM {
    private String reservation_Id;
    private LocalDate reservation_Date;
    private String student_Id;
    private String room_Type_Id;
    private String status;
    private double remain_keyMoney_fee;
}
