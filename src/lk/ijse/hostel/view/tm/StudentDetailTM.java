package lk.ijse.hostel.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDetailTM {
    private String reservation_Id;
    private String student_Id;
    private String name;
    private String address;
    private String contact_No;
    private LocalDate dob;
    private String gender;
    private LocalDate arrival_Date;
    private LocalDate departure_Date;
}
