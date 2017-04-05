package hope.server.service;

import java.util.Collection;
import java.util.Optional;

import hope.server.domain.User;

public interface UserService {
	Optional<User> FindByName(String name);

	Collection<User> FindAll();

	Optional<User> FindByEmail(String email);

	Optional<User> FindByPhone(String phone);

	Optional<User> Save(User user);

}
