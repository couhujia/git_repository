package hope.server.service;

import hope.server.domian.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Administrator on 2017/4/14.
 */
@Service
@Transactional
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> FindByName(String name) {
        return this.userRepository.findByName(name);
    }

    @Override
    public Collection<User> FindAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> FindByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> FindByPhone(String phone) {
        return this.userRepository.findByPhone(phone);
    }

    @Override
    public Optional<User> Save(User user) {
        return this.userRepository.save(user);
    }
}
