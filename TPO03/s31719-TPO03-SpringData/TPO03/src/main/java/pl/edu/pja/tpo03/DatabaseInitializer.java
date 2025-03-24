package pl.edu.pja.tpo03;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseInitializer {

    public SpringDataEntryRepository springDataEntryRepository;

    public DatabaseInitializer(SpringDataEntryRepository springDataEntryRepository) {
        this.springDataEntryRepository = springDataEntryRepository;
    }

    public void initializeDatabase() {
        if (springDataEntryRepository.findAll().isEmpty()) {
            springDataEntryRepository.save(new Entry("Apple", "Apfel", "Jabłko"));
            springDataEntryRepository.save(new Entry("House", "Haus", "Dom"));
            springDataEntryRepository.save(new Entry("Car", "Auto", "Samochód"));
            springDataEntryRepository.save(new Entry("Sun", "Sonne", "Słońce"));
            springDataEntryRepository.save(new Entry("Water", "Wasser", "Woda"));
            springDataEntryRepository.save(new Entry("Friend", "Freund", "Przyjaciel"));
            springDataEntryRepository.save(new Entry("Book", "Buch", "Książka"));
            springDataEntryRepository.save(new Entry("School", "Schule", "Szkoła"));
            springDataEntryRepository.save(new Entry("Dog", "Hund", "Pies"));
            springDataEntryRepository.save(new Entry("Love", "Liebe", "Miłość"));
            springDataEntryRepository.save(new Entry("Tree", "Baum", "Drzewo"));
        }
    }

}
