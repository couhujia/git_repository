package hope.server.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeContorller {

	@RequestMapping("/")
	public String Index(){
		return "Hello world!";
	}
	
}
