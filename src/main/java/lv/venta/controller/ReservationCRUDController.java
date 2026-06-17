
package lv.venta.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lv.venta.model.Reservation;
import lv.venta.model.ReservationStatus;
import lv.venta.service.IReservationCRUDService;

@Controller
@RequestMapping("/reservation/crud")
public class ReservationCRUDController {

    @Autowired
    private IReservationCRUDService reservationService;

    // localhost:8080/reservation/crud/all
    @GetMapping("/all")
    public String getAll(Model model) {
        try {
            ArrayList<Reservation> list = reservationService.retrieveAllReservations();
            model.addAttribute("box", list);
            model.addAttribute("statuses", ReservationStatus.values());
            return "all-reservations-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/reservation/crud/all/2
    @GetMapping("/all/{id}")
    public String getOneById(@PathVariable int id, Model model) {
        try {
            model.addAttribute("box", reservationService.retrieveReservationById(id));
            return "reservation-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/reservation/crud/one?id=2
    @GetMapping("/one")
    public String getOneByParam(@RequestParam int id, Model model) {
        try {
            model.addAttribute("box", reservationService.retrieveReservationById(id));
            return "reservation-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/reservation/crud/add  (GET)
    @GetMapping("/add")
    public String showAddForm(Model model) {
     
        model.addAttribute("reservation", new Reservation());
        return "add-reservation-page";
    }

    // localhost:8080/reservation/crud/add  (POST)
    @PostMapping("/add")
    public String submitAdd(
            @RequestParam int userId,
            @RequestParam int roomId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            Model model) {
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            reservationService.createReservation(userId, roomId, start, end);
            ArrayList<Reservation> list = reservationService.retrieveAllReservations();
            model.addAttribute("box", list);
            model.addAttribute("statuses", ReservationStatus.values());
            return "all-reservations-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/reservation/crud/update/2  (GET)
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        try {
            model.addAttribute("box", reservationService.retrieveReservationById(id));
            model.addAttribute("statuses", ReservationStatus.values());
            return "update-reservation-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/reservation/crud/update/2  (POST)
    @PostMapping("/update/{id}")
    public String submitUpdate(
            @PathVariable int id,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String status,
            Model model) {
        try {
        	LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            ReservationStatus rs = ReservationStatus.valueOf(status);
            reservationService.updateReservationById(id, start, end, rs);

            ArrayList<Reservation> list = reservationService.retrieveAllReservations();
            model.addAttribute("box", list);
            model.addAttribute("statuses", ReservationStatus.values());
            return "all-reservations-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/reservation/crud/delete/2
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        try {
            reservationService.deleteReservationById(id);

            ArrayList<Reservation> list = reservationService.retrieveAllReservations();
            model.addAttribute("box", list);
            model.addAttribute("statuses", ReservationStatus.values());
            return "all-reservations-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }
}