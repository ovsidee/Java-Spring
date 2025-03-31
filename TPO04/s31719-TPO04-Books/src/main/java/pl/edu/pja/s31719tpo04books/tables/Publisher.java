package pl.edu.pja.s31719tpo04books.tables;

import jakarta.persistence.*;

@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long ID;

    @Column(nullable = false)
    public String Name;

    @Column(nullable = false)
    public String Surname;

    public Publisher() {}

    public Publisher(String name, String surname) {
        this.Name = name;
        this.Surname = surname;
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

    public Long getID() {
        return ID;
    }
    public String getName() {
        return Name;
    }
    public String getSurname() {
        return Surname;
    }

    @Override
    public String toString() {
        return "Publisher ID: " + ID + ", Name: " + Name + ", Surname: "+ Surname;
    }
}
