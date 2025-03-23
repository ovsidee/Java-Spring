package pl.edu.pja.tpo03;

import org.springframework.data.repository.CrudRepository;

public interface SpringDataEntryRepository extends CrudRepository<Entry, Long> {}