package hope.server.web;

import hope.server.domian.Role;
import hope.server.domian.User;
import hope.server.service.RoleService;
import hope.server.service.UserService;
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
public class UserController {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @RequestMapping("/user/findByName")
    public Optional<User> FindByName(@RequestParam String name) {
        return this.userService.FindByName(name);
    }

    @RequestMapping("/user/findAll")
    public Collection<User> FindAll() {
        return this.userService.FindAll();
    }

    @RequestMapping("/user/findByEmail")
    public Optional<User> FindByEmail(@RequestParam String email) {
        return this.userService.FindByEmail(email);
    }

    @RequestMapping("/user/findByPhone")
    public Optional<User> FindByPhone(@RequestParam String phone) {
        return this.userService.FindByPhone(phone);
    }

    @RequestMapping("/user/save")
    public Optional<User> Save(@RequestParam String name, @RequestParam String roleName,
                               @RequestParam String email, @RequestParam String password, @RequestParam String phone) {
        Optional<Role> role = this.roleService.FindByName(roleName);
        if (role.isPresent()) {
            return this.userService.Save(new User(name, phone, email, password, role.get()));
        } else {
            return null;
        }

    }
}
