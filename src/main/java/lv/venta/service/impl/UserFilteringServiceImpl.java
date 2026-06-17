package lv.venta.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.venta.model.User;
import lv.venta.model.Status;
import lv.venta.repo.IUserRepo;
import lv.venta.service.IUserFiltering;


@Service
public class UserFilteringServiceImpl implements IUserFiltering {

	@Autowired
	private IUserRepo userRepo;

	@Override
	public ArrayList<User> filterByName(String name) throws Exception {
		if(name == null || name.isEmpty()) {
			throw new Exception("Name should not be empty");
		}

		ArrayList<User> usersFromDB = userRepo.findByNameContainingIgnoreCase(name);

		if(usersFromDB.isEmpty()) {
			throw new Exception("There are no users with name containing " + name);
		}

		return usersFromDB;
	}

	@Override
	public ArrayList<User> filterBySurname(String surname) throws Exception {
		if(surname == null || surname.isEmpty()) {
			throw new Exception("Surname should not be empty");
		}

		ArrayList<User> usersFromDB = userRepo.findBySurnameContainingIgnoreCase(surname);

		if(usersFromDB.isEmpty()) {
			throw new Exception("There are no users with surname containing " + surname);
		}

		return usersFromDB;
	}

	@Override
	public ArrayList<User> filterByStatus(Status status) throws Exception {
		if(status == null) {
			throw new Exception("Status should not be null");
		}

		ArrayList<User> usersFromDB = new ArrayList<>(userRepo.findByRole(status));

		if(usersFromDB.isEmpty()) {
			throw new Exception("There are no users with status " + status);
		}

		return usersFromDB;
	}
}