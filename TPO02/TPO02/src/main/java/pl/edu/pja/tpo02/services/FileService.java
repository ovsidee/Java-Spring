package pl.edu.pja.tpo02.services;

import pl.edu.pja.tpo02.entries.Entry;

public interface FileService {
    void loadWords();
    void saveWordToFile(Entry entry);
}
