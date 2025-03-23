package pl.edu.pja.tpo03;

import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {

    private final EntryRepositoryService entryRepositoryService;

    public DatabaseInitializer(EntryRepositoryService entryRepositoryService) {
        this.entryRepositoryService = entryRepositoryService;
    }

    public void initializeDatabase() {
        if (entryRepositoryService.getAllEntries().isEmpty()){
            entryRepositoryService.addEntry(new Entry("Apple", "Apfel", "Jabłko"));
            entryRepositoryService.addEntry(new Entry("House", "Haus", "Dom"));
            entryRepositoryService.addEntry(new Entry("Car", "Auto", "Samochód"));
            entryRepositoryService.addEntry(new Entry("Sun", "Sonne", "Słońce"));
            entryRepositoryService.addEntry(new Entry("Water", "Wasser", "Woda"));
            entryRepositoryService.addEntry(new Entry("Friend", "Freund", "Przyjaciel"));
            entryRepositoryService.addEntry(new Entry("Book", "Buch", "Książka"));
            entryRepositoryService.addEntry(new Entry("School", "Schule", "Szkoła"));
            entryRepositoryService.addEntry(new Entry("Dog", "Hund", "Pies"));
            entryRepositoryService.addEntry(new Entry("Love", "Liebe", "Miłość"));
            entryRepositoryService.addEntry(new Entry("Tree", "Baum", "Drzewo"));
        }
    }

}
