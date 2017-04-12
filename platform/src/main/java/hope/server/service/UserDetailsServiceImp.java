package hope.server.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hope.server.domain.Authority;
import hope.server.domain.Role;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	protected static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImp.class);

	private final UserService userService;
	private final AuthorityService authorityService;

	@Autowired
	public UserDetailsServiceImp(UserService userService, AuthorityService authorityService) {
		this.userService = userService;
		this.authorityService = authorityService;
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

		logger.info("in the load");
		hope.server.domain.User user = this.getUserByName(name);
		if (user == null) {
			throw new UsernameNotFoundException(name);
		}
		logger.info("the name is" + user.getName() + " the rolename is " + user.getRole().getName());

		Role role = user.getRole();
		Collection<Authority> powers = this.authorityService.findByRoleId(role.getId());
		List<SimpleGrantedAuthority> grantedAuthority = new ArrayList<SimpleGrantedAuthority>();
		for (Authority au : powers) {
			grantedAuthority.add(new SimpleGrantedAuthority(au.getName()));
		}

		// grantedAuthority.add(new
		// SimpleGrantedAuthority("ROLE_"+role.getName()));
		/*
		 * BCryptPasswordEncoder bCryptPasswordEncoder = new
		 * BCryptPasswordEncoder(16); User springUser=new User(user.getName(),
		 * bCryptPasswordEncoder.encode(user.getPassword()), true, true, true,
		 * true, grantedAuthority);
		 */
		User springUser = new User(user.getName(), user.getPassword(), true, true, true, true, grantedAuthority);
		logger.info("the after Security context contains " + SecurityContextHolder.getContext().getAuthentication());
		return springUser;
	}

	public hope.server.domain.User getUserByName(String name) {
		Optional<hope.server.domain.User> user = this.userService.FindByName(name);
		if (user.isPresent())
			return user.get();
		else
			return null;
	}

}
