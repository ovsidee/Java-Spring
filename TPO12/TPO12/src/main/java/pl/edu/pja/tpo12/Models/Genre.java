package pl.edu.pja.tpo12.Models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GENRE")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GENRE_ID")
    private Long id;

    @Column(name = "GENRE_NAME", unique = true, length = 50)
    private String genreName;

    @ManyToMany(mappedBy = "genres")
    private Set<Book> books = new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public String getGenreName() {
        return genreName;
    }

    public Set<Book> getBooks() {
        return books;
    }
}