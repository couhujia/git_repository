package hope.server.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)  //@PreAuthorize enable use
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final AuthenticationProvider authenticationProvider;
	
	protected static Logger logger=LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Autowired
	public WebSecurityConfig(AuthenticationProvider authenticationProvider){
		this.authenticationProvider=authenticationProvider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/login","/","/test").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.usernameParameter("username")
				.passwordParameter("password")
				.successHandler(new HopeAuthenticationSuccessHandler())
				.failureHandler(new SimpleUrlAuthenticationFailureHandler())
				.and()
				.logout()
				.logoutUrl("/logout")// default url is login?logout
				.logoutSuccessHandler(new HopeLogoutSuccessHandler())
				.and()
				.exceptionHandling().authenticationEntryPoint(new HopeAuthenticationEntryPoint())
				.and()
				.csrf().disable();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.authenticationProvider(authenticationProvider);
	}
	
	public static class HopeAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
		
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request,
											HttpServletResponse response,
											Authentication authentication)
				throws ServletException,IOException{
				logger.info("the request is "+request.toString());
				logger.info("in the config before Security context contains "+SecurityContextHolder.getContext().getAuthentication());
				clearAuthenticationAttributes(request);
				response.addHeader("test", "helloworld");
				logger.info("in the config after Security context contains "+SecurityContextHolder.getContext().getAuthentication());
		}
	}
	
	public static class HopeLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
		
		@Override
		public void onLogoutSuccess(HttpServletRequest request,
									HttpServletResponse response,
									Authentication authentication)
									throws ServletException,IOException{
			logger.info("out!");
		}
	}
	
	public static class HopeAuthenticationEntryPoint implements AuthenticationEntryPoint{
		
		public void commence(HttpServletRequest request,
							 HttpServletResponse response,
							 AuthenticationException authException) throws IOException{
			logger.info("in the fialed  Security context contains "+SecurityContextHolder.getContext().getAuthentication());
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
							"Authentication Failed:"+authException.getMessage());
		}
	}

}
