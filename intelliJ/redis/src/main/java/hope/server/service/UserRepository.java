package hope.server.service;

import hope.server.domian.User;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Administrator on 2017/4/14.
 */
public interface UserRepository extends Repository<User,Long> {
    Optional<User> findByName(String name);

    Collection<User> findAll();

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    Optional<User> save(User user);
}
