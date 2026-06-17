package lv.venta.model;

import java.util.Collection;

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
@Table(name = "RoomTable")
public class Room {
	
	//variable
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private int roId; 
	
	@ManyToOne
	@JoinColumn(name = "prId")
	private Property prId;
	 
	 @NotNull
	 @NotEmpty
	 @Pattern(regexp = "^[1-9][0-9]?|100$",
	        message = " Room number should be between 1 and 100")
	 private String roomNumber;
	 
	 @NotNull
	 @Min(value = 1, message = "Room capacity should be at least 1")
	 @Max(value = 10, message = "Room capacity should be at most 10")
	 private int capacity;
	 
	@Min(1)
	@Max(1000)
	 private float pricePerNight;
	 
	 @OneToMany(mappedBy = "room")
	 private Collection<Reservation> reservations;
	 
	 //setter and getter
		public Property getPrId() {
			return prId;
		}

		public void setPrId(Property prId) {
			this.prId = prId;
		}

		public int getRoId() {
			return roId;
		}

		public void setRoId(int roId) {
			this.roId = roId;
		}
	 public String getRoomNumber() {
		 return roomNumber;
	 }
	 public void setRoomNumber(String roomNumber) {
		 this.roomNumber = roomNumber;
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
			this.prId = PrId;
			this.roomNumber = room_number;
			this.capacity = capacity;
			this.pricePerNight = pricePerNight;
		}
		
		//not arg-constructor
		public Room() {

		}
		
		//toString method
		@Override
		public String toString() {
			return "Room [room_number=" + roomNumber + ", capacity=" + capacity + ", pricePerNight=" + pricePerNight
					+ "]";
		}
	 

	 
	 
	 


}
