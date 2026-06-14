package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Payment;

public interface IPaymentRepo extends CrudRepository<Payment, Integer>{
	

}
