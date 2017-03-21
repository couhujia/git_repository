package hope.server.web;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hope.server.domain.Role;
import hope.server.domain.User;
import hope.server.service.RoleService;
import hope.server.service.UserService;

@RestController
public class UserController {
	
	private final UserService userService;
	private final RoleService roleService;
	
	@Autowired
	public UserController(UserService userService,RoleService roleService){
		this.userService=userService;
		this.roleService=roleService;
	}
	
	@RequestMapping("/user/findByName")
	public Optional<User> FindByName(@RequestParam String name){
		return this.userService.FindByName(name);
	}
	
	@RequestMapping("/user/findAll")
	public Collection<User> FindAll(){
		return this.userService.FindAll();
	}
	
	@RequestMapping("/user/findByEmial")
	public Optional<User> FindByEmail(@RequestParam String email){
		return this.userService.FindByEmail(email);
	}
	
	@RequestMapping("/user/findByPhone")
	public Optional<User> FindByPhone(@RequestParam String phone){
		return this.userService.FindByPhone(phone);
	}
	
	@RequestMapping("/user/save")
	@PreAuthorize("hasRole('superadmin')") //relate to ROLE_superadmin
	public Optional<User> Save(@RequestParam String name,@RequestParam String roleName,
	@RequestParam String email,@RequestParam String password,@RequestParam String phone){
		Optional<Role> role=this.roleService.FindByName(roleName);
		if(role.isPresent()){
			return this.userService.Save(new User(name,phone,email,password,role.get()));
		}
		else{
			return null;
		}
			
	}
}
