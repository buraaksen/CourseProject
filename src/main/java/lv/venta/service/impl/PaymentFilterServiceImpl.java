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
	public ArrayList<Payment> filterBySuccessStatus(boolean isSuccessful) throws Exception {
		ArrayList<Payment> result = paymentRepo.findBySuccessStatus(isSuccessful);

		if (result.isEmpty()) {
			throw new Exception("There are no payments with success status " + isSuccessful);
		}
		return result;
	}

	@Override
	public ArrayList<Payment> filterByPaymentMethod(PaymentMethod method) throws Exception {
		if (method == null) {
			throw new Exception("Wrong input param");
		}
		ArrayList<Payment> result = paymentRepo.findByPayment(method);

		if (result.isEmpty()) {
			throw new Exception("There are no payments with method " + method);
		}
		return result;
	}

	@Override
	public ArrayList<Payment> filterByAmountRange(float minAmount, float maxAmount) throws Exception {
		if (minAmount < 0 || maxAmount <= 0 || minAmount > maxAmount) {
			throw new Exception("Wrong input param");
		}
		ArrayList<Payment> result = paymentRepo.findByAmountBetween(minAmount, maxAmount);

		if (result.isEmpty()) {
			throw new Exception("There are no payments with amount between " + minAmount + " and " + maxAmount);
		}
		return result;
	}
}