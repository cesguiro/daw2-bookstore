package es.cesguiro.dao.jpa;

import es.cesguiro.TestConfig;
import es.cesguiro.dao.GenreDao;
import es.cesguiro.dao.jpa.entity.GenreEntityJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
class GenreDaoJpaTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private GenreDao genreDao;

    @Test
    @DisplayName("Find all genres by book with existing ISBN should return a list with 1 genre")
    void findAllGenresByBookWithExistingIsbnShouldReturnAListWithGenres() {
        String isbn = "9780142424179";
        List<GenreEntityJpa> result = genreDao.findAllByBookIsbn(isbn);
        assertAll(
                () -> assertNotNull(result.getFirst(), "Genre should not be null"),
                () -> assertEquals("Children's", result.getFirst().getNameEn(), "Name should match")
        );
    }

    @Test
    @DisplayName("Find all genres by book with existing ISBN should return a list with more than 1 genre")
    void findAllGenresByBookWithExistingIsbnShouldReturnAListWithMoreThanOneGenre() {
        String isbn = "9780618260300";
        List<GenreEntityJpa> result = genreDao.findAllByBookIsbn(isbn);
        assertAll(
                () -> assertEquals(2, result.size(), "Size should match"),
                () -> assertEquals("Children's", result.getFirst().getNameEn(), "Name genre 1 should match"),
                () -> assertEquals("Fiction", result.getLast().getNameEn(), "Name genre 2 should match")
        );
    }

    @Test
    @DisplayName("Find all genres by book with non-existing ISBN should return an empty list")
    void findAllGenresByBookWithNonExistingIsbnShouldReturnAnEmptyList() {
        String isbn = "1234567890";
        assertTrue(genreDao.findAllByBookIsbn(isbn).isEmpty(), "List should be empty");
    }


}