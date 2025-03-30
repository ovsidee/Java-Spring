package pl.edu.pja.s31719tpo04blog.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.s31719tpo04blog.tables.Blog;

public interface SpringDataBlogRepository extends CrudRepository<Blog, Long> {

}
