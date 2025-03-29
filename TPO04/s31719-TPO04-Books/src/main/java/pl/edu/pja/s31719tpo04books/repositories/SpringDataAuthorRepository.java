package pl.edu.pja.s31719tpo04books.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.s31719tpo04books.tables.Author;

public interface SpringDataAuthorRepository extends CrudRepository<Author, Long> {
}
