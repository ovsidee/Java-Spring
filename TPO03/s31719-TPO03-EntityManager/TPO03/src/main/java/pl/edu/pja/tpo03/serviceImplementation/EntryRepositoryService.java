package pl.edu.pja.tpo03.serviceImplementation;

import org.springframework.stereotype.Service;
import pl.edu.pja.tpo03.exception.EntryNotFoundException;
import pl.edu.pja.tpo03.entries.Entry;
import pl.edu.pja.tpo03.repository.EntryRepository;

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

    public Entry getRandomEntry() throws EntryNotFoundException {
        return entryRepository.findById(
                random.nextLong(entryRepository.getMaxId())
        ).orElseThrow(EntryNotFoundException::new);
    }

    public Optional<Entry> findById(Long id) {
        return entryRepository.findById(id);
    }

    public void addEntry(Entry entry) {
        Long nextId = entryRepository.getMaxId() + 1;
        entry.setID(nextId);

        entryRepository.addEntry(entry);
    }

    public void deleteById(Long id) throws EntryNotFoundException {
        entryRepository.deleteById(id);
    }

    public Entry update(Entry entry) throws EntryNotFoundException  {
        return entryRepository.update(entry);
    }

    public List<Entry> getAllEntriesSorted(String language, String order) {
        return entryRepository.getAllEntriesSorted(language, order);
    }

    public List<Entry> searchEntries(String languageToSearch, String word) {
        return entryRepository.searchByLanguage(languageToSearch, word);
    }

}