package pl.edu.pja.tpo11.Constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.tpo11.Repositories.LinkRepositorySpringData;

@Component
public class UniqueTargetUrlValidator implements ConstraintValidator<UniqueTargetUrl, String> {

    @Autowired
    private LinkRepositorySpringData repository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !repository.existsByTargetUrl(value);
    }
}
