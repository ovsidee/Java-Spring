package pl.edu.pja.tpo03;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class EntryRepositoryService {
    private final EntryRepository entryRepository;
    private final Random random;

    public EntryRepositoryService(EntryRepository entryRepository, Random random) {
        this.entryRepository = entryRepository;
        this.random = random;
    }

    public List<Entry> getAllEntries() {
        TypedQuery<Entry> query = entryRepository.getEntityManager().createQuery(
                "SELECT e FROM Entry e", Entry.class
        );
        return query.getResultList();
    }

    public Entry getRandomEntry() {
        return getAllEntries().get(
                random.nextInt(getAllEntries().size())
        );
    }

    @Transactional
    public void addEntry(Entry entry) {
        entryRepository.getEntityManager().persist(entry);
    }

    public Optional findById(Long id){
        return Optional.ofNullable(entryRepository.getEntityManager().find(Entry.class, id));
    }

    @Transactional
    public void deleteById(Long id){
        findById(id).ifPresent(entryRepository.getEntityManager()::remove);
    }

}