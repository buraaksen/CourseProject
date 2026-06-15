package lv.venta.repo;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Room;

public interface IRoomRepo extends CrudRepository<Room, Integer> {


	boolean existsByRoomNumberAndCapacityAndPricePerNight(String room_number, int capacity, float pricePerNight);

	ArrayList<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate);

	ArrayList<Room> findByPrId_PrId(int propertyId);

	ArrayList<Room> findByCapacityGreaterThanEqual(int capacity);

	ArrayList<Room> findByPricePerNightBetween(float minPrice, float maxPrice);



}
