package lv.venta.model;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
@Entity
@Table(name = "PaymentTable")
public class Payment {
	
	@Column(name = "IDU")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPay; 
	
	@Column(name = "Amount")
	@DecimalMin(value = "1.00")
	@DecimalMax(value = "9999999.99")
	private float amount;
	
	@Column(name = "PaymentMethod")
	@Enumerated(EnumType.STRING)
	private PaymentMethod payment;
	
	@Column(name = "PaymentStatus")
	boolean isSuccessfull;
	
	@OneToOne()
	private Reservation reservation;
	
	
//Getters and Setters

	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public PaymentMethod getPayment() {
		return payment;
	}
	public void setPayment(PaymentMethod payment) {
		this.payment = payment;
	}
	public boolean getIsSuccessfull() {
		return isSuccessfull;
	}
	public void setSuccessfull(boolean isSuccessfull) {
		this.isSuccessfull = isSuccessfull;
	}
	public int getIdPay() {
		return idPay;
	}
	
	public Reservation getReservation() {
	    return reservation;
	}
	public void setReservation(Reservation reservation) {
	    this.reservation = reservation;
	}
// Contructors
	public Payment() {}
	public Payment(float amount, PaymentMethod payment, boolean isSuccesfull) {
		setAmount(amount);
		setPayment(payment);
		setSuccessfull(isSuccesfull);
	} 
//toString
	public String toString() {
		return "["+ idPay + "] "+ amount + "€ " + "by "+ payment + "Status: "+ isSuccessfull;
	}
}
