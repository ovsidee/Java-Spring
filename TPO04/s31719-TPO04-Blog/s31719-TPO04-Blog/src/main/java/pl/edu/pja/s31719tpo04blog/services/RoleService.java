package pl.edu.pja.s31719tpo04blog.services;

import org.springframework.stereotype.Service;
import pl.edu.pja.s31719tpo04blog.repositories.SpringDataRoleRepository;
import pl.edu.pja.s31719tpo04blog.tables.Role;
import java.util.Optional;

@Service
public class RoleService {
    private final SpringDataRoleRepository roleRepository;

    public RoleService(SpringDataRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> getRole(Long id) {
        return roleRepository.findById(id);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
