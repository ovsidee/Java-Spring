package pl.edu.pja.s31719tpo04books.tables;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false)
    private String Name;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public Author() {}

    public Author(String name, List<Book> books) {
        this.Name = name;
        this.books = books;
    }

    public Long getId() {
        return ID;
    }
    public String getName() {
        return Name;
    }
    public List<Book> getBooks() {
        return books;
    }

    public void setId(Long id) {
        this.ID = id;
    }
    public void setName(String name) {
        this.Name = name;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author ID: " + ID + ", Name: " + Name + ", Books: " + books.stream().map(Book::getTitle).toList();
    }
}