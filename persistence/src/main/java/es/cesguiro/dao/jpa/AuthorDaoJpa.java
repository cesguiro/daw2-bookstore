package es.cesguiro.dao.jpa;

import es.cesguiro.dao.AuthorDao;
import es.cesguiro.repository.model.AuthorEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AuthorDaoJpa implements AuthorDao {

    private final EntityManager entityManager;

    public AuthorDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<AuthorEntity> findAllByBookIsbn(String isbn) {
        String sql = "SELECT " +
                "new es.cesguiro.repository.model.AuthorEntity(" +
                "a.name, a.nationality, a.biographyEs, a.biographyEn, a.birthYear, a.deathYear, a.slug) " +
                "FROM AuthorEntityJpa a " +
                "JOIN a.books b " +
                "WHERE b.isbn = :isbn";
        return entityManager.createQuery(sql, AuthorEntity.class)
                .setParameter("isbn", isbn)
                .getResultList();
    }
}
