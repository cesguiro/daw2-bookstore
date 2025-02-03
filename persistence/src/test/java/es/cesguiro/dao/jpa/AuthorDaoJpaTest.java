package es.cesguiro.dao.jpa;

import es.cesguiro.TestConfig;
import es.cesguiro.dao.AuthorDao;
import es.cesguiro.dao.jpa.entity.AuthorEntityJpa;
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
class AuthorDaoJpaTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AuthorDao authorDao;

    @Test
    @DisplayName("Find all authors by book with existing ISBN should return a list with 1 author")
    void findAllAuthorsByBookWithExistingIsbnShouldReturnAListWithAuthors() {
        String isbn = "9780142424179";
        List<AuthorEntityJpa> result = authorDao.findAllByBookIsbn(isbn);
        assertAll(
                () -> assertNotNull(result.getFirst(), "Author should not be null"),
                () -> assertEquals("Antoine de Saint-Exupéry", result.getFirst().getName(), "Name should match")
        );
    }

    @Test
    @DisplayName("Find all authors by book with existing ISBN should return a list with more than 1 author")
    void findAllAuthorsByBookWithExistingIsbnShouldReturnAListWithMoreThanOneAuthor() {
        String isbn = "9780060557912";
        List<AuthorEntityJpa> result = authorDao.findAllByBookIsbn(isbn);
        assertAll(
                () -> assertEquals(2, result.size(), "Size should match"),
                () -> assertEquals("Terry Pratchett", result.getFirst().getName(), "Name author 1 should match"),
                () -> assertEquals("Neil Gaiman", result.getLast().getName(), "Name author 2 should match")
        );
    }

    @Test
    @DisplayName("Find all authors by book with non-existing ISBN should return an empty list")
    void findAllAuthorsByBookWithNonExistingIsbnShouldReturnAnEmptyList() {
        String isbn = "1234567890";
        assertTrue(authorDao.findAllByBookIsbn(isbn).isEmpty(), "List should be empty");
    }

}