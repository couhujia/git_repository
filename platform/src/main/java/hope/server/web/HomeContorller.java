package hope.server.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeContorller {

/*	private final AuthenticationManager am;
	
	public HomeContorller(AuthenticationManager am){
		this.am=am;
	}
	*/
	protected static Logger logger=LoggerFactory.getLogger(HomeContorller.class);
	
	@RequestMapping("/")
	public String Index(){
		return "Hello world!";
	}
	
	@RequestMapping("/test")
	public String Error(){
		return "I am so sorry. There is an error.\n please ensure you last action!";
	}
	
/*	@RequestMapping("/login")
	public String login(@RequestParam String phone,@RequestParam String password){
		Authentication request = new UsernamePasswordAuthenticationToken(phone, password);
		Authentication result = am.authenticate(request);
		return "successful";
	}*/

/*	@RequestMapping("/login")
	public String login(){
		logger.info("in the home Security context contains "+SecurityContextHolder.getContext().getAuthentication());
		return "hello world";
	}*/
	
}
