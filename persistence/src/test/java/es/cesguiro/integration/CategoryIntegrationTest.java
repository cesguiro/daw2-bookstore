package es.cesguiro.integration;


import es.cesguiro.TestConfig;
import es.cesguiro.repository.CategoryRepository;
import es.cesguiro.repository.model.CategoryEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
public class CategoryIntegrationTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Test findAllByBookIsbn method returns Optional<CategoryEntity>")
    void testFindByBookIsbn() {
        String isbn = "9780142418222";

        Optional<CategoryEntity> expected = Optional.of(
                new CategoryEntity("Ofertas", "Offers", "offers")
        );
        Optional<CategoryEntity> result = categoryRepository.findByBookIsbn(isbn);

        assertAll(
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(expected.get().nameEs(), result.get().nameEs()),
                () -> assertEquals(expected.get().nameEn(), result.get().nameEn()),
                () -> assertEquals(expected.get().slug(), result.get().slug())
        );
    }

    @Test
    @DisplayName("Test findByBookIsbn method returns Optional.empty()")
    void testByIsbnBookEmpty() {
        String isbn = "123";

        assertTrue(categoryRepository.findByBookIsbn(isbn).isEmpty());
    }
}
