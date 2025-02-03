package es.cesguiro.integration;


import es.cesguiro.TestConfig;
import es.cesguiro.repository.PublisherRepository;
import es.cesguiro.repository.model.PublisherEntity;
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
public class PublisherIntegrationTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    @DisplayName("Test findByBookIsbn method returns Optional<PublisherEntity>")
    void testFindByBookIsbn() {
        String isbn = "9780142410363";

        Optional<PublisherEntity> expected = Optional.of(
                new PublisherEntity("Anagrama", "anagrama")
        );
        Optional<PublisherEntity> result = publisherRepository.findByBookIsbn(isbn);

        assertAll(
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(expected.get().name(), result.get().name()),
                () -> assertEquals(expected.get().slug(), result.get().slug())
        );
    }

    @Test
    @DisplayName("Test findByBookIsbn method returns Optional.empty()")
    void testByIsbnBookEmpty() {
        String isbn = "123";

        assertTrue(publisherRepository.findByBookIsbn(isbn).isEmpty());
    }
}
