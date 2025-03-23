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

    public Optional<Entry> findById(Long id) {
        return Optional.ofNullable(entryRepository.getEntityManager().find(Entry.class, id));
    }

    @Transactional
    public void addEntry(Entry entry) {
        entryRepository.getEntityManager().persist(entry);
    }

    @Transactional
    public void deleteById(Long id)   {
        findById(id).ifPresent(entryRepository.getEntityManager()::remove);
    }

    @Transactional
    public Entry update(Entry entry) throws EntryNotFoundException  {
        Entry dbEntry = findById(entry.getID())
                .orElseThrow(EntryNotFoundException::new);
        dbEntry.setTranslationEnglish(entry.getTranslationEnglish());
        dbEntry.setTranslationPolish(entry.getTranslationPolish());
        dbEntry.setTranslationGerman(entry.getTranslationGerman());
        return entryRepository.getEntityManager().merge(dbEntry);
    }

}