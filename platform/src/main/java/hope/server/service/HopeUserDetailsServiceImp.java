package hope.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import hope.server.domain.Role;

@Service
public class HopeUserDetailsServiceImp implements HopeUserDetailsService {

	private final UserService userService;
	
	public HopeUserDetailsServiceImp(UserService userService){
		this.userService=userService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		
		hope.server.domain.User user=this.getUserByName(name);
	    if (user == null) {
            throw new UsernameNotFoundException(name);
        }
	    
	    Role role=user.getRole();
	    //define delegation
	    List<SimpleGrantedAuthority> grantedAuthority=new ArrayList<SimpleGrantedAuthority>();
	    grantedAuthority.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
	    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
		User springUser=new User(name,
								 bCryptPasswordEncoder.encode(user.getPassword()),
								 true,
								 true,
								 true,
								 true,
								 grantedAuthority); 
		
		return springUser;
	}

	@Override
	public hope.server.domain.User getUserByName(String name) {
		Optional<hope.server.domain.User> user=this.userService.FindByName(name);
		if(user.isPresent())
			return user.get();
		else
			return null;
	}

}
