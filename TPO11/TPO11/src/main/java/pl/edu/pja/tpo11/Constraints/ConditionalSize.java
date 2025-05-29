package pl.edu.pja.tpo11.Constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ConditionalSizeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConditionalSize {
    String message() default "{validation.name.size}";
    int min() default 0;
    int max() default Integer.MAX_VALUE;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
