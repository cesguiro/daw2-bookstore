package es.cesguiro.integration;

import es.cesguiro.TestConfig;
import es.cesguiro.dao.jpa.entity.AuthorEntityJpa;
import es.cesguiro.repository.AuthorRepository;
import es.cesguiro.repository.data.AuthorData;
import es.cesguiro.repository.model.AuthorEntity;
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
public class AuthorIntegrationTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("Test findAllByBookIsbn method returns a list of AuthorEntity")
    void testFindAllByBookIsbn() {
        String isbn = "9780060557912";
        List<AuthorEntity> expected = List.of(
                new AuthorEntity("Terry Pratchett", "British", "Terry Pratchett fue un autor inglés de novelas de fantasía, conocido por su serie Mundodisco.", "Terry Pratchett was an English author of fantasy novels, best known for his Discworld series.", 1948, 2015, "terry-pratchett"),
                new AuthorEntity("Neil Gaiman", "British", "Neil Gaiman es un autor inglés de cuentos, novelas, cómics, novelas gráficas, teatro audio y películas. Sus obras notables incluyen la serie de cómics The Sandman y las novelas Stardust, American Gods, y Good Omens.", "Neil Gaiman is an English author of short fiction, novels, comic books, graphic novels, audio theatre, and films. His notable works include the comic book series The Sandman and novels Stardust, American Gods, and Good Omens.", 1960, null, "neil-gaiman")
        );
        List<AuthorEntity> result = authorRepository.findAllByBookIsbn(isbn);

        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals(expected.getFirst().name(), result.getFirst().name()),
                () -> assertEquals(expected.getFirst().biographyEn(), result.getFirst().biographyEn()),
                () -> assertEquals(expected.getFirst().biographyEs(), result.getFirst().biographyEs()),
                () -> assertEquals(expected.getFirst().slug(), result.getFirst().slug()),
                () -> assertEquals(expected.getLast().name(), result.get(1).name()),
                () -> assertEquals(expected.getLast().biographyEn(), result.get(1).biographyEn()),
                () -> assertEquals(expected.getLast().biographyEs(), result.get(1).biographyEs()),
                () -> assertEquals(expected.getLast().slug(), result.get(1).slug())
        );
    }

    @Test
    @DisplayName("Test findAllByBookIsbn method returns a list of single AuthorEntity")
    void testFindAllByBookIsbnOneAuthor() {
        String isbn = "9780060853983";

        List<AuthorEntity> expected = List.of(
                new AuthorEntity("Neil Gaiman", "British", "Neil Gaiman es un autor inglés de cuentos, novelas, cómics, novelas gráficas, teatro audio y películas. Sus obras notables incluyen la serie de cómics The Sandman y las novelas Stardust, American Gods, y Good Omens.", "Neil Gaiman is an English author of short fiction, novels, comic books, graphic novels, audio theatre, and films. His notable works include the comic book series The Sandman and novels Stardust, American Gods, and Good Omens.", 1960, null, "neil-gaiman")
        );
        List<AuthorEntity> result = authorRepository.findAllByBookIsbn(isbn);

        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals(expected.getFirst().name(), result.getFirst().name()),
                () -> assertEquals(expected.getFirst().biographyEn(), result.getFirst().biographyEn()),
                () -> assertEquals(expected.getFirst().biographyEs(), result.getFirst().biographyEs()),
                () -> assertEquals(expected.getFirst().slug(), result.getFirst().slug())
        );
    }

    @Test
    @DisplayName("Test findAllByBookIsbn method with non existing isbn returns a empty list of AuthorEntity")
    void testFindAll() {
        String isbn = "123";

        List<AuthorEntity> result = authorRepository.findAllByBookIsbn(isbn);
        assertEquals(0, result.size());
    }

}
