package lv.venta.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.model.Reservation;
import lv.venta.model.ReservationStatus;
import lv.venta.service.IReservationCRUDService;

@Controller
@RequestMapping("/reservation/crud")
public class ReservationCRUDController {

	@Autowired
	private IReservationCRUDService reservationService;

	@GetMapping("/all") // localhost:8080/reservation/crud/all
	public String getControllerToGetAllReservations(Model model) {
		try {
			ArrayList<Reservation> reservationsFromDB = reservationService.retrieveAllReservations();
			model.addAttribute("box", reservationsFromDB);
			return "all-reservations-page";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/all/{id}") // localhost:8080/reservation/crud/all/2
	public String getControllerToGetOneReservationById(@PathVariable(name = "id") int id, Model model) {
		try {
			Reservation reservationFromDB = reservationService.retrieveReservationById(id);
			model.addAttribute("box", reservationFromDB);
			return "reservation-page";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/one") // localhost:8080/reservation/crud/one?id=2
	public String getControllerToGetOneReservationById2(@RequestParam(name = "id") int id, Model model) {
		try {
			Reservation reservationFromDB = reservationService.retrieveReservationById(id);
			model.addAttribute("box", reservationFromDB);
			return "reservation-page";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/delete/{id}") // localhost:8080/reservation/crud/delete/2
	public String getControllerForDeleteReservationById(@PathVariable(name = "id") int id, Model model) {
		try {
			reservationService.deleteReservationById(id);

			ArrayList<Reservation> reservationsFromDB = reservationService.retrieveAllReservations();
			model.addAttribute("box", reservationsFromDB);
			return "all-reservations-page";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/add") // localhost:8080/reservation/crud/add
	public String getControllerForReservationAdd(Model model) {
		model.addAttribute("reservation", new Reservation()); // empty reservation is passed
		return "add-reservation-page";
	}

	@PostMapping("/add")
	public String postControllerForReservationAdd(@Valid Reservation reservation,
			BindingResult problems, Model model,
			@RequestParam(name = "userId") int userId,
			@RequestParam(name = "roomId") int roomId) {

		if (problems.hasErrors()) {
			return "add-reservation-page";
		} else {
			try {
				reservationService.createReservation(userId, roomId,
						reservation.getStartDate(), reservation.getEndDate());

				return "redirect:/reservation/crud/all";
			} catch (Exception e) {
				model.addAttribute("box", e.getMessage());
				return "error-page";
			}
		}
	}

	@GetMapping("/update/{id}") // localhost:8080/reservation/crud/update/2
	public String getControllerForReservationUpdateById(@PathVariable(name = "id") int id, Model model) {
		try {
			Reservation reservationFromDB = reservationService.retrieveReservationById(id);
			model.addAttribute("reservation", reservationFromDB);
			return "update-reservation-page";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/update/{id}") // localhost:8080/reservation/crud/update/2
	public String postControllerForReservationUpdateById(@PathVariable(name = "id") int id,
			Model model, Reservation reservation,
			@RequestParam(name = "status") ReservationStatus status) {
		try {
			reservationService.updateReservationById(id,
					reservation.getStartDate(), reservation.getEndDate(), status);

			return "redirect:/reservation/crud/all";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

}