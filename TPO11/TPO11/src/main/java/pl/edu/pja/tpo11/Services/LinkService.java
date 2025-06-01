package pl.edu.pja.tpo11.Services;

import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pja.tpo11.Model.DTO.LinkDTO;
import pl.edu.pja.tpo11.Model.DTO.LinkUpdateDTO;
import pl.edu.pja.tpo11.Model.Link;
import pl.edu.pja.tpo11.Repositories.LinkRepositorySpringData;

import jakarta.validation.Validator;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class LinkService {

    private final LinkRepositorySpringData repository;
    private final Validator validator;

    public LinkService(LinkRepositorySpringData repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Link create(LinkDTO dto) {
        String id = generateRandomId(10);

        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            dto.setPassword(null);
        }

        Link link = LinkDTOMapper.fromDTO(dto, id);

        Set<ConstraintViolation<Link>> errors = validator.validate(link);
        if (errors.isEmpty()) {
            return repository.save(link);
        } else {
            errors.forEach(error -> System.out.println(
                    "\"" + error.getPropertyPath() + "\": \"" + error.getMessage() + "\" (" + error.getInvalidValue() + ")"
            ));
        }
        return null;
    }


    public boolean update(String id, LinkUpdateDTO dto) {
        if (id == null || id.isBlank())
            return false;

        Optional<Link> linkOptional = findById(id);
        if (linkOptional.isEmpty())
            return false;

        Link existingLink = linkOptional.get();

        String existingPassword = existingLink.getPassword();
        String submittedPassword = dto.getPassword();

        boolean passwordRequired = existingPassword != null && !existingPassword.isBlank();

        if (passwordRequired && (submittedPassword == null || !existingPassword.equals(submittedPassword))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "wrong password");
        }

        LinkDTOMapper.updateEntityFromUpdateDTO(existingLink, dto);

        Set<ConstraintViolation<Link>> errors = validator.validate(existingLink);
        if (errors.isEmpty()) {
            repository.save(existingLink);
        }
        else {
            errors.forEach(error ->
                    System.out.println(
                            "\"" + error.getPropertyPath() + "\": \"" + error.getMessage() + "\" (" + error.getInvalidValue() + ")"
                    )
            );
            return false;
        }
        return true;
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

    public Iterable<Link> findAll() {
        return repository.findAll();
    }

    public Optional<Link> findByName(String name) {
        return repository.findByName(name);
    }

    public boolean verifyPassword(String id, String password) {
        Optional<Link> linkOpt = repository.findById(id);
        return linkOpt.isPresent() && linkOpt.get().getPassword().equals(password);
    }
}