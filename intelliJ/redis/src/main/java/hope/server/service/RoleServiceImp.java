package hope.server.service;

import hope.server.domian.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Administrator on 2017/4/14.
 */
@Service
@Transactional
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Cacheable(value = "role", key = "#name")
    public Optional<Role> FindByName(String name) {
        return this.roleRepository.findByName(name);
    }

    @Override
    public Optional<Role> FindById(long id) {
        return this.roleRepository.findById(id);
    }

    @Override
    public Collection<Role> FindAll() {
        return this.roleRepository.findAll();
    }

    @Override
    @CacheEvict(value = "role", key = "#name")
    public void DeleteByName(String name) {
        this.roleRepository.deleteByName(name);
    }

    @Override
    @CachePut(value = "role", key = "#name")
    public Optional<Role> Save(String name) {
        return this.roleRepository.save(name);
    }
}
