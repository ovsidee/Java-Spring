package pl.edu.pja.tpo12.Models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "USER_ROLE")
public class UserRole  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ROLE_ID")
    private Long id;

    @Column(nullable = false, unique = true, length = 32)
    private String name;

    @Column(length = 255)
    private String description;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String d){
        this.description = d;
    }


    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole role)) return false;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserRole{id=%d, name='%s'}".formatted(id, name);
    }
}