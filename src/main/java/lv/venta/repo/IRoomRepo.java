package lv.venta.repo;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Room;

public interface IRoomRepo extends CrudRepository<Room, Integer> {


	boolean existsByRoomNumberAndCapacityAndPricePerNight(String room_number, int capacity, float pricePerNight);

	@Query("SELECT r FROM Room r WHERE r.RoId NOT IN " +
		       "(SELECT res.rooms.RoId FROM Reservation res WHERE " +
		       "(res.startDate < :endDate AND res.endDate > :startDate))")
	ArrayList<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate);

	ArrayList<Room> findByPrId_PrId(int propertyId);

	ArrayList<Room> findByCapacityGreaterThanEqual(int capacity);

	ArrayList<Room> findByPricePerNightBetween(float minPrice, float maxPrice);



}
