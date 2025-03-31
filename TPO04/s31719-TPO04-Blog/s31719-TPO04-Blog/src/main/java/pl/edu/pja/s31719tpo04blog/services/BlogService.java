package pl.edu.pja.s31719tpo04blog.services;

import org.springframework.stereotype.Service;
import pl.edu.pja.s31719tpo04blog.repositories.SpringDataBlogRepository;
import pl.edu.pja.s31719tpo04blog.tables.Blog;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final SpringDataBlogRepository blogRepository;

    public BlogService(SpringDataBlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAllBlogs();
    }

    public Optional<Blog> findBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public void addBlog(Blog blog) {
        blogRepository.save(blog);
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    public List<Blog> searchBlogs(String name) {
        return blogRepository.findByNameContainingIgnoreCase(name);
    }

    public Blog findBlogByManagerId(Long id) {
        return blogRepository.findByManagerId(id);
    }

    public List<Blog> findAllBlogsByMinArticles(Long minArticles) {
        return blogRepository.findBlogsByMinArticles(minArticles);
    }
}
