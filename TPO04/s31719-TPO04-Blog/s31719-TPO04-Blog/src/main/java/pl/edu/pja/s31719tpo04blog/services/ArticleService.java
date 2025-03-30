package pl.edu.pja.s31719tpo04blog.services;

import org.springframework.stereotype.Service;
import pl.edu.pja.s31719tpo04blog.repositories.SpringDataArticleRepository;
import pl.edu.pja.s31719tpo04blog.tables.Article;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    public SpringDataArticleRepository articleRepository;

    public ArticleService(SpringDataArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Iterable<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article addArticle(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> searchArticles(String title) {
        return articleRepository.findByTitleContainingIgnoreCase(title);
    }

    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    public Optional<Article> findArticleById(Long id) {
        return articleRepository.findById(id);
    }
}
