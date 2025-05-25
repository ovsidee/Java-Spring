package pl.edu.pja.tpo10.Models.DTO;

public class LinkDTO {

    public String id;
    public String name;
    public String targetUrl;
    public String redirectUrl;
    public int visits;

    public LinkDTO() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTargetUrl() { return targetUrl; }
    public void setTargetUrl(String targetUrl) { this.targetUrl = targetUrl; }
    public String getRedirectUrl() { return redirectUrl; }
    public void setRedirectUrl(String redirectUrl) { this.redirectUrl = redirectUrl; }
    public int getVisits() { return visits; }
    public void setVisits(int visits) { this.visits = visits; }
}
