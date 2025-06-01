package pl.edu.pja.tpo12.Repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.tpo12.Models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {}