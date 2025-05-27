package pl.edu.pja.tpo11.Constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HttpsUrlValidator implements ConstraintValidator<HttpsUrl, String> {
    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        return url != null && url.matches("^https://.+");
    }
}
