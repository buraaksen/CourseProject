
package lv.venta.service;

import lv.venta.model.Payment;
import lv.venta.model.PaymentMethod;

public interface IPaymentCRUDService {

    void createPayment(int reservationId, float amount, PaymentMethod paymentMethod, boolean successful) throws Exception;

    Payment retrievePaymentById(int id) throws Exception;

    Payment retrievePaymentByReservationId(int reservationId) throws Exception;

    void updatePaymentStatus(int id, boolean successful) throws Exception;

    void deletePaymentById(int id) throws Exception;
}