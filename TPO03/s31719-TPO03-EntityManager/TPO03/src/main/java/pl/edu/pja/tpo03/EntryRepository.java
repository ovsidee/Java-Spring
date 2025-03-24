package pl.edu.pja.tpo03;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class EntryRepository {
    private final EntityManager entityManager;

    public EntryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Entry> getAllEntries() {
        TypedQuery<Entry> query = entityManager.createQuery(
                "SELECT e FROM Entry e", Entry.class
        );
        return query.getResultList();
    }

    public Optional<Entry> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Entry.class, id));
    }

    public Long getMaxId() {
        String queryString = "SELECT COALESCE(MAX(e.ID), 0) FROM Entry e";
        TypedQuery<Long> query = entityManager.createQuery(
                queryString, Long.class
        );
        return query.getSingleResult();
    }

    public List<Entry> getAllEntriesSorted(String translationLanguage, String order) {
        String queryString = "SELECT e FROM Entry e ORDER BY e." + translationLanguage + " " + order;

        TypedQuery<Entry> query = entityManager.createQuery(
                queryString, Entry.class
        );
        return query.getResultList();
    }


    @Transactional
    public void addEntry(Entry entry) {
        entityManager.persist(entry);
    }

    @Transactional
    public void deleteById(Long id) throws EntryNotFoundException {
        Entry entry = findById(id)
                .orElseThrow(EntryNotFoundException::new);
        entityManager.remove(entry);
    }

    @Transactional
    public Entry update(Entry entry) throws EntryNotFoundException  {
        Entry dbEntry = findById(entry.getID())
                .orElseThrow(EntryNotFoundException::new);
        dbEntry.setTranslationEnglish(entry.getTranslationEnglish());
        dbEntry.setTranslationPolish(entry.getTranslationPolish());
        dbEntry.setTranslationGerman(entry.getTranslationGerman());
        return dbEntry;
    }

    public List<Entry> searchByLanguage(String languageToSearch, String word) {
        String queryString = "SELECT e FROM Entry e WHERE e." + languageToSearch + " LIKE :word";

        TypedQuery<Entry> query = entityManager.createQuery(
                queryString, Entry.class
        );

        query.setParameter("word", "%" + word + "%");

        return query.getResultList();
    }

}