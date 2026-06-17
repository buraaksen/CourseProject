package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Property;
import lv.venta.model.Room;
import lv.venta.repo.IRoomRepo;
import lv.venta.service.IRoomCRUDService;

@Service
public class RoomCRUDService implements IRoomCRUDService {
	
	@Autowired
	private IRoomRepo roomRepo;

	@Override
	public void create(Property PrId, String room_number, int capacity, float pricePerNight) throws Exception {
		if (PrId == null 
				|| room_number == null 
				|| room_number.isEmpty() 
				|| capacity <= 0 
				|| pricePerNight <= 0) {
			throw new Exception("Input data is incorrect");
		}
		
		if (roomRepo.existsByRoomNumberAndCapacityAndPricePerNight(room_number, capacity,
				pricePerNight)) {
			throw new Exception("This room already exists in the DB");
		}
	Room room = new Room(PrId, room_number, capacity, pricePerNight);
	roomRepo.save(room);
	}

	@Override
	public ArrayList<Room> retrieveAllRooms() throws Exception {
		if (roomRepo.count() == 0) {
			throw new Exception("There are no rooms in DB");
		} else {
			return (ArrayList<Room>) roomRepo.findAll();
		}
	}

	@Override
	public Room retrieveRoomById(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("Id should be positive");
		}
		if (!roomRepo.existsById(id)) {
			throw new Exception("Room with id " + id + " doesn't exist");
		} else {
			return roomRepo.findById(id).get();
		}
	}

	@Override
	public void updateRoomById(int id, Property PrId, String room_number, int capacity, float pricePerNight)
			throws Exception {
		if (id <= 0 
                || PrId == null 
                || room_number == null 
                || room_number.isEmpty() 
                || capacity <= 0 
                || pricePerNight <= 0) {
			            throw new Exception("Input data is incorrect");
		}
		
		Room room = roomRepo.findById(id).get();
		room.setRoomNumber(room_number);
		room.setCapacity(capacity);
		room.setPricePerNight(pricePerNight);
		roomRepo.save(room);
		
	}

	@Override
	public void deleteRoomById(int id) throws Exception {
		Room roomFromDB = retrieveRoomById(id);
		roomRepo.delete(roomFromDB);
	}}