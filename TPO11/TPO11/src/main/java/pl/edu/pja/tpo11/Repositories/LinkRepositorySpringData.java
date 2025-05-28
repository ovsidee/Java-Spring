package pl.edu.pja.tpo11.Repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.tpo11.Model.Link;

import java.util.Optional;

public interface LinkRepositorySpringData extends CrudRepository<Link, String> {
    boolean existsByTargetUrl(String targetUrl);
    Optional<Link> findByName(String name);
}
