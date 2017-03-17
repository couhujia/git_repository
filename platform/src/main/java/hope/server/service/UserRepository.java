package hope.server.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//import hope.server.domain.Role;
import hope.server.domain.User;


public interface UserRepository extends CrudRepository<User,Long> {

	User findByName(String name);
	List<User> findAll();
}
