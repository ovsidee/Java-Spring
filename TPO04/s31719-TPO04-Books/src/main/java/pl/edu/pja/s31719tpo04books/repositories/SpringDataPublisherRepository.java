package pl.edu.pja.s31719tpo04books.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.s31719tpo04books.tables.Publisher;

public interface SpringDataPublisherRepository extends CrudRepository<Publisher, Long> {
}
