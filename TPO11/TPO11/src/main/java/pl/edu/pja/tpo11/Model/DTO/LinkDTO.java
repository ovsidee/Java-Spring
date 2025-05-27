package pl.edu.pja.tpo11.Model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LinkDTO {
    public String id;
    public String name;
    public String targetUrl;
    public String redirectUrl;
    public Long visits;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String password;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setVisits(Long visits) {
        this.visits = visits;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public Long getVisits() {
        return visits;
    }

    public String getPassword() {
        return password;
    }
}
