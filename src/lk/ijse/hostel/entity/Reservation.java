package lk.ijse.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Reservation")
public class Reservation {
    @Id
    @Column(name = "Reserve_Id")
    private String reservation_Id;
    @Column(name = "Reserve_Date")
    private LocalDate reservation_Date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_Id",referencedColumnName = "student_Id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_Type_Id",referencedColumnName = "room_Type_Id")
    private Room room;
    private String status;
}
