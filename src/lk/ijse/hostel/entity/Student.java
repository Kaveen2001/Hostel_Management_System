package lk.ijse.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Student")
public class Student {
    @Id
    @Column(name = "Student_Id")
    private String student_Id;
    @Column(name = "Student_Name",length = 150,nullable = false)
    private String name;
    @Column(name = "Student_Address",columnDefinition = "TEXT")
    private String address;
    @Column(name = "Student_MobileNo")
    private String contact_No;
    @Column(name = "Student_Birthday")
    private LocalDate dob;
    @Column(name = "Student_Gender")
    private String gender;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Reservation> reservation;

    public Student(String student_Id, String name, String address, String contact_No, LocalDate dob, String gender) {
        this.student_Id = student_Id;
        this.name = name;
        this.address = address;
        this.contact_No = contact_No;
        this.dob = dob;
        this.gender = gender;
    }
}
