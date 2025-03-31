package pl.edu.pja.s31719tpo04blog.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.s31719tpo04blog.tables.Role;

import java.util.List;

public interface SpringDataRoleRepository extends CrudRepository<Role, Long> {
    @Query("SELECT r FROM Role r")
    List<Role> findAllRoles();
}
