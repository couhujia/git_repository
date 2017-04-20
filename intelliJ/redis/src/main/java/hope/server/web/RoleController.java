package hope.server.web;

import hope.server.domian.Role;
import hope.server.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Administrator on 2017/4/14.
 */
@RestController
public class RoleController {
    private final RoleService roleService;
    protected static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping("/role/findAll")
    public Collection<Role> FindAll() {
        return this.roleService.FindAll();
    }

    @RequestMapping("/role/findByName")
    public Role FindByName(@RequestParam String name) {
        Optional<Role> role = this.roleService.FindByName(name);
        if (role.isPresent()) {
            return role.get();
        } else {
            return null;
        }

    }

    @RequestMapping("/role/findById")
    public Optional<Role> FindById(@RequestParam String id) {
        return this.roleService.FindById(Long.valueOf(id));
    }

    @RequestMapping("/role/save")
    //@PreAuthorize("hasRole('superadmin')")
    public Optional<Role> Save(@RequestParam String name) {
        return this.roleService.Save(name);
    }

    @RequestMapping("/role/deleteByName")
    //@PreAuthorize("hasRole('superadmin')")
    public String Delete(@RequestParam String name) {
        this.roleService.DeleteByName(name);
        return "delete Successful";
    }


}
