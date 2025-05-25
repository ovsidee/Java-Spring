package pl.edu.pja.tpo10.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String shortId;

    public String name;

    public String targetUrl;

    public String password;

    public int visits;

    public Link() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getShortId() { return shortId; }
    public void setShortId(String shortId) { this.shortId = shortId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTargetUrl() { return targetUrl; }
    public void setTargetUrl(String targetUrl) { this.targetUrl = targetUrl; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public int getVisits() { return visits; }
    public void setVisits(int visits) { this.visits = visits; }
}
