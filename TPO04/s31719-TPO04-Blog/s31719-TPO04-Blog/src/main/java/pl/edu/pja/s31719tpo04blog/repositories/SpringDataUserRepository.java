package pl.edu.pja.s31719tpo04blog.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.s31719tpo04blog.tables.User;

import java.util.List;

public interface SpringDataUserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u")
    List<User> findAllUsers();
}
