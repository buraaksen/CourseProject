package lv.venta.repo;

import org.springframework.data.repository.CrudRepository;
import lv.venta.model.Room;

public interface IRoomRepo extends CrudRepository<Room, Integer> {

}