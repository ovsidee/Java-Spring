package pl.edu.pja.s31719tpo04blog.services;

import org.springframework.stereotype.Service;
import pl.edu.pja.s31719tpo04blog.repositories.SpringDataBlogRepository;
import pl.edu.pja.s31719tpo04blog.tables.Blog;
import java.util.Optional;

@Service
public class BlogService {
    private final SpringDataBlogRepository blogRepository;

    public BlogService(SpringDataBlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Iterable<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Optional<Blog> findBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public Blog addBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
