
package lv.venta.repo;

import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import lv.venta.model.Payment;
import lv.venta.model.PaymentMethod;

public interface IPaymentRepo extends CrudRepository<Payment, Integer> {

    ArrayList<Payment> findByReservation_IdRA(int reservationId);

    ArrayList<Payment> findBySuccessful(boolean successful);

    ArrayList<Payment> findByPaymentMethod(PaymentMethod paymentMethod);

    ArrayList<Payment> findByAmountBetween(float minAmount, float maxAmount);
}