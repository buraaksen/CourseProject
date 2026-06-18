package lv.venta.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.venta.model.User;
import lv.venta.model.Status;
import lv.venta.repo.IUserRepo;
import lv.venta.service.IUserCRUDService;

@Service
public class UserServiceImpl implements IUserCRUDService {

	@Autowired
	private IUserRepo userRepo;

	@Override
	public void createUser(String name, String surname, String email, String password, Status status)
			throws Exception {
		if(name == null
				|| name.isEmpty()
				|| !name.matches("[A-Z]{1}[a-zA-Z ]{2,40}")
				|| surname == null
				|| surname.isEmpty()
				|| !surname.matches("[A-Z]{1}[a-zA-Z ]{2,40}")
				|| email == null
				|| email.isEmpty()
				|| password == null
				|| password.isEmpty()
				|| status == null) {
				throw new Exception("Input data is incorrect");
			}

			if(userRepo.existsByEmail(email)) {
				throw new Exception("This user already exists in the DB");
			}

			User user = new User(name, surname, email, password, status);
			userRepo.save(user);
		}

	@Override
	public ArrayList<User> retrieveAllUsers() throws Exception {
		if(userRepo.count() == 0) {
			throw new Exception("There are no users in DB");
		}
		else {
			ArrayList<User> users = new ArrayList<>();
			for (User user : userRepo.findAll()) {
				users.add(user);
			}
			return users;
		}
	}

	@Override
	public User retrieveUserById(int id) throws Exception {
		if(id <= 0) {
			throw new Exception("Id should be positive");
		}

		if(!userRepo.existsById(id)) {
			throw new Exception("User with id " + id + " doesn't exist");
		}

		return userRepo.findById(id).get();
	}

	@Override
	public User retrieveUserByEmail(String email) throws Exception {
		if(email == null || email.isEmpty()) {
			throw new Exception("Email should not be empty");
		}

		if(!userRepo.existsByEmail(email)) {
			throw new Exception("User with email " + email + " doesn't exist");
		}

		return userRepo.findByEmail(email);
	}

	@Override
	public ArrayList<User> retrieveUsersByStatus(Status status) throws Exception {
		if(status == null) {
			throw new Exception("Status should not be null");
		}

		ArrayList<User> usersFromDB = new ArrayList<>(userRepo.findByRole(status));

		if(usersFromDB.isEmpty()) {
			throw new Exception("There are no users with status " + status);
		}

		return usersFromDB;
	}

	@Override
	public void updateUserById(int id, String name, String surname, String email, String password, Status status)
			throws Exception {
				if(name == null
						|| name.isEmpty()
						|| !name.matches("[A-Z]{1}[a-zA-Z ]{2,40}")
						|| surname == null
						|| surname.isEmpty()
						|| !surname.matches("[A-Z]{1}[a-zA-Z ]{2,40}")
						|| email == null
						|| email.isEmpty()
						|| password == null
						|| password.isEmpty()
						|| status == null) {
						throw new Exception("Input data is incorrect");
					}
				User userFromDB = retrieveUserById(id);

				userFromDB.setName(name);
				userFromDB.setSurname(surname);
				userFromDB.setEmail(email);
				userFromDB.setPassword(password);
				userFromDB.setRole(status);
				userRepo.save(userFromDB);
			}

	@Override
	public void deleteUserById(int id) throws Exception {
			User userFromDB = retrieveUserById(id);
			userRepo.delete(userFromDB);
		}

	}