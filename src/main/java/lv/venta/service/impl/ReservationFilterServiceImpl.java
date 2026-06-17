
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
        if (status == null) throw new Exception("Status cannot be null");
        ArrayList<Reservation> result = reservationRepo.findByStatus(status);
        if (result.isEmpty()) throw new Exception("No reservations found with status: " + status);
        return result;
    }

    @Override
    public ArrayList<Reservation> filterByUserId(int userId) throws Exception {
        if (userId <= 0) throw new Exception("Id must be positive");
        ArrayList<Reservation> result = reservationRepo.findByUser_Id(userId);
        if (result.isEmpty()) throw new Exception("No reservations found for user id: " + userId);
        return result;
    }

    @Override
    public ArrayList<Reservation> filterByDateRange(LocalDate startDate, LocalDate endDate)
            throws Exception {
        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            throw new Exception("Invalid date range");
        }
        ArrayList<Reservation> result = reservationRepo.findByDateRange(startDate, endDate);
        if (result.isEmpty()) {
            throw new Exception("No reservations found between " + startDate + " and " + endDate);
        }
        return result;
    }
}