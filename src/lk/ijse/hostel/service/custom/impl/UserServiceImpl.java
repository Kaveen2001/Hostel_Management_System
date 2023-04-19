package lk.ijse.hostel.service.custom.impl;

import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.DAOTypes;
import lk.ijse.hostel.entity.User;
import lk.ijse.hostel.service.custom.UserService;
import lk.ijse.hostel.dao.custom.UserDAO;
import lk.ijse.hostel.dto.UserDTO;

import java.io.IOException;
import java.util.ArrayList;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.USER);

    @Override
    public ArrayList<UserDTO> getAllUser() throws IOException {
        ArrayList<User> all = userDAO.getAll();
        ArrayList<UserDTO> allUSer = new ArrayList<>();
        for (User user : all) {
            allUSer.add(new UserDTO(user.getUser_Id(),user.getUser_Name(),user.getPassword()));
        }
        return allUSer;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws IOException {
        return userDAO.update(new User(userDTO.getUser_Id(),userDTO.getUser_Name(),userDTO.getPassword()));
    }
}
