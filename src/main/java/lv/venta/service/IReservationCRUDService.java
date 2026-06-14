package lv.venta.service;

import java.time.LocalDate;
import java.util.ArrayList;

import lv.venta.model.Reservation;
import lv.venta.model.ReservationStatus;

public interface IReservationCRUDService {

	// C - create (user id + room id + dates, total price is calculated)
	public abstract void createReservation(int userId, int roomId, LocalDate startDate, LocalDate endDate)
			throws Exception;

	// R - retrieve all
	public abstract ArrayList<Reservation> retrieveAllReservations() throws Exception;

	// R - retrieve by id
	public abstract Reservation retrieveReservationById(int id) throws Exception;

	// U - update by id (dates + status)
	public abstract void updateReservationById(int id, LocalDate startDate, LocalDate endDate,
			ReservationStatus status) throws Exception;

	// D - delete by id
	public abstract void deleteReservationById(int id) throws Exception;
}