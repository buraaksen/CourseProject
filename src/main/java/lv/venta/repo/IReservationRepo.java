package lv.venta.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import lv.venta.model.Reservation;
import lv.venta.model.ReservationStatus;


public interface IReservationRepo extends CrudRepository<Reservation, Integer> {
	
	ArrayList<Reservation> findByStatus(ReservationStatus status);

	ArrayList<Reservation> findByUsers_Id(int userId);

	@Query("SELECT r FROM Reservation r WHERE "
			+ "(r.startDate BETWEEN :startDate AND :endDate) "
			+ "OR (r.endDate BETWEEN :startDate AND :endDate)")
	ArrayList<Reservation> findByDateRange(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);
	}
