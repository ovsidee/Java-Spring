package pl.edu.pja.tpo12.Repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.tpo12.Models.Genre;

public interface GenreRepository extends CrudRepository<Genre, Integer> {
}
