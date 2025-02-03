package es.cesguiro.integration;


import es.cesguiro.TestConfig;
import es.cesguiro.pagination.Page;
import es.cesguiro.repository.BookRepository;
import es.cesguiro.repository.BookRepositoryJpa;
import es.cesguiro.repository.model.BookEntity;
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
public class BookIntegrationTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("Test find all books")
    public void testFindAllBooks() {
        Page<BookEntity> result = bookRepository.findAll(1, 10);

        assertAll(
                () -> assertEquals("9780142424179", result.data().getFirst().isbn(), "First ISBN should match"),
                () -> assertEquals("9781423103349", result.data().getLast().isbn(), "Last ISBN should match"),
                () -> assertEquals(1, result.pageNumber(), "Page number should match"),
                () -> assertEquals(10, result.pageSize(), "Page size should match"),
                () -> assertEquals(3, result.totalPages(), "Total pages should match"),
                () -> assertEquals(24, result.totalElements(), "Total elements should match"),
                () -> assertEquals(10, result.data().size(), "Data size should match"),
                () -> assertEquals(3, result.totalPages(), "Total pages should match")
        );
    }

    @Test
    @DisplayName("Test find all books with page number")
    public void testFindAllBooksWithPageNumber() {
        Page<BookEntity> result = bookRepository.findAll(2, 10);

        assertAll(
                () -> assertEquals("9780316015844", result.data().getFirst().isbn(), "First ISBN should match"),
                () -> assertEquals("9780316029186", result.data().getLast().isbn(), "Last ISBN should match"),
                () -> assertEquals(2, result.pageNumber(), "Page number should match"),
                () -> assertEquals(10, result.pageSize(), "Page size should match"),
                () -> assertEquals(3, result.totalPages(), "Total pages should match"),
                () -> assertEquals(24, result.totalElements(), "Total elements should match"),
                () -> assertEquals(10, result.data().size(), "Data size should match"),
                () -> assertEquals(3, result.totalPages(), "Total pages should match"),
                () -> assertEquals(2, result.pageNumber(), "Page number should match"),
                () -> assertEquals(10, result.pageSize(), "Page size should match"),
                () -> assertEquals(3, result.totalPages(), "Total pages should match"),
                () -> assertEquals(24, result.totalElements(), "Total elements should match"),
                () -> assertEquals(10, result.data().size(), "Data size should match"),
                () -> assertEquals(3, result.totalPages(), "Total pages should match")
        );
    }

    @Test
    @DisplayName("Test find all books with page number out of bounds")
    public void testFindAllBooksWithPageNumberOutOfBounds() {
        Page<BookEntity> result = bookRepository.findAll(100, 10);

        assertAll(
                () -> assertTrue(result.data().isEmpty(), "Data should be empty"),
                () -> assertEquals(0, result.data().size(), "Data size should match"),
                () -> assertEquals(100, result.pageNumber(), "Page number should match"),
                () -> assertEquals(10, result.pageSize(), "Page size should match"),
                () -> assertEquals(3, result.totalPages(), "Total pages should match"),
                () -> assertEquals(24, result.totalElements(), "Total elements should match")
        );
    }

    @Test
    @DisplayName("Test find book by ISBN")
    public void testFindBookByIsbn() {
        Optional<BookEntity> result = bookRepository.findByIsbn("9780142424179");

        assertAll(
                () -> assertTrue(result.isPresent(), "Book should be present"),
                () -> assertEquals("9780142424179", result.get().isbn(), "ISBN should match"),
                () -> assertEquals("El principito", result.get().titleEs(), "TitleEs should match"),
                () -> assertEquals("The Little Prince", result.get().titleEn(), "TitleEn should match")
        );
    }

    @Test
    @DisplayName("Test find book by ISBN not found")
    public void testFindBookByIsbnNotFound() {
        String isbn = "123";
        Optional<BookEntity> bookEntityOptional = bookRepository.findByIsbn(isbn);

        assertAll(
                () -> assertTrue(bookEntityOptional.isEmpty(), "Book should not be present")
        );
    }

}
