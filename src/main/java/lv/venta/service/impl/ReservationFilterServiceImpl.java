package lv.venta.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Reservation;
import lv.venta.model.ReservationStatus;
import lv.venta.repo.IReservationRepo;
import lv.venta.service.IReservationFilterService;

@Service
public class ReservationFilterServiceImpl implements IReservationFilterService {

	@Autowired
	private IReservationRepo reservationRepo;

	@Override
	public ArrayList<Reservation> filterByStatus(ReservationStatus status) throws Exception {
		if (status == null) {
			throw new Exception("Wrong input param");
		}
		ArrayList<Reservation> result = reservationRepo.findByStatus(status);

		if (result.isEmpty()) {
			throw new Exception("There are no reservations with status " + status);
		}
		return result;
	}

	@Override
	public ArrayList<Reservation> filterByUserId(int userId) throws Exception {
		if (userId <= 0) {
			throw new Exception("Wrong input param");
		}
		ArrayList<Reservation> result = reservationRepo.findByUsers_Id(userId);

		if (result.isEmpty()) {
			throw new Exception("There are no reservations for user with id " + userId);
		}
		return result;
	}

	@Override
	public ArrayList<Reservation> filterByDateRange(LocalDate startDate, LocalDate endDate) throws Exception {
		if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
			throw new Exception("Wrong input param");
		}
		ArrayList<Reservation> result = reservationRepo.findByDateRange(startDate, endDate);

		if (result.isEmpty()) {
			throw new Exception("There are no reservations between " + startDate + " and " + endDate);
		}
		return result;
	}
}