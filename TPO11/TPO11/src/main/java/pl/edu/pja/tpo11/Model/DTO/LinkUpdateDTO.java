package pl.edu.pja.tpo11.Model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import pl.edu.pja.tpo11.Constraints.HttpsUrl;
import pl.edu.pja.tpo11.Constraints.PasswordValidation;
import pl.edu.pja.tpo11.Constraints.UniqueTargetUrl;

public class LinkUpdateDTO {

    @Size(min = 5, max = 20)
    public String name;

    @HttpsUrl
    @UniqueTargetUrl
    @Nullable
    public String targetUrl;

    @PasswordValidation
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
}
