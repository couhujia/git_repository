package hope.server.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import hope.server.domain.User;

public interface HopeUserDetailsService extends UserDetailsService  {
	
	public User getUserByName(String name);

}
