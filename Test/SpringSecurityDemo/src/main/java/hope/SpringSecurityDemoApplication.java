package hope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class SpringSecurityDemoApplication {

	@RequestMapping("/")
	public String Greet(){
		return "index";
	}
	
	@RequestMapping("/hello")
    public String index() {
        return "hello";
    }
	
	@RequestMapping("/login")
    public String login() {
        return "login";
    }
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}
}
