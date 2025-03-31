package pl.edu.pja.s31719tpo04books.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.s31719tpo04books.tables.Publisher;

import java.util.List;

public interface SpringDataPublisherRepository extends CrudRepository<Publisher, Long> {
    @Query("SELECT p FROM Publisher p")
    List<Publisher> findAllPublisher();
}
