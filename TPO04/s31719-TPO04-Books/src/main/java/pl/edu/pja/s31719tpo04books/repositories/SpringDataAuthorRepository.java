package pl.edu.pja.s31719tpo04books.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.s31719tpo04books.tables.Author;

import java.util.List;

public interface SpringDataAuthorRepository extends CrudRepository<Author, Long> {
    @Query("SELECT a FROM Author a")
    List<Author> findAllAuthors();
}
