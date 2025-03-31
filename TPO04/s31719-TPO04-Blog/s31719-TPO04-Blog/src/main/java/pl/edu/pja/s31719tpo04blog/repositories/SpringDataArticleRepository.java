package pl.edu.pja.s31719tpo04blog.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.edu.pja.s31719tpo04blog.tables.Article;

import java.util.List;

public interface SpringDataArticleRepository extends CrudRepository<Article, Long> {

    @Query("SELECT a FROM Article a")
    List<Article> findAllArticles();

    @Query("SELECT a FROM Article a " +
            "WHERE LOWER(a.title) " +
            "LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Article> findByTitleContainingIgnoreCase(String title);

    List<Article> findByAuthorId(Long authorId);

    List<Article> findByBlogId(Long blogId);

    @Query("SELECT a FROM Article a " +
            "WHERE LOWER(a.title)" +
            " LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Article> searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT a FROM Article a " +
            "WHERE a.author.email = :email")
    List<Article> findByAuthorEmail(@Param("email") String email);

}
