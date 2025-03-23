package pl.edu.pja.tpo03;

import org.springframework.stereotype.Service;

import java.util.List;
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
                random.nextInt(getAllEntries().size())
        );
    }

    public void addEntry(Entry entry) {
        if (!entryRepository.getAllEntries().contains(entry)) {
            entryRepository.getAllEntries().add(entry);
        }
    }
}