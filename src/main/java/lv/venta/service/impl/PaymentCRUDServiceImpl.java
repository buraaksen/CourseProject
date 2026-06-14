/* package lv.venta.service.impl;

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
	public void createPayment(int reservationId, float amount, PaymentMethod method, boolean isSuccessful)
			throws Exception {

		if (amount < 1.00f || amount > 9999999.99f || method == null) {
			throw new Exception("Input data is incorrect");
		}

		if (!reservationRepo.existsById(reservationId)) {
			throw new Exception("Reservation with id " + reservationId + " doesn't exist");
		}

		Reservation reservation = reservationRepo.findById(reservationId).get();

		Payment payment = new Payment(amount, method, isSuccessful);
		payment.setReservation(reservation);

		paymentRepo.save(payment);
	}

	@Override
	public Payment retrievePaymentById(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("Id should be positive");
		}
		if (!paymentRepo.existsById(id)) {
			throw new Exception("Payment with id " + id + " doesn't exist");
		}
		return paymentRepo.findById(id).get();
	}

	@Override
	public Payment retrievePaymentByReservationId(int reservationId) throws Exception {
		if (reservationId <= 0) {
			throw new Exception("Id should be positive");
		}
		return paymentRepo.findByReservation_IdRES(reservationId)
				.orElseThrow(() -> new Exception("Reservation with id " + reservationId + " has no payment yet"));
	}

	@Override
	public void updatePaymentStatus(int id, boolean isSuccessful) throws Exception {
		Payment paymentFromDB = retrievePaymentById(id);
		paymentFromDB.setSuccesfull(isSuccessful);
		paymentRepo.save(paymentFromDB);
	}

	@Override
	public void deletePaymentById(int id) throws Exception {
		Payment paymentFromDB = retrievePaymentById(id);
		paymentRepo.delete(paymentFromDB);
	}
}

*/