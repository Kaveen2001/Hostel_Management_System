package lk.ijse.hostel.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentTM {
    private String student_Id;
    private String name;
    private String address;
    private String contact_No;
    private LocalDate dob;
    private String gender;
}
