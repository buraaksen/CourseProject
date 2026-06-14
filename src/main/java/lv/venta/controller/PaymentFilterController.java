package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.model.Payment;
import lv.venta.model.PaymentMethod;
import lv.venta.service.IPaymentFilterService;

@Controller
@RequestMapping("/payment/filter")
public class PaymentFilterController {

	@Autowired
	private IPaymentFilterService paymentService;

	// localhost:8080/payment/filter/status/true
	@GetMapping("/status/{isSuccessful}")
	public String getControllerFilterBySuccessStatus(
			@PathVariable(name = "isSuccessful") boolean isSuccessful,
			Model model) {
		try {
			ArrayList<Payment> filteredPaymentsFromDB =
					paymentService.filterBySuccessStatus(isSuccessful);
			model.addAttribute("box", filteredPaymentsFromDB);
			return "all-payments-page";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

	// localhost:8080/payment/filter/method/card
	@GetMapping("/method/{method}")
	public String getControllerFilterByPaymentMethod(
			@PathVariable(name = "method") PaymentMethod method,
			Model model) {
		try {
			ArrayList<Payment> filteredPaymentsFromDB =
					paymentService.filterByPaymentMethod(method);
			model.addAttribute("box", filteredPaymentsFromDB);
			return "all-payments-page";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

	// localhost:8080/payment/filter/amount/50/500
	@GetMapping("/amount/{minAmount}/{maxAmount}")
	public String getControllerFilterByAmountRange(
			@PathVariable(name = "minAmount") float minAmount,
			@PathVariable(name = "maxAmount") float maxAmount,
			Model model) {
		try {
			ArrayList<Payment> filteredPaymentsFromDB =
					paymentService.filterByAmountRange(minAmount, maxAmount);
			model.addAttribute("box", filteredPaymentsFromDB);
			return "all-payments-page";
		} catch (Exception e) {
			model.addAttribute("box", e.getMessage());
			return "error-page";
		}
	}

}