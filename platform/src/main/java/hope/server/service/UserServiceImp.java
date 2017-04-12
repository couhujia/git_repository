/*
 * @author hujia
 * @time 20170317
 */

package hope.server.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import hope.server.domain.User;

@Service
public class UserServiceImp implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Cacheable(value = "user", key = "#name")
	public Optional<User> FindByName(String name) {
		return this.userRepository.findByName(name);
	}

	@Override
	@Cacheable(value = "user", key = "all")
	public Collection<User> FindAll() {
		return this.userRepository.findAll();
	}

	@Override
	@Cacheable(value = "user", key = "#email")
	public Optional<User> FindByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	@Override
	@Cacheable(value = "user", key = "#phone")
	public Optional<User> FindByPhone(String phone) {
		return this.userRepository.findByPhone(phone);
	}

	@Override
	@CachePut(value = "user", key = "#user.id")
	public Optional<User> Save(User user) {
		return this.userRepository.save(user);
	}

}
