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
	public String getControllerFilterByStatus(
			@PathVariable(name = "status") ReservationStatus status,
			Model model) {
		try {
			ArrayList<Reservation> filteredReservationsFromDB =
					reservationService.filterByStatus(status);
			model.addAttribute("box", filteredReservationsFromDB);
			return "all-reservations-page";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

	// localhost:8080/reservation/filter/user/3
	@GetMapping("/user/{userId}")
	public String getControllerFilterByUserId(
			@PathVariable(name = "userId") int userId,
			Model model) {
		try {
			ArrayList<Reservation> filteredReservationsFromDB =
					reservationService.filterByUserId(userId);
			model.addAttribute("box", filteredReservationsFromDB);
			return "all-reservations-page";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

	// localhost:8080/reservation/filter/dates/2025-01-01/2025-12-31
	@GetMapping("/dates/{startDate}/{endDate}")
	public String getControllerFilterByDateRange(
			@PathVariable(name = "startDate") LocalDate startDate,
			@PathVariable(name = "endDate") LocalDate endDate,
			Model model) {
		try {
			ArrayList<Reservation> filteredReservationsFromDB =
					reservationService.filterByDateRange(startDate, endDate);
			model.addAttribute("box", filteredReservationsFromDB);
			return "all-reservations-page";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

}