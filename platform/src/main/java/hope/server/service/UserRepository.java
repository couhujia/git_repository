package hope.server.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import hope.server.domain.User;

public interface UserRepository extends Repository<User, Long> {

	Optional<User> findByName(String name);

	Collection<User> findAll();

	Optional<User> findByEmail(String email);

	Optional<User> findByPhone(String phone);

	Optional<User> save(User user);
}
