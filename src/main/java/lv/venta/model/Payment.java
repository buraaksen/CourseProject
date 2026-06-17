
package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "PaymentTable")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDU")
    private int idPay;

    @Column(name = "Amount")
    private float amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "PaymentMethod")
    private PaymentMethod paymentMethod;

    @Column(name = "PaymentStatus")
    private boolean successful;

    @ManyToOne
    @JoinColumn(name = "idRA")
    private Reservation reservation;

    public int getIdPay() {
    	return idPay; 
    	}

    public float getAmount() { 
    	return amount; 
    	}
    public void setAmount(float amount) { 
    	this.amount = amount; 
    	}

    public PaymentMethod getPaymentMethod() { 
    	return paymentMethod; 
    	}
    public void setPaymentMethod(PaymentMethod paymentMethod) {
    	this.paymentMethod = paymentMethod; 
    	}

    public boolean isSuccessful() {
    	return successful; 
    	}
    public void setSuccessful(boolean successful) {
    	this.successful = successful;
    	}

    public Reservation getReservation() {
    	return reservation; 
    	}
    public void setReservation(Reservation reservation) {
    	this.reservation = reservation;
    	}
    
    public Payment() {}

    public Payment(float amount, PaymentMethod paymentMethod, boolean successful, Reservation reservation) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.successful = successful;
        this.reservation = reservation;
    }


    @Override
    public String toString() {
        return "[" + idPay + "] " + amount + "€ by " + paymentMethod + " | Success: " + successful;
    }
}