package es.cesguiro.dao.jpa;

import es.cesguiro.TestConfig;
import es.cesguiro.dao.PublisherDao;
import es.cesguiro.dao.jpa.entity.PublisherEntityJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
class PublisherDaoJpaTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PublisherDao publisherDao;

    @Test
    @DisplayName("Find category by book with existing ISBN should return a category")
    void findCategoryByBookWithExistingIsbnShouldReturnACategory() {
        String isbn = "9780142424179";
        Optional<PublisherEntityJpa> result = publisherDao.findByBookIsbn(isbn);
        assertAll(
                () -> assertNotNull(result.orElse(null), "Category should not be null"),
                () -> assertEquals("Editorial Sudamericana", result.get().getName(), "Name should match")
        );
    }

    @Test
    @DisplayName("Find category by book with non-existing ISBN should return an empty optional")
    void findCategoryByBookWithNonExistingIsbnShouldReturnAnEmptyOptional() {
        String isbn = "1234567890";
        assertTrue(publisherDao.findByBookIsbn(isbn).isEmpty(), "Optional should be empty");
    }

}