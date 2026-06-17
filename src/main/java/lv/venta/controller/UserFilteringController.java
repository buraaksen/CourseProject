package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lv.venta.model.Status;
import lv.venta.service.IUserFiltering;

@Controller
@RequestMapping("/user/filter")
public class UserFilteringController {

    @Autowired
    private IUserFiltering userFiltering;

    // localhost:8080/user/filter/name/John
    @GetMapping("/name/{name}")
    public String getUsersByName(
            @PathVariable("name") String name,
            Model model) {
        try {
            model.addAttribute("box",
                    userFiltering.filterByName(name));
            return "all-users-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/user/filter/surname/Smith
    @GetMapping("/surname/{surname}")
    public String getUsersBySurname(
            @PathVariable("surname") String surname,
            Model model) {
        try {
            model.addAttribute("box",
                    userFiltering.filterBySurname(surname));
            return "all-users-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/user/filter/status/CLIENT
    @GetMapping("/status/{status}")
    public String getUsersByStatus(
            @PathVariable("status") Status status,
            Model model) {
        try {
            model.addAttribute("box",
                    userFiltering.filterByStatus(status));
            return "all-users-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }
}