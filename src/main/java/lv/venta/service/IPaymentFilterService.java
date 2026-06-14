package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Payment;
import lv.venta.model.PaymentMethod;

public interface IPaymentFilterService {

	public abstract ArrayList<Payment> filterBySuccessStatus(boolean isSuccessful) throws Exception;

	public abstract ArrayList<Payment> filterByPaymentMethod(PaymentMethod method) throws Exception;

	public abstract ArrayList<Payment> filterByAmountRange(float minAmount, float maxAmount) throws Exception;
}