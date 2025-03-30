package pl.edu.pja.s31719tpo04blog.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.s31719tpo04blog.tables.Article;

import java.util.List;

public interface SpringDataArticleRepository extends CrudRepository<Article, Long> {

    List<Article> findByTitleContainingIgnoreCase(String title);
}
