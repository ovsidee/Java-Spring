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

    @Column(nullable = false)
    public int price;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Book_Author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();

    public Book() {}

    public Book(String title, int price, Publisher publisher, List<Author> authors) {
        this.Title = title;
        this.price = price;
        this.publisher = publisher;
        this.authors = authors;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    public void setPrice(int price) {
        this.price = price;
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
    public int getPrice() {
        return price;
    }
    public Publisher getPublisher() {
        return publisher;
    }
    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        return "Book ID: " + ID + ", Title: " + Title + ", Price:" + price + ", Publisher: [" + publisher + "], Authors: [" + authors +"]";
    }
}