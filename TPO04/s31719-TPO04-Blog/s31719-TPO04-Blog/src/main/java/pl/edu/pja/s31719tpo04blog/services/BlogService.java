package pl.edu.pja.s31719tpo04blog.services;

import org.springframework.stereotype.Service;
import pl.edu.pja.s31719tpo04blog.repositories.SpringDataBlogRepository;

@Service
public class BlogService {
    SpringDataBlogRepository blogRepository;

    public BlogService(SpringDataBlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

}
