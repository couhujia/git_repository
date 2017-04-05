package hope.server.service;

import java.util.Collection;

import org.springframework.data.repository.Repository;

import hope.server.domain.Authority;

public interface AuthorityRepository extends Repository<Authority, Long> {

	Collection<Authority> findAll();

	Collection<Authority> findByRoleId(Long role_id);

}
