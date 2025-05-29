package pl.edu.pja.tpo11.Constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConditionalSizeValidator implements ConstraintValidator<ConditionalSize, String> {
    public int minSize;
    public int maxSize;

    @Override
    public void initialize(ConditionalSize constraintAnnotation) {
        this.minSize = constraintAnnotation.min();
        this.maxSize = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) return true;

        int len = value.length();
        return len >= minSize && len <= maxSize;
    }
}
