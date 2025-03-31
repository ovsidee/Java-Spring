package pl.edu.pja.s31719tpo04blog.services;

import org.springframework.stereotype.Service;
import pl.edu.pja.s31719tpo04blog.repositories.SpringDataArticleRepository;
import pl.edu.pja.s31719tpo04blog.tables.Article;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final SpringDataArticleRepository articleRepository;

    public ArticleService(SpringDataArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAllArticles();
    }

    public Optional<Article> findArticleById(Long id) {
        return articleRepository.findById(id);
    }

    public void addArticle(Article article) {
        articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    public List<Article> searchArticles(String title) {
        return articleRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Article> findArticlesByAuthorId(Long authorId) {
        return articleRepository.findByAuthorId(authorId);
    }

    public List<Article> findArticlesByBlogId(Long blogId) {
        return articleRepository.findByBlogId(blogId);
    }

    public List<Article> findArticlesByAuthorEmail(String email) {
        return articleRepository.findByAuthorEmail(email);
    }

    public List<Article> searchArticlesByKeyword(String keyword) {
        return articleRepository.searchByKeyword(keyword);
    }
}
