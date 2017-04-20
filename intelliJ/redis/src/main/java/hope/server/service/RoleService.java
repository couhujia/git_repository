package hope.server.service;

import hope.server.domian.Role;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Administrator on 2017/4/14.
 */
public interface RoleService {
    Optional<Role> FindByName(String name);

    Optional<Role> FindById(long id);

    Collection<Role> FindAll();

    void DeleteByName(String name);

    Optional<Role> Save(String name);
}
