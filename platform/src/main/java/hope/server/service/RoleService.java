package hope.server.service;

import java.util.Collection;
import java.util.Optional;

import hope.server.domain.Role;

public interface RoleService {
	Optional<Role> FindByName(String name);
	Optional<Role> FindById(long id);
	Collection<Role> FindAll();
	void DeleteByName(String name);
	Optional<Role> Save(String name);
}
