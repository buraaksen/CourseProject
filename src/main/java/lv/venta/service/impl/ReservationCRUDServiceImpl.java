/* package lv.venta.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Reservation;
import lv.venta.model.ReservationStatus;
import lv.venta.model.Room;
import lv.venta.model.User;
import lv.venta.repo.IReservationRepo;
//import lv.venta.repo.IRoomRepo;
//import lv.venta.repo.IUserRepo;
import lv.venta.service.IReservationCRUDService;

@Service
public class ReservationCRUDServiceImpl implements IReservationCRUDService {

	@Autowired
	private IReservationRepo reservationRepo;

	@Autowired
	private IUserRepo userRepo;

	@Autowired
	private IRoomRepo roomRepo;
	
	@Override
	public void createReservation(int userId, int roomId, LocalDate startDate, LocalDate endDate) throws Exception {

		if (startDate == null || endDate == null || !startDate.isBefore(endDate)) {
			throw new Exception("Input data is incorrect");
		}

	if (!userRepo.existsById(userId)) {
			throw new Exception("User with id " + userId + " doesn't exist");
		}

		if (!roomRepo.existsById(roomId)) {
			throw new Exception("Room with id " + roomId + " doesn't exist");
		}

		User user = userRepo.findById(userId).get();
		Room room = roomRepo.findById(roomId).get();

	long nights = 0;
		LocalDate current = startDate;
		while (current.isBefore(endDate)) {
			nights++;
			current = current.plusDays(1);
		}
		
		float totalPrice = nights * room.getPricePerNight();

		Reservation reservation = new Reservation(user, room, startDate, endDate, totalPrice, ReservationStatus.Pending);
		reservationRepo.save(reservation);
	}

	@Override
	public ArrayList<Reservation> retrieveAllReservations() throws Exception {
		if (reservationRepo.count() == 0) {
			throw new Exception("There are no reservations in DB");
		}
		return (ArrayList<Reservation>) reservationRepo.findAll();
	}

	@Override
	public Reservation retrieveReservationById(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("Id should be positive");
		}
		if (!reservationRepo.existsById(id)) {
			throw new Exception("Reservation with id " + id + " doesn't exist");
		}
		return reservationRepo.findById(id).get();
	}

	@Override
	public void updateReservationById(int id, LocalDate startDate, LocalDate endDate, ReservationStatus status)
			throws Exception {

		if (startDate == null || endDate == null || !startDate.isBefore(endDate) || status == null) {
			throw new Exception("Input data is incorrect");
		}

		Reservation reservationFromDB = retrieveReservationById(id);


		long nights = 0;
		LocalDate current = startDate;
		while (current.isBefore(endDate)) {
			nights++;
			current = current.plusDays(1);
		}
		
		float totalPrice = nights * reservationFromDB.getRoom().getPricePerNight();

		reservationFromDB.setStartDate(startDate);
		reservationFromDB.setEndDate(endDate);
		reservationFromDB.setTotalPrice(totalPrice);
		reservationFromDB.setStatus(status);

		reservationRepo.save(reservationFromDB);
	}

	@Override
	public void deleteReservationById(int id) throws Exception {
		Reservation reservationFromDB = retrieveReservationById(id);
		reservationRepo.delete(reservationFromDB);
	}
}

*/	