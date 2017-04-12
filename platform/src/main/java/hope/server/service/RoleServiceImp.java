package hope.server.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import hope.server.domain.Role;

@Service
public class RoleServiceImp implements RoleService {

	private final RoleRepository roleRepostiry;

	@Autowired
	public RoleServiceImp(RoleRepository roleRepository) {
		this.roleRepostiry = roleRepository;
	}

	@Override
	@Cacheable(value = "role", key = "#role.id")
	public Optional<Role> FindByName(String name) {
		return this.roleRepostiry.findByName(name);
	}

	@Override
	@Cacheable(value = "role", key = "#role.id")
	public Optional<Role> FindById(long id) {
		return this.roleRepostiry.findById(id);
	}

	@Override
	@Cacheable(value = "role",  key = "all")
	public Collection<Role> FindAll() {
		return this.roleRepostiry.findAll();
	}

	@Override
	@CacheEvict(value = "role", key = "#role.id")
	public void DeleteByName(String name) {
		this.roleRepostiry.deleteByName(name);
	}

	@Override
	@CachePut(value = "role", key = "#role.id")
	public Optional<Role> Save(String name) {
		return this.roleRepostiry.save(name);
	}

}
