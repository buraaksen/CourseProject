// src/main/java/lv/venta/service/IReservationFilterService.java
package lv.venta.service;

import java.time.LocalDate;
import java.util.ArrayList;
import lv.venta.model.Reservation;
import lv.venta.model.ReservationStatus;

public interface IReservationFilterService {

    ArrayList<Reservation> filterByStatus(ReservationStatus status) throws Exception;

    ArrayList<Reservation> filterByUserId(int userId) throws Exception;

    ArrayList<Reservation> filterByDateRange(LocalDate startDate, LocalDate endDate) throws Exception;
}