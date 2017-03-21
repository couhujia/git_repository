package hope.server.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeContorller {

	@RequestMapping("/")
	public String Index(){
		return "Hello world!";
	}
	
	@RequestMapping("/error")
	public String Error(){
		return "I am so sorry. There is an error.\n please ensure you last action!";
	}
	
	@RequestMapping("/login")
	public String loing(@RequestParam String phone,@RequestParam String password){
		return "login successful";
	}
	
}
