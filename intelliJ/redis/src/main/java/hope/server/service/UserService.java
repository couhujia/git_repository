package hope.server.service;

import hope.server.domian.User;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Administrator on 2017/4/14.
 */
public interface UserService {
    Optional<User> FindByName(String name);

    Collection<User> FindAll();

    Optional<User> FindByEmail(String email);

    Optional<User> FindByPhone(String phone);

    Optional<User> Save(User user);
}
