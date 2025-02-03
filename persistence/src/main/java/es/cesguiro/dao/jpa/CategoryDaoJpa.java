package es.cesguiro.dao.jpa;

import es.cesguiro.dao.CategoryDao;
import es.cesguiro.dao.jpa.entity.CategoryEntityJpa;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class CategoryDaoJpa implements CategoryDao {

    private final EntityManager entityManager;

    public CategoryDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<CategoryEntityJpa> findByBookIsbn(String isbn) {
        String sql = "SELECT c.* FROM categories c " +
                "JOIN books b ON b.category_id = c.id " +
                "WHERE b.isbn = :isbn";
        return entityManager.createNativeQuery(sql, CategoryEntityJpa.class)
                .setParameter("isbn", isbn)
                .getResultList()
                .stream()
                .findFirst();
    }
}
