package pl.edu.pja.tpo03;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("SELECT e FROM Entry e WHERE e.translationEnglish LIKE %:word%")
    List<Entry> searchByEnWord(@Param("word") String word);

    @Query("SELECT e FROM Entry e WHERE e.translationGerman LIKE %:word%")
    List<Entry> searchByDeWord(@Param("word") String word);

    @Query("SELECT e FROM Entry e WHERE e.translationPolish LIKE %:word%")
    List<Entry> searchByPlWord(@Param("word") String word);
}