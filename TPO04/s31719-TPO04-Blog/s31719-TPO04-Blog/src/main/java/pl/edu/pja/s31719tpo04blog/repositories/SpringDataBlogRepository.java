package pl.edu.pja.s31719tpo04blog.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.edu.pja.s31719tpo04blog.tables.Blog;

import java.util.List;

public interface SpringDataBlogRepository extends CrudRepository<Blog, Long> {
    @Query("SELECT b FROM Blog b")
    List<Blog> findAllBlogs();

    @Query("SELECT b FROM Blog b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Blog> findByNameContainingIgnoreCase(String name);

    Blog findByManagerId(Long managerId);

    @Query("SELECT b FROM Blog b" +
            " JOIN b.articles a" +
            " GROUP BY b " +
            "HAVING COUNT(a) > :minArticles")
    List<Blog> findBlogsByMinArticles(@Param("minArticles") Long minArticles);
}
