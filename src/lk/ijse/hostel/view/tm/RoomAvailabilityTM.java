package lk.ijse.hostel.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomAvailabilityTM {
    private String room_Type_Id;
    private String room_Type;
    private String key_Money;
    private int available_Rooms_Qty;
    private int unavailable_Rooms_Qty;
}
