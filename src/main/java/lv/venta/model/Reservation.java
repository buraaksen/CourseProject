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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;



@Table(name = "ReservationTable")
@Entity
public class Reservation {
 
    @Column(name = "idRES")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idRES;
 
    @ManyToOne
    @JoinColumn(name = "idU")
    private User user;
 	
 
    @ManyToOne
    @JoinColumn(name = "idRO")
    private Room room;
 
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
 
    public int getIdRES() {
        return idRES;
    }
 
   public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
 
    public Room getRoom() {
        return room;
    }
 
    public void setRoom(Room room) {
        this.room = room;
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
        return idRES + " User: [" + user + "] Room: [" + room + "] Start: " + startDate + " End: " + endDate + " Price: " + totalPrice + " Status: " + status;
    }
    
}