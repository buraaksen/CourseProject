
package lv.venta.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.venta.model.Reservation;
import lv.venta.model.ReservationStatus;
import lv.venta.model.Room;
import lv.venta.model.User;
import lv.venta.repo.IReservationRepo;
import lv.venta.repo.IRoomRepo;
import lv.venta.repo.IUserRepo;
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
    public void createReservation(int userId, int roomId, LocalDate startDate, LocalDate endDate)
            throws Exception {

        if (startDate == null || endDate == null || !startDate.isBefore(endDate)) {
            throw new Exception("Start date must be before end date");
        }
        if (!userRepo.existsById(userId)) {
            throw new Exception("User with id " + userId + " does not exist");
        }
        if (!roomRepo.existsById(roomId)) {
            throw new Exception("Room with id " + roomId + " does not exist");
        }

        User user = userRepo.findById(userId).get();
        Room room = roomRepo.findById(roomId).get();

        // DÜZELTME: Period.getDays() yerine ChronoUnit.DAYS.between kullanılıyor,
        // aksi halde 1 aydan uzun rezervasyonlarda gece sayısı (ve toplam fiyat) yanlış hesaplanır.
        long nights = ChronoUnit.DAYS.between(startDate, endDate);
        float totalPrice = nights * room.getPricePerNight();

        Reservation reservation = new Reservation(user, room, startDate, endDate,
                totalPrice, ReservationStatus.Pending);
        reservationRepo.save(reservation);
    }

    @Override
    public ArrayList<Reservation> retrieveAllReservations() throws Exception {
        if (reservationRepo.count() == 0) {
            throw new Exception("There are no reservations in the database");
        }
        return (ArrayList<Reservation>) reservationRepo.findAll();
    }

    @Override
    public Reservation retrieveReservationById(int id) throws Exception {
        if (id <= 0) throw new Exception("Id must be positive");
        if (!reservationRepo.existsById(id)) {
            throw new Exception("Reservation with id " + id + " does not exist");
        }
        return reservationRepo.findById(id).get();
    }

    @Override
    public void updateReservationById(int id, LocalDate startDate, LocalDate endDate,
                                      ReservationStatus status) throws Exception {

        if (startDate == null || endDate == null || !startDate.isBefore(endDate)) {
            throw new Exception("Start date must be before end date");
        }
        if (status == null) {
            throw new Exception("Status cannot be null");
        }

        Reservation reservation = retrieveReservationById(id);

        long nights = ChronoUnit.DAYS.between(startDate, endDate);
        float totalPrice = nights * reservation.getRoom().getPricePerNight();

        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setTotalPrice(totalPrice);
        reservation.setStatus(status);

        reservationRepo.save(reservation);
    }

    @Override
    public void deleteReservationById(int id) throws Exception {
        Reservation reservation = retrieveReservationById(id);
        reservationRepo.delete(reservation);
    }
}