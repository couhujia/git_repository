package hope.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthticationProviderImp implements AuthenticationProvider {

	protected static Logger logger = LoggerFactory.getLogger(AuthticationProviderImp.class);

	private final UserDetailsService userDetailsService;

	public AuthticationProviderImp(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		String username = token.getName();
		UserDetails userDetails = null;
		if (username != null) {
			userDetails = this.userDetailsService.loadUserByUsername(username);
		}
		if (userDetails == null) {
			throw new UsernameNotFoundException("not found user!");
		} else if (!userDetails.isEnabled()) {
			throw new DisabledException("user disable");
		} else if (!userDetails.isAccountNonExpired()) {
			throw new AccountExpiredException("user expire");
		} else if (!userDetails.isAccountNonLocked()) {
			throw new LockedException("user locked");
		} else if (!userDetails.isCredentialsNonExpired()) {
			throw new LockedException("credential expire");
		}
		// BCryptPasswordEncoder bCryptPasswordEncoder = new
		// BCryptPasswordEncoder(16);
		if (!userDetails.getPassword().equals(token.getCredentials())) {
			logger.info("user password is" + userDetails.getPassword());
			throw new BadCredentialsException("Invalid username/password");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// 返回true后才会执行上面的authenticate方法,这步能确保authentication能正确转换类型
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}
