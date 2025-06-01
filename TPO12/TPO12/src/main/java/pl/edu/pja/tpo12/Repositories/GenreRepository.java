package pl.edu.pja.tpo12.Repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.tpo12.Models.Genre;

import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Optional<Genre> findByGenreName(String name);
}