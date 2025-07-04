package pl.edu.pja.tpo11.Constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordValidation {
    String message() default "{validation.password.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
