package lv.venta.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Property")
public class Room {
	
	//variable
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private int RoId; 
	
	
	 private Property PrId;
	 
	 @NotNull
	 @NotEmpty
	 @Pattern(regexp = "[1-100]+",
	        message = " Room number should be between 1 and 100")
	 private String room_number;
	 
	 @NotNull
	 @NotEmpty
	 @Pattern(regexp = "[1-10]+", 
	 		message = " Room capacity should be between 1 and 10")
	 private int capacity;
	 
	 @NotNull
	 @NotEmpty
	 @Pattern(regexp = "[1-1000]+", 
	 		message = " Price per night should be between 1 and 1000")
	 private float pricePerNight;
	 
	 //setter and getter
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
