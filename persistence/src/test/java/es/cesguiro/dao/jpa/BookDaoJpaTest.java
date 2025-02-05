package es.cesguiro.dao.jpa;

import es.cesguiro.TestConfig;
import es.cesguiro.dao.BookDao;
import es.cesguiro.dao.jpa.entity.BookEntityJpa;
import es.cesguiro.repository.model.BookEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
class BookDaoJpaTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BookDao bookDao;


    @Test
    @DisplayName("Find all books with page 1 and size 10")
    void findAllBooksWithPage1AndSize10() {
        int page = 1;
        int size = 10;
        List<BookEntity> result = bookDao.findAll(page, size);
        assertAll(
                () -> assertEquals(10, result.size(), "Size should match"),
                () -> assertEquals("9780142424179", result.getFirst().isbn(), "ISBN should match"),
                () -> assertEquals("9781423103349", result.getLast().isbn(), "ISBN should match")
        );
    }

    @Test
    @DisplayName("Find all books with page 2 and size 10")
    void findAllBooksWithPage2AndSize10() {
        int page = 2;
        int size = 10;
        List<BookEntity> result = bookDao.findAll(page, size);
        assertAll(
                () -> assertEquals(10, result.size(), "Size should match"),
                () -> assertEquals("9780316015844", result.getFirst().isbn(), "ISBN should match"),
                () -> assertEquals("9780316029186", result.getLast().isbn(), "ISBN should match")
        );
    }

    @Test
    @DisplayName("Find all books with page 3 and size 10")
    void findAllBooksWithPage3AndSize10() {
        int page = 3;
        int size = 10;
        List<BookEntity> result = bookDao.findAll(page, size);
        assertAll(
                () -> assertEquals(4, result.size(), "Size should match"),
                () -> assertEquals("9780316073797", result.getFirst().isbn(), "ISBN should match"),
                () -> assertEquals("9780060557812", result.getLast().isbn(), "ISBN should match")
        );
    }

    @Test
    @DisplayName("Find all books with page 4 and size 10 should return an empty list")
    void findAllBooksWithPage4AndSize10ShouldReturnAnEmptyList() {
        int page = 4;
        int size = 10;
        List<BookEntity> result = bookDao.findAll(page, size);
        assertTrue(result.isEmpty(), "List should be empty");
    }

    @Test
    @DisplayName("FindByIsbn with existing isbn should return a book")
    void findByIsbnShouldReturnABook() {
        String isbn = "9780618260300";
        Optional<BookEntity> result = bookDao.findByIsbn(isbn);
        assertAll(
                () -> assertFalse(result.isEmpty(), "Book should not be null"),
                () -> assertEquals(isbn, result.get().isbn(), "ISBN should match"),
                () -> assertEquals("The Lion, the Witch and the Wardrobe", result.get().titleEn(), "Title should match")
        );
    }

    @Test
    @DisplayName("FindByIsbn with non existing isbn should return an empty optional")
    void findByIsbnShouldReturnAnEmptyOptional() {
        String isbn = "111111111";
        Optional<BookEntity> result = bookDao.findByIsbn(isbn);
        assertAll(
                () -> assertTrue(result.isEmpty(), "Optional should be empty")
        );
    }

    @Test
    @DisplayName("Count all books should return the total number of books")
    void countAllBooksShouldReturnTheTotalNumberOfBooks() {
        long result = bookDao.count();
        assertEquals(24, result, "Count should match");
    }


    /********** CriteriaBuilder version **********/

    @Test
    @DisplayName("Find all books with page 1 and size 10 with CriteriaBuilder")
    void findAllBooksWithPage1AndSize10WithCriteriaBuilder() {
        int page = 1;
        int size = 10;
        List<BookEntity> result = bookDao.findAllCB(page, size);
        assertAll(
                () -> assertEquals(10, result.size(), "Size should match"),
                () -> assertEquals("9780142424179", result.getFirst().isbn(), "ISBN should match"),
                () -> assertEquals("9781423103349", result.getLast().isbn(), "ISBN should match")
        );
    }

    @Test
    @DisplayName("Find book by isbn with CriteriaBuilder")
    void findBookByIsbnWithCriteriaBuilder() {
        String isbn = "9780618260300";
        Optional<BookEntity> result = bookDao.findByIsbnCB(isbn);
        assertAll(
                () -> assertFalse(result.isEmpty(), "Book should not be null"),
                () -> assertEquals(isbn, result.get().isbn(), "ISBN should match"),
                () -> assertEquals("The Lion, the Witch and the Wardrobe", result.get().titleEn(), "Title should match")
        );
    }

    @Test
    @DisplayName("Count all books with CriteriaBuilder should return the total number of books")
    void countAllBooksWithCriteriaBuilderShouldReturnTheTotalNumberOfBooks() {
        long result = bookDao.countCB();
        assertEquals(24, result, "Count should match");
    }


}


