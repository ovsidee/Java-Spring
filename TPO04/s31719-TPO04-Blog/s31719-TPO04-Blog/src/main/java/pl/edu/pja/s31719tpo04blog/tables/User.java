package pl.edu.pja.s31719tpo04blog.tables;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String email;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    public Set<Article> articles = new HashSet<>();

    @OneToOne(mappedBy = "manager", cascade = CascadeType.ALL)
    public Blog managedBlog;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    public Set<Role> roles = new HashSet<>();

    public User() {}

    public User(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User ID: " + id + ", Email: " + email + ", roles: " + roles;
    }

}
