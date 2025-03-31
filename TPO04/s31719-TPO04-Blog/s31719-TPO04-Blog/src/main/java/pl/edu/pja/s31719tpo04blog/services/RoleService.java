package pl.edu.pja.s31719tpo04blog.services;

import org.springframework.stereotype.Service;
import pl.edu.pja.s31719tpo04blog.repositories.SpringDataRoleRepository;
import pl.edu.pja.s31719tpo04blog.tables.Role;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final SpringDataRoleRepository roleRepository;

    public RoleService(SpringDataRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAllRoles();
    }

    public Optional<Role> findRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public void addRole(Role role) {
        roleRepository.save(role);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    public List<Role> searchRoles(String name) {
        return roleRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Role> findAllRolesWithoutUsers(){
        return roleRepository.findRolesWithoutUsers();
    }

    public List<Role> findAllRolesByMinUsers(Long minUsers) {
        return roleRepository.findRolesByMinUsers(minUsers);
    }
}
