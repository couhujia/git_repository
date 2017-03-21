package hope.server.web;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hope.server.domain.Role;
import hope.server.service.RoleService;

@RestController
public class RoleController {
	
	private final RoleService roleService;
	
	@Autowired
	public RoleController(RoleService roleService){
		this.roleService=roleService;
	}
	
	@RequestMapping("/role/findAll")
	public Collection<Role> FindAll(){
		return this.roleService.FindAll();
	}
	
	@RequestMapping("/role/findByName")
	public Optional<Role> FindByName(@RequestParam String name){
		return this.roleService.FindByName(name);
	}
	
	@RequestMapping("/role/findById")
	public Optional<Role> FindById(@RequestParam String id){
		return this.roleService.FindByName(id);
	}
	
	@RequestMapping("/role/save")
	@PreAuthorize("hasRole('superadmin')")
	public Optional<Role> Save(@RequestParam String name){
		return this.roleService.Save(name);
	}
	
	@RequestMapping("/role/deleteByName")
	@PreAuthorize("hasRole('superadmin')")
	public String Delete(@RequestParam String name){
		this.roleService.DeleteByName(name);
		return "delete Successful";
	}
}
