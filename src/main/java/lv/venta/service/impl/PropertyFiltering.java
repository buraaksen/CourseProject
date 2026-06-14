package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Property;
import lv.venta.model.Type;
import lv.venta.repo.IPropertyRepo;
import lv.venta.service.IPropertyFiltering;

@Service
public class PropertyFiltering implements IPropertyFiltering {

@Autowired
private IPropertyRepo propertyRepo;

@Override
public ArrayList<Property> filterByType(Type type) throws Exception {
	if(type == null) {
		throw new Exception("Wrong input param: type cannot be null");
	}
	
	ArrayList<Property> result = propertyRepo.findByType(type);
	
	if(result.isEmpty()) {
		throw new Exception("There are no properties of type " + type);
	}
	else {
		return result;
		}
	}


@Override
public ArrayList<Property> filterByLocation(String location) throws Exception {
	if(location == null || location.isEmpty()) {
		throw new Exception("Wrong input param: location cannot be empty");
	}
	
	ArrayList<Property> result = propertyRepo.findByLocation(location);

	if(result.isEmpty()) {
		throw new Exception("There are no properties located in " + location);
	}
	else {
		return result;
	}
}

@Override
public ArrayList<Property> filterByAvailability(boolean isAvailable) throws Exception {
	ArrayList<Property> result = propertyRepo.findByIsAvailable(isAvailable);

	if(result.isEmpty()) {
		String status = isAvailable ? "available" : "unavailable";
		throw new Exception("There are no " + status + " properties at the moment");
	}
	else {
		return result;
	}
}
}
