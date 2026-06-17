package lv.venta.repo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import lv.venta.model.User;
import lv.venta.model.Status;

public interface IUserRepo extends CrudRepository<User, Integer> {
	boolean existsByEmail(String email);
	User findByEmail(String email);
	List<User> findByRole(Status status);
	ArrayList<User> findByNameContainingIgnoreCase(String name);
	ArrayList<User> findBySurnameContainingIgnoreCase(String surname);
}