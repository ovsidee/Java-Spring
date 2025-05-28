package pl.edu.pja.tpo11.Constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isBlank()) {
            return true;
        }

        List<String> errors = new ArrayList<>();
        if (!password.matches(".*[a-z].*")) errors.add("{validation.password.missing_lowercase}");
        if (password.replaceAll("[^A-Z]", "").length() < 2) errors.add("{validation.password.missing_uppercase}");
        if (password.replaceAll("\\D", "").length() < 3) errors.add("{validation.password.missing_digits}");
        if (password.replaceAll("[a-zA-Z0-9]", "").length() < 4) errors.add("{validation.password.missing_special}");
        if (password.length() < 10) errors.add("{validation.password.too_short}");

        if (!errors.isEmpty()) {
            context.disableDefaultConstraintViolation();
            errors.forEach(error -> context.buildConstraintViolationWithTemplate(error).addConstraintViolation());
            return false;
        }
        return true;
    }
}
