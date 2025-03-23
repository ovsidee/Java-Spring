package pl.edu.pja.tpo03;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileServiceImplementation implements FileService {
    public String filename;
    public final EntryRepositoryService repositoryService;

    public FileServiceImplementation(EntryRepositoryService repositoryService, @Value("${pl.edu.pja.tpo03.filename}") String filename) {
        this.repositoryService = repositoryService;
        this.filename = filename;
    }

    public void loadWords() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    repositoryService.addEntry(new Entry(parts[0], parts[1], parts[2]));
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
