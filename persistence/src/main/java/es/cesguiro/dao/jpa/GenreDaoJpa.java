package es.cesguiro.dao.jpa;

import es.cesguiro.dao.GenreDao;
import es.cesguiro.dao.jpa.entity.GenreEntityJpa;
import es.cesguiro.repository.model.GenreEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class GenreDaoJpa implements GenreDao {

    private final EntityManager entityManager;

    public GenreDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<GenreEntity> findAllByBookIsbn(String isbn) {
        String sql = "SELECT " +
                "new es.cesguiro.repository.model.GenreEntity(g.nameEs, g.nameEn, g.slug)" +
                "FROM GenreEntityJpa g " +
                "JOIN g.books b " +
                "WHERE b.isbn = :isbn";
        return entityManager.createQuery(sql, GenreEntity.class)
                .setParameter("isbn", isbn)
                .getResultList();
    }
}
