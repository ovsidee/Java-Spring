package pl.edu.pja.s31719tpo04blog.services;

import org.springframework.stereotype.Service;
import pl.edu.pja.s31719tpo04blog.repositories.SpringDataRoleRepository;

@Service
public class RoleService {
    SpringDataRoleRepository roleRepository;

    public RoleService(SpringDataRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

}