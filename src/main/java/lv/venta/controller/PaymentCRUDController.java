package lv.venta.controller;

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
import lv.venta.model.Payment;
import lv.venta.model.PaymentMethod;
import lv.venta.service.IPaymentCRUDService;

@Controller
@RequestMapping("/payment/crud")
public class PaymentCRUDController {

	@Autowired
	private IPaymentCRUDService paymentService;

	@GetMapping("/all/{id}") // localhost:8080/payment/crud/all/2
	public String getControllerToGetOnePaymentById(@PathVariable(name = "id") int id, Model model) {
		try {
			Payment paymentFromDB = paymentService.retrievePaymentById(id);
			model.addAttribute("box", paymentFromDB);
			return "payment-page";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/one") // localhost:8080/payment/crud/one?id=2
	public String getControllerToGetOnePaymentById2(@RequestParam(name = "id") int id, Model model) {
		try {
			Payment paymentFromDB = paymentService.retrievePaymentById(id);
			model.addAttribute("box", paymentFromDB);
			return "payment-page";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/reservation/{reservationId}") // localhost:8080/payment/crud/reservation/3
	public String getControllerToGetPaymentByReservationId(
			@PathVariable(name = "reservationId") int reservationId, Model model) {
		try {
			Payment paymentFromDB = paymentService.retrievePaymentByReservationId(reservationId);
			model.addAttribute("box", paymentFromDB);
			return "payment-page";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/delete/{id}") // localhost:8080/payment/crud/delete/2
	public String getControllerForDeletePaymentById(@PathVariable(name = "id") int id, Model model) {
		try {
			paymentService.deletePaymentById(id);
			return "redirect:/";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/add") // localhost:8080/payment/crud/add
	public String getControllerForPaymentAdd(Model model) {
		model.addAttribute("payment", new Payment()); // empty payment is passed
		return "add-payment-page";
	}

	@PostMapping("/add")
	public String postControllerForPaymentAdd(@Valid Payment payment,
			BindingResult problems, Model model,
			@RequestParam(name = "reservationId") int reservationId) {

		if (problems.hasErrors()) {
			return "add-payment-page";
		} else {
			try {
				paymentService.createPayment(reservationId, payment.getAmount(),
						payment.getPayment(), payment.getIsSuccessfull());

				return "redirect:/reservation/crud/all";
			} catch (Exception e) {
				model.addAttribute("box", e.getMessage());
				return "error-page";
			}
		}
	}

	@GetMapping("/update/{id}") // localhost:8080/payment/crud/update/2
	public String getControllerForPaymentUpdateById(@PathVariable(name = "id") int id, Model model) {
		try {
			Payment paymentFromDB = paymentService.retrievePaymentById(id);
			model.addAttribute("payment", paymentFromDB);
			return "update-payment-page";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/update/{id}") // localhost:8080/payment/crud/update/2
	public String postControllerForPaymentUpdateById(@PathVariable(name = "id") int id,
			Model model,
			@RequestParam(name = "isSuccessful") boolean isSuccessful) {
		try {
			paymentService.updatePaymentStatus(id, isSuccessful);

			return "redirect:/reservation/crud/all";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

}