package hope.server.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hope.server.domain.Authority;

@Service
public class AuthorityServiceImp implements AuthorityService {

	private final AuthorityRepository authorityRepository;
	
	@Autowired
	public AuthorityServiceImp( AuthorityRepository authorityRepository){
		this.authorityRepository=authorityRepository;
	}
	
	@Override
	public Collection<Authority> findAll() {
		return this.authorityRepository.findAll();
		
	}

	@Override
	public Collection<Authority> findByRoleId(Long role_id) {
		return this.authorityRepository.findByRoleId(role_id);
		
	}

}
