package pl.edu.pja.tpo11.Constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueTargetUrlValidator.class)
public @interface UniqueTargetUrl {
    String message() default "{validation.url.not_unique}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
