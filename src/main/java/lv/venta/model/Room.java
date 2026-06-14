package lv.venta.model;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Room")
public class Room {
	
	//variable
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private int RoId; 
	
	@ManyToOne
	@JoinColumn(name = "prId")
	 private Property PrId;
	 
	 @NotNull
	 @NotEmpty
	 @Pattern(regexp = "^[1-9][0-9]?|100$",
	        message = " Room number should be between 1 and 100")
	 private String room_number;
	 
	@Min(1)
	@Max(10)
	 private int capacity;
	 
	@Min(1)
	@Max(1000)
	 private float pricePerNight;
	 
	 @OneToMany
	 @JoinColumn(name = "idRes")
	 private Reservation reservations;
	 
	 //setter and getter
		public Property getPrId() {
			return PrId;
		}

		public void setPrId(Property prId) {
			PrId = prId;
		}

		public int getRoId() {
			return RoId;
		}

		public void setRoId(int roId) {
			RoId = roId;
		}
	 public String getRoom_number() {
		 return room_number;
	 }
	 public void setRoom_number(String room_number) {
		 this.room_number = room_number;
	 }
	 public int getCapacity() {
		 return capacity;
	 }
	 public void setCapacity(int capacity) {
		 this.capacity = capacity;
	 }
	 public float getPricePerNight() {
		 return pricePerNight;
	 }
	 public void setPricePerNight(float pricePerNight) {
		 this.pricePerNight = pricePerNight;
	 } 
	 
	 //constructor
		public Room(Property PrId, String room_number, int capacity, float pricePerNight) {
			this.PrId = PrId;
			this.room_number = room_number;
			this.capacity = capacity;
			this.pricePerNight = pricePerNight;
		}
		
		//not arg-constructor
		public Room() {

		}
		
		//toString method
		@Override
		public String toString() {
			return "Room [room_number=" + room_number + ", capacity=" + capacity + ", pricePerNight=" + pricePerNight
					+ "]";
		}
	 

	 
	 
	 


}
