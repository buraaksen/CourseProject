package lv.venta.repo;

import java.util.ArrayList;


import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Payment;
import lv.venta.model.PaymentMethod;

public interface IPaymentRepo extends CrudRepository<Payment, Integer> {
	
	ArrayList<Payment> findByReservation_IdRES(int reservationId);

	ArrayList<Payment> findByIsSuccessfull(boolean isSuccessfull);

	ArrayList<Payment> findByPayment(PaymentMethod method);

	ArrayList<Payment> findByAmountBetween(float minAmount, float maxAmount);

}
