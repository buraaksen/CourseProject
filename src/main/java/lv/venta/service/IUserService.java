package lv.venta.service;

import lv.venta.model.User;
import lv.venta.model.Status;
import java.util.ArrayList;

public interface IUserService {

    // CREATE
    void registerUser(User user);

    // READ
    User getUserById(int id);
    ArrayList<User> getAllUsers();
    ArrayList<User> getUsersByRole(Status role);
    User getUserByEmail(String email);

    // UPDATE
    void updateUser(int id, String name, String surname, String email, String password);

    // DELETE
    void deleteUser(int id);
}