package pl.edu.pja.s31719tpo04blog.tables;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    public Set<Article> articles = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "manager_id")
    public User manager;

    public Blog() {}

    public Blog(String name, User manager) {
        this.name = name;
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Set<Article> getArticles() {
        return articles;
    }
    public User getManager() {
        return manager;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
    public void setManager(User manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Blog{id=" + id +
                ", name='" + name +
                "', managerId=" + (manager != null ? manager.getId() : "null")
                + "}";
    }

}
