package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Property;
import lv.venta.model.Room;

public interface IRoomCRUDService {
	
	//C - create	
	
	public abstract void create(Property PrId, String room_number, int capacity, float pricePerNight) throws Exception;
	
	
	//R - retrieve all
	public abstract ArrayList<Room> retrieveAllRooms() throws Exception;
	
	//R - retrieve by id
	public abstract Room retrieveRoomById(int id) throws Exception;
	
	//U - update by id
	public abstract void updateRoomById(int id, Property PrId, String room_number, int capacity, float pricePerNight) throws Exception;
	
	//D - delete by id
	public abstract void deleteRoomById(int id) throws Exception;

	
	

}
