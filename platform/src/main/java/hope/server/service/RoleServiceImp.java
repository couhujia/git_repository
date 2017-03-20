package hope.server.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import hope.server.domain.Role;

public class RoleServiceImp implements RoleService {

	private final RoleRepository roleRepostiry;
	
	@Autowired
	public RoleServiceImp(RoleRepository roleRepository){
		this.roleRepostiry=roleRepository;
	}
	
	@Override
	public Optional<Role> FindByName(String name) {
		return this.roleRepostiry.findByName(name);
	}

	@Override
	public Optional<Role> FindById(long id) {
		return this.roleRepostiry.findById(id);
	}

	@Override
	public Collection<Role> FindAll() {
		return this.roleRepostiry.findAll();
	}

	@Override
	public void DeleteByName(String name) {
		this.roleRepostiry.deleteByName(name);
	}

	@Override
	public Optional<Role> Save(String name) {
		return this.roleRepostiry.save(name);
	}
	
	

}
