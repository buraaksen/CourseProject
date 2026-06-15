package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Property;
import lv.venta.model.Type;

public interface IPropertyCRUDService {
		//C - create
		public abstract void createProperty(String name, String location, 
				Type type, String description, boolean isAvailable) throws Exception;
		
		//R - retrieve all
		public abstract ArrayList<Property> retrieveAllProperties() throws Exception;
		
		//R - retrieve by id
		public abstract Property retrievePropertyById(int id) throws Exception;
		
		//U - update by id
		public abstract void updatePropertyById(int id, String name, String location, 
				Type type, String description, boolean isAvailable) throws Exception;
		
		//D - delete by id
		public abstract void deletePropertyById(int id) throws Exception;
		
	}
