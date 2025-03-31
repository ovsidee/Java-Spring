package pl.edu.pja.s31719tpo04blog.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.edu.pja.s31719tpo04blog.tables.Role;

import java.util.List;
import java.util.Optional;

public interface SpringDataRoleRepository extends CrudRepository<Role, Long> {
    @Query("SELECT r FROM Role r")
    List<Role> findAllRoles();

    @Query("SELECT r FROM Role r" +
            " WHERE LOWER(r.name) " +
            "LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Role> findByNameContainingIgnoreCase(String name);

    @Query("SELECT r FROM Role r " +
            "LEFT JOIN r.users u " +
            "WHERE u IS NULL")
    List<Role> findRolesWithoutUsers();

    @Query("SELECT r FROM Role r" +
            " LEFT JOIN r.users u " +
            "GROUP BY r " +
            "HAVING COUNT(u) >= :minUsers")
    List<Role> findRolesByMinUsers(@Param("minUsers") Long minUsers);

    Optional<Role> findByName(String name);
}
