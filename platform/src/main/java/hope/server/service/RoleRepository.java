package hope.server.service;


import java.util.List;

/*import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;*/

//import java.util.List;

import org.springframework.data.repository.CrudRepository;

import hope.server.domain.Role;

public interface RoleRepository extends CrudRepository<Role,Long> {
	Role findByName(String name);
	Role findById(long id);
	List<Role> findAll();
	void deleteByName(String name);
}
