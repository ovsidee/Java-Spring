package pl.edu.pja.s31719tpo04books.tables;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long ID;

    @Column(nullable = false)
    public String Title;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToMany
    @JoinTable(name = "Book_Author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();

    public Book(String title, Publisher publisher) {
        this.Title = title;
        this.publisher = publisher;
    }

    public Book(){}

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Long getID() {
        return ID;
    }

    public String getTitle() {
        return Title;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID=" + ID +
                ", Title='" + Title + '\'' +
                ", publisher=" + publisher +
                '}';
    }
}