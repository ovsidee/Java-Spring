package pl.edu.pja.tpo03;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class EntryRepository {
    private final EntityManager entityManager;

    public EntryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

}