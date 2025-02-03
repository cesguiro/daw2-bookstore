package es.cesguiro.dao.jpa;

import es.cesguiro.dao.AuthorDao;
import es.cesguiro.dao.jpa.entity.AuthorEntityJpa;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AuthorDaoJpa implements AuthorDao {

    private final EntityManager entityManager;

    public AuthorDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<AuthorEntityJpa> findAllByBookIsbn(String isbn) {
        String sql = "SELECT a.* FROM authors a " +
                "JOIN books_authors ba ON a.id = ba.author_id " +
                "JOIN books b ON ba.book_id = b.id " +
                "WHERE b.isbn = :isbn";
        return entityManager.createNativeQuery(sql, AuthorEntityJpa.class)
                .setParameter("isbn", isbn)
                .getResultList();
    }
}
