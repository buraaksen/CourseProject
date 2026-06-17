
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
    @GetMapping("/status/{successful}")
    public String filterByStatus(@PathVariable boolean successful, Model model) {
        try {
            ArrayList<Payment> result = paymentService.filterBySuccessStatus(successful);
            model.addAttribute("box", result);
            return "all-payments-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/payment/filter/method/card
    @GetMapping("/method/{method}")
    public String filterByMethod(@PathVariable String method, Model model) {
        try {
            PaymentMethod pm = PaymentMethod.valueOf(method);
            ArrayList<Payment> result = paymentService.filterByPaymentMethod(pm);
            model.addAttribute("box", result);
            return "all-payments-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }

    // localhost:8080/payment/filter/amount/50/500
    @GetMapping("/amount/{min}/{max}")
    public String filterByAmount(@PathVariable float min, @PathVariable float max, Model model) {
        try {
            ArrayList<Payment> result = paymentService.filterByAmountRange(min, max);
            model.addAttribute("box", result);
            return "all-payments-page";
        } catch (Exception e) {
            model.addAttribute("box", e.getMessage());
            return "error-page";
        }
    }
}