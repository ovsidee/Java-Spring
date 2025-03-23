package pl.edu.pja.tpo03;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntryRepository {
    public List<Entry> entries;

    public EntryRepository(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Entry> getAllEntries() {
        return entries;
    }

}