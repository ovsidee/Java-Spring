package pl.edu.pja.tpo02.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pja.tpo02.entries.Entry;

import java.util.List;
import java.util.Random;

@Repository
public class EntryRepository {
    public List<Entry> entries;
    public Random random;

    public EntryRepository(List<Entry> entries, Random random) {
        this.entries = entries;
        this.random = random;
    }

    public List<Entry> getAllEntries() {
        return entries;
    }

    public Entry getRandomEntry() {
        return entries.get(random.nextInt(entries.size()));
    }

    public void addEntry(Entry entry) {
        entries.add(entry);
    }

}
