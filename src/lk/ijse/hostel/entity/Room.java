package lk.ijse.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "Room")
public class Room {
    @Id
    @Column(name = "Room_Id")
    private String room_Type_Id;
    @Column(name = "Room_Type")
    private String room_Type;
    @Column(name = "Room_KeyMoney")
    private double key_Money;
    @Column(name = "Room_Qty")
    private int room_Qty;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private List<Reservation> roomDetail;

    public Room(String room_Type_Id, String room_Type, double key_Money, int room_Qty) {
        this.room_Type_Id = room_Type_Id;
        this.room_Type = room_Type;
        this.key_Money = key_Money;
        this.room_Qty = room_Qty;
    }
}
