package pl.edu.pja.tpo11.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Link {
    @Id
    public String id;

    public String name;

    public String targetUrl;

    public String password;

    public Long visits;

    public Link() {}

    public Link(String id, String name, String targetUrl, String password) {
        this.id = id;
        this.name = name;
        this.targetUrl = targetUrl;
        this.password = password;
        this.visits = 0L;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVisits(Long visits) {
        this.visits = visits;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public String getPassword() {
        return password;
    }

    public Long getVisits() {
        return visits;
    }
}
