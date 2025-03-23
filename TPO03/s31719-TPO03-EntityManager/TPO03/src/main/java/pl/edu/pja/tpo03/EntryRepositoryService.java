package pl.edu.pja.tpo03;

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
        return entryRepository.getAllEntries();
    }

    public Entry getRandomEntry() {
        return entryRepository.getAllEntries().get(
                random.nextInt(entryRepository.getAllEntries().size())
        );
    }

    public Optional<Entry> findById(Long id) {
        return entryRepository.findById(id);
    }

    public void addEntry(Entry entry) {
        entryRepository.addEntry(entry);
    }

    public void deleteById(Long id) {
        entryRepository.deleteById(id);
    }

    public Entry update(Entry entry) throws EntryNotFoundException  {
        return entryRepository.update(entry);
    }

    public List<Entry> getAllEntriesSorted(String language, boolean ascending) {
        return entryRepository.getAllEntriesSorted(language, ascending);
    }
}