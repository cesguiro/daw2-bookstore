package es.cesguiro.dao.jpa;

import es.cesguiro.dao.CategoryDao;
import es.cesguiro.dao.jpa.entity.CategoryEntityJpa;
import es.cesguiro.repository.model.CategoryEntity;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class CategoryDaoJpa implements CategoryDao {

    private final EntityManager entityManager;

    public CategoryDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<CategoryEntity> findByBookIsbn(String isbn) {
        String sql = "SELECT " +
                "new es.cesguiro.repository.model.CategoryEntity(c.nameEs, c.nameEn, c.slug) " +
                "FROM CategoryEntityJpa c " +
                "JOIN c.books b " +
                "WHERE b.isbn = :isbn";
        return entityManager.createQuery(sql, CategoryEntity.class)
                .setParameter("isbn", isbn)
                .getResultList()
                .stream()
                .findFirst();
        /*return entityManager.createNativeQuery(sql, CategoryEntityJpa.class)
                .setParameter("isbn", isbn)
                .getResultList()
                .stream()
                .findFirst();*/
    }
}
