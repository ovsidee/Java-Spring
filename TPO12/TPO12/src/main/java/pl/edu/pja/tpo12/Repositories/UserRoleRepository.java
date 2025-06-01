package pl.edu.pja.tpo12.Repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.tpo12.Models.UserRole;

import java.util.Optional;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
    Optional<UserRole> findByName(String name);
}