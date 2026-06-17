package lv.venta.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Room;
import lv.venta.repo.IRoomRepo;
import lv.venta.service.IRoomFiltering;

@Service
public class RoomFiltering implements IRoomFiltering {
	
	@Autowired
	private IRoomRepo roomRepo;

	@Override
	public ArrayList<Room> filterByPriceRange(float minPrice, float maxPrice) throws Exception {
		ArrayList<Room> result =
	            roomRepo.findByPricePerNightBetween(
	                    minPrice,
	                    maxPrice);

	    if(result.isEmpty())
	        throw new Exception("No rooms found");

	    return result;
	}
	
	@Override
	public ArrayList<Room> filterByCapacity(int capacity) throws Exception {
		ArrayList<Room> result =
	            roomRepo.findByCapacityGreaterThanEqual(capacity);

	    if(result.isEmpty())
	        throw new Exception("No rooms found");

	    return result;
	}

	@Override
	public ArrayList<Room> filterByPropertyId(int propertyId) throws Exception {
		ArrayList<Room> result =
	            roomRepo.findByPropertyId(propertyId);

	    if(result.isEmpty())
	        throw new Exception("No rooms found");

	    return result;
	}

	@Override
	public ArrayList<Room> filterByDateAvilability(LocalDate startDate, LocalDate endDate) throws Exception {

        ArrayList<Room> result =
                roomRepo.findAvailableRooms(
                        startDate,
                        endDate);

        if(result.isEmpty())
            throw new Exception("No available rooms found");

        return result;
	}
}
