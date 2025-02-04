package es.cesguiro.dao.jpa;

import es.cesguiro.TestConfig;
import es.cesguiro.dao.CategoryDao;
import es.cesguiro.dao.jpa.entity.CategoryEntityJpa;
import es.cesguiro.repository.model.CategoryEntity;
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
class CategoryDaoJpaTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CategoryDao categoryDao;

    @Test
    @DisplayName("Find category by book with existing ISBN should return a category")
    void findCategoryByBookWithExistingIsbnShouldReturnACategory() {
        String isbn = "9780142424179";
        Optional<CategoryEntity> result = categoryDao.findByBookIsbn(isbn);
        assertAll(
                () -> assertNotNull(result.orElse(null), "Category should not be null"),
                () -> assertEquals("New Releases", result.get().nameEn(), "Name should match")
        );
    }

    @Test
    @DisplayName("Find category by book with non-existing ISBN should return an empty optional")
    void findCategoryByBookWithNonExistingIsbnShouldReturnAnEmptyOptional() {
        String isbn = "1234567890";
        assertTrue(categoryDao.findByBookIsbn(isbn).isEmpty(), "Optional should be empty");
    }



}