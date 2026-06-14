package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Facility;

public interface IFacilityRepo extends CrudRepository<Facility, Integer> {

}
