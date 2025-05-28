package pl.edu.pja.tpo11.Constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import pl.edu.pja.tpo11.Repositories.LinkRepositorySpringData;

@Component
public class UniqueTargetUrlValidator implements ConstraintValidator<UniqueTargetUrl, String> {

    private final LinkRepositorySpringData linkRepositorySpringData;

    public UniqueTargetUrlValidator(LinkRepositorySpringData linkRepositorySpringData) {
        this.linkRepositorySpringData = linkRepositorySpringData;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        return !linkRepositorySpringData.existsByTargetUrl(s);
    }
}
