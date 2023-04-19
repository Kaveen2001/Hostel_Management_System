package lk.ijse.hostel.service.custom;

import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.service.SuperService;

import java.io.IOException;
import java.util.ArrayList;

public interface UserService extends SuperService {
    ArrayList<UserDTO> getAllUser() throws IOException;
    boolean updateUser(UserDTO userDTO) throws IOException;
}
