package pl.edu.pja.tpo11.Services;

import org.springframework.stereotype.Service;
import pl.edu.pja.tpo11.Model.DTO.LinkDTO;
import pl.edu.pja.tpo11.Model.Link;
import pl.edu.pja.tpo11.Repositories.LinkRepositorySpringData;

import java.util.Optional;
import java.util.Random;

@Service
public class LinkService {
    public LinkRepositorySpringData repository;

    public LinkService(LinkRepositorySpringData repository) {
        this.repository = repository;
    }

    public Link create(LinkDTO dto) {
        String id = generateRandomId(10);
        Link link = LinkDTOMapper.fromDTO(dto, id);
        return repository.save(link);
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

