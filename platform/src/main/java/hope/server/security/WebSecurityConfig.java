package hope.server.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import hope.server.service.HopeUserDetailsService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)  //@PreAuthorize enable use
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final HopeUserDetailsService userService;
	
	@Autowired
	public WebSecurityConfig(HopeUserDetailsService userService){
		this.userService=userService;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/login","/error","/").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.successHandler(new HopeAuthenticationSuccessHandler())
				.failureHandler(new SimpleUrlAuthenticationFailureHandler())
				.and()
				.logout()
				.logoutUrl("/logout")// default url is login?logout
				.logoutSuccessHandler(new HopeLogoutSuccessHandler())
				.and()
				.exceptionHandling().authenticationEntryPoint(new HopeAuthenticationEntryPoint());
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.userDetailsService(this.userService)
			.passwordEncoder(new BCryptPasswordEncoder(16));
	}
	
	public static class HopeAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
		
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request,
											HttpServletResponse response,
											Authentication authentication)
				throws ServletException,IOException{
				clearAuthenticationAttributes(request);
		}
	}
	
	public static class HopeLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
		
		@Override
		public void onLogoutSuccess(HttpServletRequest request,
									HttpServletResponse response,
									Authentication authentication)
									throws ServletException,IOException{
			//donoting
		}
	}
	
	public static class HopeAuthenticationEntryPoint implements AuthenticationEntryPoint{
		
		public void commence(HttpServletRequest request,
							 HttpServletResponse response,
							 AuthenticationException authException) throws IOException{
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
							"Authentication Failed:"+authException.getMessage());
		}
	}

}
