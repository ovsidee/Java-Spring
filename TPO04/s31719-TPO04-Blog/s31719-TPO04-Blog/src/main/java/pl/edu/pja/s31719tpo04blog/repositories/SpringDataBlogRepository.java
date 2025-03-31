package pl.edu.pja.s31719tpo04blog.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.s31719tpo04blog.tables.Blog;

import java.util.List;

public interface SpringDataBlogRepository extends CrudRepository<Blog, Long> {
    @Query("SELECT b FROM Blog b")
    List<Blog> findAllBlogs();
}
