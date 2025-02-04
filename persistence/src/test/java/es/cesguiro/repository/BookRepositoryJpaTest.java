package es.cesguiro.repository;

import es.cesguiro.dao.BookDao;
import es.cesguiro.dao.jpa.entity.BookEntityJpa;
import es.cesguiro.pagination.Page;
import es.cesguiro.repository.data.BookData;
import es.cesguiro.repository.model.BookEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookRepositoryJpaTest {

    @Mock
    BookDao bookDao;

    @InjectMocks
    BookRepositoryJpa bookRepositoryJpa;

    @Test
    @DisplayName("Test findAll method returns Page<BookEntity>")
    void testFindAll() {
        int page = 1;
        int size = 10;

        List<BookEntity> bookEntities = List.of(BookData.getBookEntity(0), BookData.getBookEntity(1));

        when(bookDao.findAll(page, size)).thenReturn(bookEntities);
        when(bookDao.count()).thenReturn(2L);

        Page<BookEntity> expected = new Page<>(bookEntities, page, size, 2);
        Page<BookEntity> result = bookRepositoryJpa.findAll(page, size);

        assertAll(
                () -> assertNotNull(result, "Page should not be null"),
                () -> assertEquals(expected.data().size(), result.data().size(), "Data should contain 2 elements"),
                () -> assertEquals(expected.pageNumber(), result.pageNumber(), "Page number should be 1"),
                () -> assertEquals(expected.pageSize(), result.pageSize(), "Page size should be 10"),
                () -> assertEquals(expected.totalElements(), result.totalElements(), "Total elements should be 2"),
                () -> assertEquals(expected.data().getFirst().isbn(), result.data().getFirst().isbn(), "First element should have isbn 123"),
                () -> assertEquals(expected.data().getFirst().titleEs(), result.data().getFirst().titleEs(), "First element should have title TitleEs 1"),
                () -> assertEquals(expected.data().getLast().isbn(), result.data().getLast().isbn(), "Last element should have isbn 456"),
                () -> assertEquals(expected.data().getLast().titleEs(), result.data().getLast().titleEs(), "Last element should have title TitleEs 2")
        );
    }

    @Test
    @DisplayName("Test findAll method returns Page<BookEntity> with empty data")
    void testFindAllEmpty() {
        int page = 1;
        int size = 10;

        when(bookDao.findAll(page, size)).thenReturn(Collections.emptyList());
        when(bookDao.count()).thenReturn(0L);

        Page<BookEntity> result = bookRepositoryJpa.findAll(page, size);

        assertAll(
                () -> assertNotNull(result, "Page should not be null"),
                () -> assertEquals(0, result.data().size(), "Data should contain 0 elements"),
                () -> assertEquals(page, result.pageNumber(), "Page number should be 1"),
                () -> assertEquals(size, result.pageSize(), "Page size should be 10"),
                () -> assertEquals(0, result.totalElements(), "Total elements should be 0")
        );
    }

    @Test
    @DisplayName("Test findAll method with page out of range")
    void testFindAllPageOutOfRange() {
        int page = 2;
        int size = 10;

        when(bookDao.findAll(page, size)).thenReturn(Collections.emptyList());
        when(bookDao.count()).thenReturn(0L);

        Page<BookEntity> result = bookRepositoryJpa.findAll(page, size);

        assertAll(
                () -> assertNotNull(result, "Page should not be null"),
                () -> assertEquals(0, result.data().size(), "Data should contain 0 elements"),
                () -> assertEquals(page, result.pageNumber(), "Page number should be 2"),
                () -> assertEquals(size, result.pageSize(), "Page size should be 10"),
                () -> assertEquals(0, result.totalElements(), "Total elements should be 0")
        );
    }

    @Test
    @DisplayName("Test findAll method with a full page")
    void testFindAllFullPage() {
        int page = 1;
        int size = 3;

        List<BookEntity> bookEntities = List.of(BookData.getBookEntity(0), BookData.getBookEntity(1), BookData.getBookEntity(2));

        when(bookDao.findAll(page, size)).thenReturn(bookEntities);
        when(bookDao.count()).thenReturn(3L);


        Page<BookEntity> expected = new Page<>(bookEntities, page, size, 3);
        Page<BookEntity> result = bookRepositoryJpa.findAll(page, size);

        assertAll(
                () -> assertNotNull(result, "Page should not be null"),
                () -> assertEquals(expected.data().size(), result.data().size(), "Data should contain 3 elements"),
                () -> assertEquals(expected.pageNumber(), result.pageNumber(), "Page number should be 1"),
                () -> assertEquals(expected.pageSize(), result.pageSize(), "Page size should be 3"),
                () -> assertEquals(expected.totalElements(), result.totalElements(), "Total elements should be 3"),
                () -> assertEquals(expected.data().getFirst().isbn(), result.data().getFirst().isbn(), "First element should have isbn 123"),
                () -> assertEquals(expected.data().getFirst().titleEs(), result.data().getFirst().titleEs(), "First element should have title TitleEs 1"),
                () -> assertEquals(expected.data().getLast().isbn(), result.data().getLast().isbn(), "Last element should have isbn 789"),
                () -> assertEquals(expected.data().getLast().titleEs(), result.data().getLast().titleEs(), "Last element should have title TitleEs 3")
        );
    }

    @Test
    @DisplayName("Test findAll method with total pages greater than 1")
    void testFindAllMultiplePages() {
        int page = 1;
        int size = 3;

        List<BookEntity> bookEntities = List.of(BookData.getBookEntity(0), BookData.getBookEntity(1), BookData.getBookEntity(2));

        when(bookDao.findAll(page, size)).thenReturn(bookEntities);
        when(bookDao.count()).thenReturn(9L);

        Page<BookEntity> expected = new Page<>(bookEntities, page, size, 9);
        Page<BookEntity> result = bookRepositoryJpa.findAll(page, size);

        assertAll(
                () -> assertNotNull(result, "Page should not be null"),
                () -> assertEquals(expected.data().size(), result.data().size(), "Data should contain 3 elements"),
                () -> assertEquals(expected.pageNumber(), result.pageNumber(), "Page number should be 1"),
                () -> assertEquals(expected.pageSize(), result.pageSize(), "Page size should be 3"),
                () -> assertEquals(expected.totalElements(), result.totalElements(), "Total elements should be 5"),
                () -> assertEquals(expected.data().getFirst().isbn(), result.data().getFirst().isbn(), "First element should have isbn 123"),
                () -> assertEquals(expected.data().getFirst().titleEs(), result.data().getFirst().titleEs(), "First element should have title TitleEs 1"),
                () -> assertEquals(expected.data().getLast().isbn(), result.data().getLast().isbn(), "Last element should have isbn 112"),
                () -> assertEquals(expected.data().getLast().titleEs(), result.data().getLast().titleEs(), "Last element should have title TitleEs 5")
        );
    }

    @Test
    @DisplayName("Test findAll method with last page having fewer elements")
    void testFindAllLastPageFewerElements() {
        int page = 2;
        int size = 3;

        List<BookEntity> bookEntities = List.of(BookData.getBookEntity(3), BookData.getBookEntity(4));

        when(bookDao.findAll(page, size)).thenReturn(bookEntities);
        when(bookDao.count()).thenReturn(5L);

        Page<BookEntity> expected = new Page<>(bookEntities, page, size, 5);
        Page<BookEntity> result = bookRepositoryJpa.findAll(page, size);

        assertAll(
                () -> assertNotNull(result, "Page should not be null"),
                () -> assertEquals(2, result.data().size(), "Data should contain 4 elements"),
                () -> assertEquals(page, result.pageNumber(), "Page number should be 2"),
                () -> assertEquals(size, result.pageSize(), "Page size should be 3"),
                () -> assertEquals(5, result.totalElements(), "Total elements should be 9"),
                () -> assertEquals(2, result.totalPages(), "Total pages should be 2")
        );
    }


    @Test
    @DisplayName("Test findByIsbn method returns Optional<BookEntity>")
    void testFindByIsbn() {
        String isbn = "123";

        BookEntity bookEntity = BookData.getBookEntity(0);
        when(bookDao.findByIsbn(isbn)).thenReturn(Optional.of(bookEntity));


        Optional<BookEntity> expected = Optional.of(BookData.getBookEntity(0));
        Optional<BookEntity> result = bookRepositoryJpa.findByIsbn(isbn);

        assertAll(
                () -> assertTrue(result.isPresent(), "Optional should not be empty"),
                () -> assertEquals(expected.get().isbn(), result.get().isbn(), "Isbn should be 123"),
                () -> assertEquals(expected.get().titleEs(), result.get().titleEs(), "Title should be TitleEs 1"),
                () -> assertEquals(expected.get().titleEn(), result.get().titleEn(), "Title should be TitleEn 1"),
                () -> assertEquals(expected.get().synopsisEs(), result.get().synopsisEs(), "Synopsis should be SynopsisEs 1"),
                () -> assertEquals(expected.get().synopsisEn(), result.get().synopsisEn(), "Synopsis should be SynopsisEn 1"),
                () -> assertEquals(expected.get().basePrice(), result.get().basePrice(), "Price should be 10.0"),
                () -> assertEquals(expected.get().discountPercentage(), result.get().discountPercentage(), "Discount should be 5.0"),
                () -> assertEquals(expected.get().cover(), result.get().cover(), "Cover should be cover 1")
        );
    }

    @Test
    @DisplayName("Test findByIsbn method returns empty Optional<BookEntity>")
    void testFindByIsbnEmpty() {
        String isbn = "123";

        when(bookRepositoryJpa.findByIsbn(isbn))
                .thenReturn(java.util.Optional.empty());

        var result = bookRepositoryJpa.findByIsbn(isbn);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test findByIsbn method with null isbn")
    void testFindByIsbnNull() {
        String isbn = null;

        var result = bookRepositoryJpa.findByIsbn(isbn);

        assertTrue(result.isEmpty());
    }

}