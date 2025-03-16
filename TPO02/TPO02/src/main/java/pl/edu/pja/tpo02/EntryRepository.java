package pl.edu.pja.tpo02;

import java.util.List;

public interface EntryRepository {
    List<Entry> getAllEntries();
    Entry getRandomEntry();
    void addEntry(Entry entry);
}
