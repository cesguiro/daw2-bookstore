package es.cesguiro.integration;

import es.cesguiro.TestConfig;
import es.cesguiro.repository.GenreRepository;
import es.cesguiro.repository.model.GenreEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
public class GenreIntegrationTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("Test findAllByBookIsbn method returns a list of GenreEntity")
    void testFindAllByBookIsbn() {
        String isbn = "9780142424179";
        List<GenreEntity> expected = List.of(
                new GenreEntity("Infantil", "Children's", "childrens"),
                new GenreEntity("Ficción", "Fiction", "fiction")
        );
        List<GenreEntity> result = genreRepository.findAllByBookIsbn(isbn);

        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals(expected.getFirst().nameEs(), result.getFirst().nameEs()),
                () -> assertEquals(expected.getFirst().nameEn(), result.getFirst().nameEn()),
                () -> assertEquals(expected.getFirst().slug(), result.getFirst().slug()),
                () -> assertEquals(expected.getLast().nameEs(), result.get(1).nameEs()),
                () -> assertEquals(expected.getLast().nameEn(), result.get(1).nameEn()),
                () -> assertEquals(expected.getLast().slug(), result.get(1).slug())
        );
    }

    @Test
    @DisplayName("Test findAllByBookIsbn method returns a list of single GenreEntity")
    void testFindAllByBookIsbnOneGenre() {
        String isbn = "9780060853983";

        List<GenreEntity> expected = List.of(
                new GenreEntity("Fantasía", "Fantasy", "fantasy")
        );

        List<GenreEntity> result = genreRepository.findAllByBookIsbn(isbn);

        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals(expected.getFirst().nameEs(), result.getFirst().nameEs()),
                () -> assertEquals(expected.getFirst().nameEn(), result.getFirst().nameEn()),
                () -> assertEquals(expected.getFirst().slug(), result.getFirst().slug())
        );
    }

    @Test
    @DisplayName("Test findAllByBookIsbn method returns an empty list")
    void testFindAllByBookIsbnEmpty() {
        String isbn = "123";

        List<GenreEntity> result = genreRepository.findAllByBookIsbn(isbn);

        assertEquals(0, result.size());
    }
}
