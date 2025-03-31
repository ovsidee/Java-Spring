package pl.edu.pja.s31719tpo04books.tables;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long ID;

    @Column(nullable = false)
    public String Name;

    @Column(nullable = false)
    public String Surname;

    @Column(nullable = false)
    public String Address;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Book> books = new ArrayList<>();

    public Publisher() {}

    public Publisher(String name, String surname, String address, List<Book> books) {
        this.Name = name;
        this.Surname = surname;
        this.Address = address;
        this.books = books;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
    public void setName(String name) {
        Name = name;
    }
    public void setSurname(String surname) {
        Surname = surname;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public void setAdress(String address) {
        Address = address;
    }

    public String getAddress() {
        return Address;
    }
    public Long getID() {
        return ID;
    }
    public String getName() {
        return Name;
    }
    public String getSurname() {
        return Surname;
    }
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Publisher ID: " + ID + ", Name: " + Name + ", Surname: "+ Surname + ", Books: " + books.stream().map(Book::getTitle).toList();
    }
}
