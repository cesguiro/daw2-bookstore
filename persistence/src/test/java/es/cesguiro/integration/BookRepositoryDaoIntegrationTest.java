package es.cesguiro.integration;

import es.cesguiro.exception.PagedCollectionException;
import es.cesguiro.pagination.PagedCollection;
import es.cesguiro.repository.impl.BookRepositoryImpl;
import es.cesguiro.repository.model.BookEntity;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AppConfig.class)
@Transactional
public class BookRepositoryDaoIntegrationTest {

    @Autowired
    private BookRepositoryImpl bookRepositoryImpl;

    @BeforeAll
    public static void setUp() {
        // Configuración de Flyway
        Flyway flyway = Flyway.configure().dataSource(
                "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MYSQL",
                "root",
                "root"
        ).load();

        // Ejecución de migraciones
        flyway.migrate();
    }

    @Test
    @DisplayName("Test find all books")
    public void testFindAllBooks() {
        PagedCollection<BookEntity> bookEntityPagedCollection = bookRepositoryImpl.findAll(0, 10);

        assertAll(
                () -> assertEquals("9780142424179", bookEntityPagedCollection.data().getFirst().isbn(), "ISBN should match"),
                () -> assertEquals(0, bookEntityPagedCollection.pageNumber(), "Page number should match"),
                () -> assertEquals(10, bookEntityPagedCollection.pageSize(), "Page size should match"),
                () -> assertEquals(3, bookEntityPagedCollection.totalPages(), "Total pages should match"),
                () -> assertEquals(24, bookEntityPagedCollection.totalElements(), "Total elements should match"),
                () -> assertEquals(10, bookEntityPagedCollection.data().size(), "Data size should match"),
                () -> assertEquals(3, bookEntityPagedCollection.totalPages(), "Total pages should match")
        );
    }

    @Test
    @DisplayName("Test find all books with page number")
    public void testFindAllBooksWithPageNumber() {
        PagedCollection<BookEntity> bookEntityPagedCollection = bookRepositoryImpl.findAll(1, 10);

        assertAll(
                () -> assertEquals("9780316015844", bookEntityPagedCollection.data().getFirst().isbn(), "ISBN should match"),
                () -> assertEquals(1, bookEntityPagedCollection.pageNumber(), "Page number should match"),
                () -> assertEquals(10, bookEntityPagedCollection.pageSize(), "Page size should match"),
                () -> assertEquals(3, bookEntityPagedCollection.totalPages(), "Total pages should match"),
                () -> assertEquals(24, bookEntityPagedCollection.totalElements(), "Total elements should match"),
                () -> assertEquals(10, bookEntityPagedCollection.data().size(), "Data size should match"),
                () -> assertEquals(3, bookEntityPagedCollection.totalPages(), "Total pages should match")
        );
    }

    @Test
    @DisplayName("Test find all books with page number out of bounds")
    public void testFindAllBooksWithPageNumberOutOfBounds() {
        PagedCollection<BookEntity> bookEntityPagedCollection = bookRepositoryImpl.findAll(100, 10);

        assertAll(
                () -> assertTrue(bookEntityPagedCollection.data().isEmpty(), "Data should be empty"),
                () -> assertEquals(0, bookEntityPagedCollection.data().size(), "Data size should match"),
                () -> assertEquals(100, bookEntityPagedCollection.pageNumber(), "Page number should match"),
                () -> assertEquals(10, bookEntityPagedCollection.pageSize(), "Page size should match"),
                () -> assertEquals(3, bookEntityPagedCollection.totalPages(), "Total pages should match"),
                () -> assertEquals(24, bookEntityPagedCollection.totalElements(), "Total elements should match")
        );
    }

    @Test
    @DisplayName("Test find book by ISBN")
    public void testFindBookByIsbn() {
        Optional<BookEntity> bookEntityOptional = bookRepositoryImpl.findByIsbn("9780142424179");

        assertAll(
                () -> assertTrue(bookEntityOptional.isPresent(), "Book should be present"),
                () -> assertEquals("9780142424179", bookEntityOptional.get().isbn(), "ISBN should match")
        );
    }

    @Test
    @DisplayName("Test find book by ISBN not found")
    public void testFindBookByIsbnNotFound() {
        Optional<BookEntity> bookEntityOptional = bookRepositoryImpl.findByIsbn("00000000");

        assertAll(
                () -> assertTrue(bookEntityOptional.isEmpty(), "Book should not be present")
        );
    }

}
