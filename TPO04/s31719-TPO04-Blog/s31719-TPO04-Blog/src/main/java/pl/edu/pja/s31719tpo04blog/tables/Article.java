package pl.edu.pja.s31719tpo04blog.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    public User author;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    public Blog blog;

    public Article() {}

    public Article(String title, User author, Blog blog) {
        this.title = title;
        this.author = author;
        this.blog = blog;
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public User getAuthor() {
        return author;
    }
    public Blog getBlog() {
        return blog;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(User author) {
        this.author = author;
    }
    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "Article ID: " + id + ", Title" + title + ", Author " + author + ", Blog" + blog;
    }
}
