package pl.edu.pja.tpo12.Repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.tpo12.Models.AppUser;

import java.util.Optional;

public interface UserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
}