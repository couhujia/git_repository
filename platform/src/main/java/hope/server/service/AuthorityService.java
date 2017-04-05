package hope.server.service;

import java.util.Collection;

import hope.server.domain.Authority;

public interface AuthorityService {
	Collection<Authority> findAll();

	Collection<Authority> findByRoleId(Long role_id);
}
