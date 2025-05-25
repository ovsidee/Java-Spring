package pl.edu.pja.tpo10.Repositories;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.tpo10.Models.Link;


public interface LinkRepositorySpringData extends CrudRepository<Link, String> {}