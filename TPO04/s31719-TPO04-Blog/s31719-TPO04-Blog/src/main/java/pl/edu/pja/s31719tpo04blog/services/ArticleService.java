package pl.edu.pja.s31719tpo04blog.services;

import org.springframework.stereotype.Service;
import pl.edu.pja.s31719tpo04blog.repositories.SpringDataArticleRepository;

@Service
public class ArticleService {
    SpringDataArticleRepository articleRepository;

    public ArticleService(SpringDataArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    
}
