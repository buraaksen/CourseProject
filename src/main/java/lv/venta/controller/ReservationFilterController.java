
package lv.venta.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import lv.venta.model.Reservation;
import lv.venta.model.ReservationStatus;
import lv.venta.service.IReservationFilterService;

@Controller
@RequestMapping("/reservation/filter")
public class ReservationFilterController {

    @Autowired
    private IReservationFilterService reservationService;

    // localhost:8080/reservation/filter/status/Pending
    @GetMapping("/status/{status}")
    public String filterByStatus(@PathVariable String status, Model model) {
        try {
            ReservationStatus rs = ReservationStatus.valueOf(status);
            ArrayList<Reservation> result = reservationService.filterByStatus(rs);
            model.addAttribute("box", result);
            model.addAttribute("statuses", ReservationStatus.values());
            return "all-reservations-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/reservation/filter/user/1
    @GetMapping("/user/{userId}")
    public String filterByUser(@PathVariable int userId, Model model) {
        try {
            ArrayList<Reservation> result = reservationService.filterByUserId(userId);
            model.addAttribute("box", result);
            model.addAttribute("statuses", ReservationStatus.values());
            return "all-reservations-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/reservation/filter/dates/2025-01-01/2025-12-31
    @GetMapping("/dates/{startDate}/{endDate}")
    public String filterByDates(@PathVariable String startDate,
                                @PathVariable String endDate, Model model) {
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            ArrayList<Reservation> result = reservationService.filterByDateRange(start, end);
            model.addAttribute("box", result);
            model.addAttribute("statuses", ReservationStatus.values());
            return "all-reservations-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }
}