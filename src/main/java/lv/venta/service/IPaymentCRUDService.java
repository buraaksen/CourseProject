package lv.venta.service;

import lv.venta.model.Payment;
import lv.venta.model.PaymentMethod;

public interface IPaymentCRUDService {

	// C - create (generate payment record linked to a reservation)
	public abstract void createPayment(int reservationId, float amount, PaymentMethod method, boolean isSuccessful)
			throws Exception;

	// R - retrieve by id (check a specific receipt)
	public abstract Payment retrievePaymentById(int id) throws Exception;

	// R - retrieve by reservation id (check if reservation has been paid)
	public abstract Payment retrievePaymentByReservationId(int reservationId) throws Exception;

	// U - update payment status (false -> true)
	public abstract void updatePaymentStatus(int id, boolean isSuccessful) throws Exception;

	// D - delete by id
	public abstract void deletePaymentById(int id) throws Exception;
}