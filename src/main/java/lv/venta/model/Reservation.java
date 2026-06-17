package lv.venta.model;

import java.time.LocalDate;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;



@Table(name = "ReservationTable")
@Entity
public class Reservation {
 
    @Column(name = "idRA")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idRA;
 
    
 
    @NotNull
    @Column(name = "StartDate")
    private LocalDate startDate;
 
    @NotNull
    @Column(name = "EndDate")
    private LocalDate endDate;
 
    @Column(name = "TotalPrice")
    private float totalPrice;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private ReservationStatus status;
    
    @ManyToOne
    @JoinColumn(name = "idU")
    private User users;
 	
    @ManyToOne
    @JoinColumn(name = "idRoom")
    private Room rooms;
    
    @OneToOne
    @JoinColumn(name = "idPay")
    private Payment payments;
    
 
    public int getIdRES() {
        return idRA;
    }
 
   public User getUser() {
        return users;
    }
 
    public void setUser(User user) {
        this.users = user;
    }
 
    public Room getRoom() {
        return rooms;
    }
 
    public void setRoom(Room room) {
        this.rooms = room;
    }
 
    public LocalDate getStartDate() {
        return startDate;
    }
 
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
 
    public LocalDate getEndDate() {
        return endDate;
    }
 
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
 
    public float getTotalPrice() {
        return totalPrice;
    }
 
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
 
    public ReservationStatus getStatus() {
        return status;
    }
 
    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
 
    public Reservation() {}
 
   public Reservation(User user, Room room, LocalDate startDate, LocalDate endDate, float totalPrice, ReservationStatus status) {
        setUser(user);
        setRoom(room);
        setStartDate(startDate);
        setEndDate(endDate);
        setTotalPrice(totalPrice);
        setStatus(status);
    }
 
    @Override
    public String toString() {
        return idRA + " User: [" + users + "] Room: [" + rooms + "] Start: " + startDate + " End: " + endDate + " Price: " + totalPrice + " Status: " + status;
    }
    
}