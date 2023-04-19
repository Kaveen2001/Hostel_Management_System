package lk.ijse.hostel.dto;

import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    String reservation_Id;
    LocalDate reservation_Date;
    Student student_Id;
    Room room_Type_Id;
    String status;
}
