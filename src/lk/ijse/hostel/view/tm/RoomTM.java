package lk.ijse.hostel.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomTM {
    private String room_Type_Id;
    private String room_Type;
    private double key_Money;
    private int room_Qty;
}
