
package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lv.venta.model.PaymentMethod;
import lv.venta.service.IPaymentCRUDService;

@Controller
@RequestMapping("/payment/crud")
public class PaymentCRUDController {

    @Autowired
    private IPaymentCRUDService paymentService;

    // localhost:8080/payment/crud/all/2
    @GetMapping("/all/{id}")
    public String getOneById(@PathVariable int id, Model model) {
        try {
            model.addAttribute("box", paymentService.retrievePaymentById(id));
            return "payment-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/payment/crud/one?id=2
    @GetMapping("/one")
    public String getOneByParam(@RequestParam int id, Model model) {
        try {
            model.addAttribute("box", paymentService.retrievePaymentById(id));
            return "payment-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/payment/crud/reservation/3
    @GetMapping("/reservation/{reservationId}")
    public String getByReservationId(@PathVariable int reservationId, Model model) {
        try {
            model.addAttribute("box", paymentService.retrievePaymentByReservationId(reservationId));
            return "payment-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/payment/crud/add  (GET)
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("methods", PaymentMethod.values());
        return "add-payment-page";
    }

    // localhost:8080/payment/crud/add  (POST)
    @PostMapping("/add")
    public String submitAdd(
            @RequestParam float amount,
            @RequestParam String paymentMethod,
            @RequestParam boolean successful,
            @RequestParam int reservationId,
            Model model) {
        try {
            PaymentMethod method = PaymentMethod.valueOf(paymentMethod);
            paymentService.createPayment(reservationId, amount, method, successful);
            return "redirect:/reservation/crud/all";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/payment/crud/update/2  (GET)
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        try {
            model.addAttribute("box", paymentService.retrievePaymentById(id));
            return "update-payment-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/payment/crud/update/2  (POST)
    @PostMapping("/update/{id}")
    public String submitUpdate(
            @PathVariable int id,
            @RequestParam boolean successful,
            Model model) {
        try {
            paymentService.updatePaymentStatus(id, successful);
            return "redirect:/reservation/crud/all";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/payment/crud/delete/2
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        try {
            paymentService.deletePaymentById(id);
            return "redirect:/reservation/crud/all";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }
}