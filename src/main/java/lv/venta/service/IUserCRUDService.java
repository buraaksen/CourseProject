package lv.venta.service;

import java.util.ArrayList;
import lv.venta.model.User;
import lv.venta.model.Status;

public interface IUserCRUDService {

	void createUser(String name, String surname, String email, String password, Status status) throws Exception;

	ArrayList<User> retrieveAllUsers() throws Exception;

	User retrieveUserById(int id) throws Exception;

	User retrieveUserByEmail(String email) throws Exception;

	ArrayList<User> retrieveUsersByStatus(Status status) throws Exception;

	void updateUserById(int id, String name, String surname, String email, String password, Status status) throws Exception;

	void deleteUserById(int id) throws Exception;
}