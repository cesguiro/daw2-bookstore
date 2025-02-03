package es.cesguiro.dao.jpa;

import es.cesguiro.dao.GenreDao;
import es.cesguiro.dao.jpa.entity.GenreEntityJpa;
import jakarta.persistence.EntityManager;

import java.util.List;

public class GenreDaoJpa implements GenreDao {

    private final EntityManager entityManager;

    public GenreDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<GenreEntityJpa> findAllByBookIsbn(String isbn) {
        String sql = "SELECT g.* FROM genres g " +
                "JOIN books_genres bg ON g.id = bg.genre_id " +
                "JOIN books b ON bg.book_id = b.id " +
                "WHERE b.isbn = :isbn";
        return entityManager.createNativeQuery(sql, GenreEntityJpa.class)
                .setParameter("isbn", isbn)
                .getResultList();
    }
}
