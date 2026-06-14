package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Property;
import lv.venta.model.Type;

public interface IPropertyFiltering {
	
	public abstract ArrayList<Property> filterByType(Type type) throws Exception;
	
	public abstract ArrayList<Property> filterByLocation(String location) throws Exception;
	
	public abstract ArrayList<Property> filterByAvailability(boolean isAvailable) throws Exception;

}
