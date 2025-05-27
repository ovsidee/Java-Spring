package pl.edu.pja.tpo11.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pl.edu.pja.tpo11.Constraints.HttpsUrl;
import pl.edu.pja.tpo11.Constraints.PasswordValidation;

@Entity
public class Link {

    @Id
    public String id;

    @Size(min = 5, max = 20)
    public String name;

    @NotNull
    @NotBlank
    @HttpsUrl
    public String targetUrl;

    @PasswordValidation
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
