// src/main/java/lv/venta/service/impl/PaymentCRUDServiceImpl.java
package lv.venta.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.venta.model.Payment;
import lv.venta.model.PaymentMethod;
import lv.venta.model.Reservation;
import lv.venta.repo.IPaymentRepo;
import lv.venta.repo.IReservationRepo;
import lv.venta.service.IPaymentCRUDService;

@Service
public class PaymentCRUDServiceImpl implements IPaymentCRUDService {

    @Autowired
    private IPaymentRepo paymentRepo;

    @Autowired
    private IReservationRepo reservationRepo;

    @Override
    public void createPayment(int reservationId, float amount, PaymentMethod paymentMethod, boolean successful)
            throws Exception {

        if (amount < 1.0f) {
            throw new Exception("Amount must be at least 1.00");
        }
        if (paymentMethod == null) {
            throw new Exception("Payment method cannot be null");
        }
        if (!reservationRepo.existsById(reservationId)) {
            throw new Exception("Reservation with id " + reservationId + " does not exist");
        }

        Reservation reservation = reservationRepo.findById(reservationId).get();
        Payment payment = new Payment(amount, paymentMethod, successful, reservation);
        paymentRepo.save(payment);
    }

    @Override
    public Payment retrievePaymentById(int id) throws Exception {
        if (id <= 0) throw new Exception("Id must be positive");
        if (!paymentRepo.existsById(id)) throw new Exception("Payment with id " + id + " does not exist");
        return paymentRepo.findById(id).get();
    }

    @Override
    public Payment retrievePaymentByReservationId(int reservationId) throws Exception {
        if (reservationId <= 0) throw new Exception("Id must be positive");
        ArrayList<Payment> result = paymentRepo.findByReservation_IdRA(reservationId);
        if (result == null || result.isEmpty()) {
            throw new Exception("No payment found for reservation id " + reservationId);
        }
        return result.get(0);
    }

    @Override
    public void updatePaymentStatus(int id, boolean successful) throws Exception {
        Payment payment = retrievePaymentById(id);
        payment.setSuccessful(successful);
        paymentRepo.save(payment);
    }

    @Override
    public void deletePaymentById(int id) throws Exception {
        Payment payment = retrievePaymentById(id);
        paymentRepo.delete(payment);
    }
}