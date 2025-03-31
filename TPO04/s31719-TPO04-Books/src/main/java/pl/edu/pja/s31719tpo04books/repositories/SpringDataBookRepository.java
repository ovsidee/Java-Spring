package pl.edu.pja.s31719tpo04books.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.s31719tpo04books.tables.Book;

import java.util.List;


public interface SpringDataBookRepository extends CrudRepository<Book, Long> {
    @Query("SELECT b FROM Book b")
    List<Book> findAllBooks();
}
