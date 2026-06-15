package lv.venta.controller;

import lv.venta.model.User;
import lv.venta.model.Status;
import lv.venta.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    // localhost:8080/user/register
    @PostMapping("/register")
    public String postControllerRegisterUser(
            @ModelAttribute User user,
            Model model) {
        try {
            userService.registerUser(user);
            model.addAttribute("box", "User registered successfully.");
            return "success-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }
    
    @GetMapping("/register")
    public String getControllerRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user-page";
    }
    


    // localhost:8080/user/profile/3
    @GetMapping("/profile/{id}")
    public String getControllerUserById(
            @PathVariable(name = "id") int id,
            Model model) {
        try {
            User userFromDB = userService.getUserById(id);
            model.addAttribute("box", userFromDB);
            return "user-profile-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }



    // localhost:8080/user/all
    @GetMapping("/all")
    public String getControllerAllUsers(Model model) {
        try {
            ArrayList<User> allUsersFromDB = userService.getAllUsers();
            model.addAttribute("box", allUsersFromDB);
            return "all-users-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }



    // localhost:8080/user/role/CUSTOMER
    @GetMapping("/role/{role}")
    public String getControllerUsersByRole(
            @PathVariable(name = "role") Status role,
            Model model) {
        try {
            ArrayList<User> usersFromDB = userService.getUsersByRole(role);
            model.addAttribute("box", usersFromDB);
            return "all-users-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }


    // localhost:8080/user/email/john@example.com
    @GetMapping("/email/{email}")
    public String getControllerUserByEmail(
            @PathVariable(name = "email") String email,
            Model model) {
        try {
            User userFromDB = userService.getUserByEmail(email);
            model.addAttribute("box", userFromDB);
            return "user-profile-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }


    // localhost:8080/user/update/3
    @PostMapping("/update/{id}")
    public String postControllerUpdateUser(
            @PathVariable(name = "id") int id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            Model model) {
        try {
            userService.updateUser(id, name, surname, email, password);
            model.addAttribute("box", "User updated successfully.");
            return "success-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }



    // localhost:8080/user/delete/3
    @GetMapping("/delete/{id}")
    public String getControllerDeleteUser(
            @PathVariable(name = "id") int id,
            Model model) {
        try {
            userService.deleteUser(id);
            model.addAttribute("box", "User deleted successfully.");
            return "success-page";

        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }
}