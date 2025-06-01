package pl.edu.pja.tpo12.Repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.tpo12.Models.Publisher;

import java.util.Optional;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
    Optional<Publisher> findByName(String name);
}