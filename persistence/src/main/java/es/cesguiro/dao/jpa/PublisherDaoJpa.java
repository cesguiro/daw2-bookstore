package es.cesguiro.dao.jpa;

import es.cesguiro.dao.PublisherDao;
import es.cesguiro.dao.jpa.entity.PublisherEntityJpa;
import es.cesguiro.repository.model.PublisherEntity;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class PublisherDaoJpa implements PublisherDao {

    private final EntityManager entityManager;

    public PublisherDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<PublisherEntity> findByBookIsbn(String isbn) {
        String sql = "SELECT " +
                "new es.cesguiro.repository.model.PublisherEntity(p.name, p.slug)" +
                "FROM PublisherEntityJpa p " +
                "JOIN p.books b " +
                "WHERE b.isbn = :isbn";
        return entityManager.createQuery(sql, PublisherEntity.class)
                .setParameter("isbn", isbn)
                .getResultList()
                .stream()
                .findFirst();
    }
}
