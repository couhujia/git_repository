package hope.server.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import hope.server.domain.Role;

public interface RoleRepository extends Repository<Role, Long> {
	Optional<Role> findByName(String name);

	Optional<Role> findById(long id);

	Collection<Role> findAll();

	void deleteByName(String name);

	Optional<Role> save(String name);
}
