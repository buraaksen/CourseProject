package lv.venta.controller;

import lv.venta.model.User;
import lv.venta.model.Status;
import lv.venta.service.IUserCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserCRUDController {

    @Autowired
    private IUserCRUDService userService;

    @GetMapping("/all") // localhost:8080/user/all
    public String getAllUsers(Model model) {
        try {
            model.addAttribute("box", userService.retrieveAllUsers());
            return "all-users-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }
    
    @GetMapping("/profile/{id}") // localhost:8080/user/profile/3 
    public String getUserById(@PathVariable(name = "id") int id, Model model) {
        try {
            model.addAttribute("box", userService.retrieveUserById(id));
            return "user-profile-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/role/{role}") // localhost:8080/user/role/CLIENT
    public String getUsersByRole(@PathVariable(name = "role") Status role, Model model) {
        try {
            model.addAttribute("box", userService.retrieveUsersByStatus(role));
            return "all-users-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/email/{email}") // localhost:8080/user/email/coulibaly@gmail.com
    public String getUserByEmail(@PathVariable(name = "email") String email, Model model) {
        try {
            model.addAttribute("box", userService.retrieveUserByEmail(email));
            return "user-profile-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/remove/{id}") // localhost:8080/user/remove/
    public String removeUserById(@PathVariable(name = "id") int id, Model model) {
        try {
            userService.deleteUserById(id);
            return "redirect:/user/all";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/add")
    public String showInsertPage() {
        return "add-user-page";
    }

    @PostMapping("/add")
    public String insertUser(@RequestParam String name,
            @RequestParam String surname,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam Status status,
            Model model) {
        try {
            userService.createUser(name, surname, email, password, status);
            return "redirect:/user/all";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/update/{id}") // localhost:8080/user/update/3
    public String showUpdatePage(@PathVariable(name = "id") int id, Model model) {
        try {
            model.addAttribute("box", userService.retrieveUserById(id));
            return "update-user-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable(name = "id") int id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam Status status,
            Model model) {
        try {
            userService.updateUserById(id, name, surname, email, password, status);
            return "redirect:/user/all";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }
}