package lv.venta.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import lv.venta.model.Property;
import lv.venta.model.Room;

public interface IRoomRepo extends CrudRepository<Room, Integer> {

	boolean existsByRoomNumberAndPrId(String roomNumber, Property prId);

	@Query("SELECT r FROM Room r WHERE r.roId NOT IN " +
		   "(SELECT res.rooms.roId FROM Reservation res WHERE " +
		   "(res.startDate < :endDate AND res.endDate > :startDate))")
	ArrayList<Room> findAvailableRooms(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	@Query("SELECT r FROM Room r WHERE r.prId.prId = :propertyId")
	ArrayList<Room> findByPropertyId(@Param("propertyId") int prId);

	ArrayList<Room> findByCapacityGreaterThanEqual(int capacity);

	ArrayList<Room> findByPricePerNightBetween(float minPrice, float maxPrice);
}