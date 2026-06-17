
package lv.venta.service;

import java.util.ArrayList;
import lv.venta.model.Payment;
import lv.venta.model.PaymentMethod;

public interface IPaymentFilterService {

    ArrayList<Payment> filterByReservationId(int reservationId) throws Exception;

    ArrayList<Payment> filterBySuccessStatus(boolean successful) throws Exception;

    ArrayList<Payment> filterByPaymentMethod(PaymentMethod paymentMethod) throws Exception;

    ArrayList<Payment> filterByAmountRange(float minAmount, float maxAmount) throws Exception;
}