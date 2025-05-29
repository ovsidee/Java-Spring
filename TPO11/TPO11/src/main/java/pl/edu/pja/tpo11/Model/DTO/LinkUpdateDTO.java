package pl.edu.pja.tpo11.Model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import pl.edu.pja.tpo11.Constraints.ConditionalSize;
import pl.edu.pja.tpo11.Constraints.HttpsUrl;
import pl.edu.pja.tpo11.Constraints.UniqueTargetUrl;

public class LinkUpdateDTO {

    @ConditionalSize(min = 5, max = 20)
    @Nullable
    public String name;

    @HttpsUrl
    @UniqueTargetUrl
    @Nullable
    public String targetUrl;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Nullable
    public String password;

    public String getName() {
        return name;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTargetUrl(@Nullable String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setPassword(@Nullable String password) {
        this.password = password;
    }
}