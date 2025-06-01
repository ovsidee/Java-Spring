package pl.edu.pja.tpo12.Repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.tpo12.Models.Author;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
}