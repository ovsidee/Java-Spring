package pl.edu.pja.tpo02.serviceImplementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.edu.pja.tpo02.entries.Entry;
import pl.edu.pja.tpo02.repository.EntryRepository;
import pl.edu.pja.tpo02.services.FileService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileServiceImplementation implements FileService {
    public String filename;
    public final EntryRepository repository;

    public FileServiceImplementation(EntryRepository repository, @Value("${pl.edu.pja.tpo02.filename}") String filename) {
        this.repository = repository;
        this.filename = filename;
    }

    public void loadWords() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    repository.addEntry(new Entry(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveWordToFile(Entry entry) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write( "\n" + entry.getTranslationEnglish() + "," + entry.getTranslationGerman() + "," + entry.getTranslationPolish() + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}