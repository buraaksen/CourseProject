package lv.venta.service;

import lv.venta.model.User;
import lv.venta.model.Status;
import java.util.ArrayList;

public interface IUserService {

    // CREATE
    void registerUser(User user) throws Exception;

    // READ
    User getUserById(int id) throws Exception;
    ArrayList<User> getAllUsers();
    ArrayList<User> getUsersByRole(Status role);
    User getUserByEmail(String email) throws Exception;

    // UPDATE
    void updateUser(int id, String name, String surname, String email, String password) throws Exception;

    // DELETE
    void deleteUser(int id);
}