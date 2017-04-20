package hope.server.service;

import hope.server.domian.Role;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Administrator on 2017/4/13.
 */
public interface RoleRepository extends Repository<Role, Long> {
    Optional<Role> findByName(String name);

    Optional<Role> findById(long id);

    Collection<Role> findAll();

    void deleteByName(String name);

    Optional<Role> save(String name);

}
