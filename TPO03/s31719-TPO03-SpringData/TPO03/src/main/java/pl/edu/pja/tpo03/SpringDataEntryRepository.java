package pl.edu.pja.tpo03;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Random;

public interface SpringDataEntryRepository extends JpaRepository<Entry, Long> {

    default Entry getRandomEntry() {
        List<Entry> entries = findAll();
        if (entries.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return entries.get(random.nextInt(entries.size()));
    }

}