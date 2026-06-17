package lv.venta.service;

import java.time.LocalDate;
import java.util.ArrayList;

import lv.venta.model.Room;

public interface IRoomFiltering {
	
	public abstract ArrayList<Room> filterByPriceRange ( float minPrice,
            float maxPrice) throws Exception;
	
	public abstract ArrayList<Room> filterByCapacity ( int capacity) throws Exception;
	
	public abstract ArrayList<Room> filterByPropertyId (int propertyId) throws Exception;
	
	public abstract ArrayList<Room> filterByDateAvilability (LocalDate startDate,
            LocalDate endDate) throws Exception;

}
