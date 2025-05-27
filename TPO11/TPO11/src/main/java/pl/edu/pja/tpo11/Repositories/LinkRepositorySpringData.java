package pl.edu.pja.tpo11.Repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.tpo11.Model.Link;

public interface LinkRepositorySpringData extends CrudRepository<Link, String> {
    boolean existsByTargetUrl(String targetUrl);
}
