// src/main/java/lv/venta/service/impl/PaymentFilterServiceImpl.java
package lv.venta.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.venta.model.Payment;
import lv.venta.model.PaymentMethod;
import lv.venta.repo.IPaymentRepo;
import lv.venta.service.IPaymentFilterService;

@Service
public class PaymentFilterServiceImpl implements IPaymentFilterService {

    @Autowired
    private IPaymentRepo paymentRepo;

    @Override
    public ArrayList<Payment> filterByReservationId(int reservationId) throws Exception {
        if (reservationId <= 0) throw new Exception("Id must be positive");
        ArrayList<Payment> result = paymentRepo.findByReservation_IdRA(reservationId);
        if (result.isEmpty()) throw new Exception("No payments found for reservation " + reservationId);
        return result;
    }

    @Override
    public ArrayList<Payment> filterBySuccessStatus(boolean successful) throws Exception {
        ArrayList<Payment> result = paymentRepo.findBySuccessful(successful);
        if (result.isEmpty()) throw new Exception("No payments found with status: " + successful);
        return result;
    }

    @Override
    public ArrayList<Payment> filterByPaymentMethod(PaymentMethod paymentMethod) throws Exception {
        if (paymentMethod == null) throw new Exception("Payment method cannot be null");
        ArrayList<Payment> result = paymentRepo.findByPaymentMethod(paymentMethod);
        if (result.isEmpty()) throw new Exception("No payments found with method: " + paymentMethod);
        return result;
    }

    @Override
    public ArrayList<Payment> filterByAmountRange(float minAmount, float maxAmount) throws Exception {
        if (minAmount < 0 || maxAmount <= 0 || minAmount > maxAmount) {
            throw new Exception("Invalid amount range");
        }
        ArrayList<Payment> result = paymentRepo.findByAmountBetween(minAmount, maxAmount);
        if (result.isEmpty()) throw new Exception("No payments found between " + minAmount + " and " + maxAmount);
        return result;
    }
}