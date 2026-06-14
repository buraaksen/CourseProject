package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Property;
import lv.venta.model.Type;

public interface IPropertyRepo extends CrudRepository<Property, Integer> {

	boolean existsByNameAndLocationAndTypeAndDescription(String name, String location, Type type, String description);

	ArrayList<Property> findByType(Type type);

	ArrayList<Property> findByLocation(String location);

	ArrayList<Property> findByIsAvailable(boolean isAvailable);


	}
