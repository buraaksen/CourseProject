package lv.venta.repo;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import lv.venta.model.Payment;
import lv.venta.model.PaymentMethod;

public interface IPaymentRepo extends CrudRepository<Payment, Integer> {

	Optional<Payment> findByReservation_IdRES(int reservationId);

	@Query("SELECT p FROM Payment p WHERE p.isSuccesfull = :isSuccessful")
	ArrayList<Payment> findBySuccessStatus(@Param("isSuccessful") boolean isSuccessful);

	ArrayList<Payment> findByPayment(PaymentMethod method);

	ArrayList<Payment> findByAmountBetween(float minAmount, float maxAmount);
}