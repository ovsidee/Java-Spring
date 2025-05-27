package pl.edu.pja.tpo11.Services;

import jakarta.validation.ConstraintViolation;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.edu.pja.tpo11.Model.DTO.LinkDTO;
import pl.edu.pja.tpo11.Model.Link;
import pl.edu.pja.tpo11.Repositories.LinkRepositorySpringData;

import jakarta.validation.Validator;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
@Validated
public class LinkService {
    public final LinkRepositorySpringData repository;
    public final Validator validator;

    public LinkService(LinkRepositorySpringData repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Link create(LinkDTO dto) {
        String id = generateRandomId(10);
        Link link = LinkDTOMapper.fromDTO(dto, id);

        Set<ConstraintViolation<Link>> errors = validator.validate(link);
        if (errors.isEmpty()) {
            return repository.save(link);
        } else {
            errors.forEach(error ->
                    System.out.println(
                            "\"" + error.getPropertyPath() + "\": \"" + error.getMessage() + "\" (" + error.getInvalidValue() + ")"
                    )
            );
        }
        return null;
    }

    public Optional<Link> findById(String id) {
        return repository.findById(id);
    }

    private String generateRandomId(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < length; i++)
            id.append(chars.charAt(random.nextInt(chars.length())));

        return id.toString();
    }

    public void incrementVisitCount(Link link) {
        link.setVisits(link.getVisits() + 1);
        repository.save(link);
    }

    public void save(Link link) {
        repository.save(link);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}

