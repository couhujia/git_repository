package hope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path="/demo")
public class MainController {

		@Autowired
		private UserRepository userRepository;
		
		@GetMapping(path="/add")
		public @ResponseBody String addNewUser(@RequestParam String name
				,@RequestParam String email){
			User n=new User();
			n.setName(name);
			n.setEmail(email);
			this.userRepository.save(n);
			return "Saved";
		}
		
		@GetMapping(path="/all")
		public @ResponseBody Iterable<User> getAllUsers() {
			// This returns a JSON or XML with the users
			return userRepository.findAll();
		}
}
