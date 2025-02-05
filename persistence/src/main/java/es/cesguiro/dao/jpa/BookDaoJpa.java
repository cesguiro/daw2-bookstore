package es.cesguiro.dao.jpa;

import es.cesguiro.dao.BookDao;
import es.cesguiro.dao.jpa.entity.BookEntityJpa;
import es.cesguiro.repository.model.BookEntity;
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
    public Optional<BookEntity> findByIsbn(String isbn) {
        String query = "SELECT " +
                "new es.cesguiro.repository.model.BookEntity" +
                "(b.isbn, b.titleEs, b.titleEn, b.synopsisEs, b.synopsisEn, b.basePrice, b.discountPercentage, b.cover, b.publicationDate)" +
                "FROM BookEntityJpa b WHERE b.isbn = :isbn";
        try {
            return Optional.of(entityManager.createQuery(query, BookEntity.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<BookEntity> findAll(int page, int size) {
        String query = "SELECT " +
                "new es.cesguiro.repository.model.BookEntity" +
                "(b.isbn, b.titleEs, b.titleEn, b.synopsisEs, b.synopsisEn, b.basePrice, b.discountPercentage, b.cover, b.publicationDate)" +
                "FROM BookEntityJpa b";
        return entityManager.createQuery(query, BookEntity.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public long count() {
        String query = "SELECT COUNT(b) FROM BookEntityJpa b";
        return entityManager.createQuery(query, Long.class).getSingleResult();
    }

    /********** CriteriaBuilder version **********/

    public List<BookEntity> findAllCB(int page, int size) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookEntity> criteriaQuery = criteriaBuilder.createQuery(BookEntity.class);
        Root<BookEntityJpa> root = criteriaQuery.from(BookEntityJpa.class);

        criteriaQuery.select(criteriaBuilder.construct(
                BookEntity.class,
                root.get("isbn"),
                root.get("titleEs"),
                root.get("titleEn"),
                root.get("synopsisEs"),
                root.get("synopsisEn"),
                root.get("basePrice"),
                root.get("discountPercentage"),
                root.get("cover"),
                root.get("publicationDate")
        ));

        return entityManager
                .createQuery(criteriaQuery)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public Optional<BookEntity> findByIsbnCB(String isbn) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookEntity> criteriaQuery = criteriaBuilder.createQuery(BookEntity.class);
        Root<BookEntityJpa> root = criteriaQuery.from(BookEntityJpa.class);

        criteriaQuery.select(criteriaBuilder.construct(
                BookEntity.class,
                root.get("isbn"),
                root.get("titleEs"),
                root.get("titleEn"),
                root.get("synopsisEs"),
                root.get("synopsisEn"),
                root.get("basePrice"),
                root.get("discountPercentage"),
                root.get("cover"),
                root.get("publicationDate")
        ));
        criteriaQuery.where(criteriaBuilder.equal(root.get("isbn"), isbn));

        try {
            return Optional.of(entityManager
                    .createQuery(criteriaQuery)
                    .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public long countCB() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<BookEntityJpa> root = criteriaQuery.from(BookEntityJpa.class);

        criteriaQuery.select(criteriaBuilder.count(root));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
