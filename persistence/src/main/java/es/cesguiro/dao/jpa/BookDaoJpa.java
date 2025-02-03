package es.cesguiro.dao.jpa;

import es.cesguiro.dao.BookDao;
import es.cesguiro.dao.jpa.entity.BookEntityJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public class BookDaoJpa implements BookDao {

    private final EntityManager entityManager;

    public BookDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<BookEntityJpa> findByIsbn(String isbn) {
        String query = "SELECT b FROM BookEntityJpa b WHERE b.isbn = :isbn";
        try {
            return Optional.of(entityManager.createQuery(query, BookEntityJpa.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<BookEntityJpa> findAll(int page, int size) {
        String query = "SELECT b FROM BookEntityJpa b";
        return entityManager.createQuery(query, BookEntityJpa.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public long count() {
        String query = "SELECT COUNT(b) FROM BookEntityJpa b";
        return entityManager.createQuery(query, Long.class).getSingleResult();
    }

    public List<BookEntityJpa> findAllAC() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookEntityJpa> criteriaQuery = criteriaBuilder.createQuery(BookEntityJpa.class);
        Root<BookEntityJpa> root = criteriaQuery.from(BookEntityJpa.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
