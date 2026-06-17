package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;
import lv.venta.model.User;
import lv.venta.model.Status;

public interface IUserRepo extends CrudRepository<User, Integer> {

    // Find by email (useful for login / duplicate check)
    User findByEmail(String email);

    // Find all users by role
    Iterable<User> findByRole(Status role);
}