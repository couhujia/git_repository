package hope.server.web;

import java.util.List;

//import hope.server.service.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hope.server.domain.Role;
import hope.server.domain.User;
import hope.server.service.RoleRepository;
import hope.server.service.UserRepository;

@RestController
@Transactional
public class HopeContorller {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	//private UserRepository userRepository;
	
	@RequestMapping("/")
	public String Greet(){
		logger.info("hujia's program start!");
		return "good";
	}
	
	@RequestMapping("/Role/GetByRoleId")
	public String RoleGetByRoleId(@RequestParam(value="id",defaultValue="1") long id){
		return this.roleRepository.findById(id).getName();
	}
	
	@RequestMapping("/Role/GetByRoleName")
	public long RoleGetByRoleName(@RequestParam(value="name",defaultValue="admin") String name){
		return this.roleRepository.findByName(name).getId();
	}
	
	@RequestMapping("/Role/DeleteByRoleName")
	public String RoleDeleteByRoleName(@RequestParam(value="name")String name){
		 this.roleRepository.deleteByName(name);
		 return "delete successful";
	}
	
	@RequestMapping("/Role/GetRoleAll")
	public List<Role> RoleGetAll(){
		return this.roleRepository.findAll();
	}
	
	@RequestMapping("/Role/Save")
	public Role Save(@RequestParam(value="name",defaultValue="admin") String name){
		return this.roleRepository.save(new Role(name));
	}
	
	@RequestMapping("/User/GetByName")
	public String UserGetByName(@RequestParam(value="name",defaultValue="xiaofeng")String name){
		return this.userRepository.findByName(name).getEmail();
	}
	
	@RequestMapping("/User/GetAll")
	public List<User> UserGetAll(){
		return this.userRepository.findAll();
	}
	
	@RequestMapping("/User/Save")
	public User UserSave(@RequestParam(value="name")String name,@RequestParam(value="roleName")
						String roleName,@RequestParam(value="email") String email,
						@RequestParam(value="password") String password,
						@RequestParam(value="phone") String phone){
		Role role=this.roleRepository.findByName(roleName);
		return this.userRepository.save(new User(name,phone,email,password,role));
	}
}
