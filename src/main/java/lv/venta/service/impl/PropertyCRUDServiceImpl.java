package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Property;
import lv.venta.model.Type;
import lv.venta.repo.IPropertyRepo;
import lv.venta.service.IPropertyCRUDService;

@Service
public class PropertyCRUDServiceImpl implements IPropertyCRUDService {
	
	@Autowired
	private IPropertyRepo propertyRepo;

	@Override
	public void createProperty(String name, String location, Type type, String description, boolean isAvailable)
			throws Exception {
		if(name == null 
				|| name.isEmpty() 
				|| !name.matches("[A-Z]{1}[a-zA-Z ]{2,40}")
				|| location == null 
				|| location.isEmpty() 
				|| type == null 
				|| description == null 
				|| description.isEmpty()) {
				throw new Exception("Input data is incorrect");
			}
			
			if(propertyRepo
				.existsByNameAndLocationAndTypeAndDescription(name, location, type, description)) {
				throw new Exception("This property already exists in the DB");
			}
			
			Property prop = new Property(name, location, description, isAvailable, type);
			propertyRepo.save(prop);
		}
	
	@Override
	public ArrayList<Property> retrieveAllProperties() throws Exception {
		if(propertyRepo.count() == 0) {
			throw new Exception("There are no properties in DB");
		}
		else {
			return (ArrayList<Property>) propertyRepo.findAll();
		}
	}

	@Override
	public Property retrievePropertyById(int id) throws Exception {
		if(id <= 0) {
			throw new Exception("Id should be positive");
		}
		
		if(!propertyRepo.existsById(id)) {
			throw new Exception("Property with id " + id + " doesn't exist");
		}
		
		return propertyRepo.findById(id).get();
	}

	@Override
	public void updatePropertyById(int id, String name, String location, Type type, String description,
			boolean isAvailable) throws Exception {
				if(name == null 
						|| name.isEmpty() 
						|| !name.matches("[A-Z]{1}[a-zA-Z ]{2,40}")
						|| location == null 
						|| location.isEmpty() 
						|| type == null
						|| description == null 
						|| description.isEmpty()) {
						throw new Exception("Input data is incorrect");
					}

				Property propertyFromDB = retrievePropertyById(id);
				
				propertyFromDB.setName(name);
				propertyFromDB.setLocation(location);
				propertyFromDB.setType(type);
				propertyFromDB.setDescription(description);
				propertyFromDB.setIsAvailable(isAvailable);

				propertyRepo.save(propertyFromDB);
			}
	@Override
	public void deletePropertyById(int id) throws Exception {
			Property propertyFromDB = retrievePropertyById(id);
			propertyRepo.delete(propertyFromDB);
		}
		
	}