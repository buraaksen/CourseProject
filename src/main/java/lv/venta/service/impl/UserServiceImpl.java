package lv.venta.service.impl;

import lv.venta.model.User;
import lv.venta.model.Status;
import lv.venta.repo.IUserRepo;
import lv.venta.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepo userRepo;

    // CREATE
    @Override
    public void registerUser(User user) {
        userRepo.save(user);
    }

    // READ
    @Override
    public User getUserById(int id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        userRepo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public ArrayList<User> getUsersByRole(Status role) {
        ArrayList<User> list = new ArrayList<>();
        userRepo.findByRole(role).forEach(list::add);
        return list;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }
        return user;
    }

    // UPDATE
    @Override
    public void updateUser(int id, String name, String surname, String email, String password) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setName(name);
        existingUser.setSurname(surname);
        existingUser.setEmail(email);
        existingUser.setPassword(password);

        userRepo.save(existingUser);
    }

    // DELETE
    @Override
    public void deleteUser(int id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepo.deleteById(id);
    }
}