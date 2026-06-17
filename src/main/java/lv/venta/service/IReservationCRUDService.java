// src/main/java/lv/venta/service/IReservationCRUDService.java
package lv.venta.service;

import java.time.LocalDate;
import java.util.ArrayList;
import lv.venta.model.Reservation;
import lv.venta.model.ReservationStatus;

public interface IReservationCRUDService {

    void createReservation(int userId, int roomId, LocalDate startDate, LocalDate endDate) throws Exception;

    ArrayList<Reservation> retrieveAllReservations() throws Exception;

    Reservation retrieveReservationById(int id) throws Exception;

    void updateReservationById(int id, LocalDate startDate, LocalDate endDate, ReservationStatus status) throws Exception;

    void deleteReservationById(int id) throws Exception;
}