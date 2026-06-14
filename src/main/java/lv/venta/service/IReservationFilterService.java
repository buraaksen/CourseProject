package lv.venta.service;

import java.time.LocalDate;
import java.util.ArrayList;

import lv.venta.model.Reservation;
import lv.venta.model.ReservationStatus;

public interface IReservationFilterService {

	public abstract ArrayList<Reservation> filterByStatus(ReservationStatus status) throws Exception;

	public abstract ArrayList<Reservation> filterByUserId(int userId) throws Exception;

	public abstract ArrayList<Reservation> filterByDateRange(LocalDate startDate, LocalDate endDate) throws Exception;
}