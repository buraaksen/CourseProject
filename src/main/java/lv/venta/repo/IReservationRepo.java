
package lv.venta.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import lv.venta.model.Reservation;
import lv.venta.model.ReservationStatus;

public interface IReservationRepo extends CrudRepository<Reservation, Integer> {

    ArrayList<Reservation> findByStatus(ReservationStatus status);

    ArrayList<Reservation> findByUser_Id(int userId);

    @Query("SELECT r FROM Reservation r WHERE r.startDate >= :startDate AND r.endDate <= :endDate")
    ArrayList<Reservation> findByDateRange(LocalDate startDate, LocalDate endDate);
}