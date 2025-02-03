package es.cesguiro.dao.jpa;

import es.cesguiro.dao.PublisherDao;
import es.cesguiro.dao.jpa.entity.PublisherEntityJpa;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class PublisherDaoJpa implements PublisherDao {

    private final EntityManager entityManager;

    public PublisherDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<PublisherEntityJpa> findByBookIsbn(String isbn) {
        String sql = "SELECT p.* FROM publishers p " +
                "JOIN books b ON b.publisher_id = p.id " +
                "WHERE b.isbn = :isbn";
        return entityManager.createNativeQuery(sql, PublisherEntityJpa.class)
                .setParameter("isbn", isbn)
                .getResultList()
                .stream()
                .findFirst();
    }
}
