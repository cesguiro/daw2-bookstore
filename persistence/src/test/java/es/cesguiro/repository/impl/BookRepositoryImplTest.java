package es.cesguiro.repository.impl;

import es.cesguiro.dao.jpa.entity.BookEntityJpa;
import es.cesguiro.dao.jpa.repository.BookRepositoryJpa;
import es.cesguiro.pagination.PagedCollection;
import es.cesguiro.repository.model.BookEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookRepositoryImplTest {

    @Mock
    BookRepositoryJpa bookRepositoryJpa;

    @InjectMocks
    BookRepositoryImpl bookRepositoryImpl;

    @Test
    @DisplayName("Test findAll method returns PagedCollection<BookEntity>")
    void testFindAll() {
        int page = 0;
        int size = 10;

        BookEntityJpa bookEntityJpa1 = new BookEntityJpa(
                1L,
                "123",
                "TitleEs 1",
                "TitleEn 1",
                "SynopsisEs 1",
                "SynopsisEn 1",
                new BigDecimal("10.0"),
                5.0,
                "cover",
                null
        );
        BookEntityJpa bookEntityJpa2 = new BookEntityJpa(
                2L,
                "456",
                "TitleEs 2",
                "TitleEn 2",
                "SynopsisEs 2",
                "SynopsisEn 2",
                new BigDecimal("20.0"),
                10.0,
                "cover",
                null
        );

        List<BookEntityJpa> bookEntities = List.of(bookEntityJpa1, bookEntityJpa2);

        Page<BookEntityJpa> pageResult = new PageImpl<>(bookEntities, PageRequest.of(page, size), bookEntities.size());

        when(bookRepositoryJpa.findAll(PageRequest.of(page, size)))
                .thenReturn(pageResult);

        PagedCollection<BookEntity> result = bookRepositoryImpl.findAll(page, size);

        assertAll(
                () -> assertNotNull(result, "PagedCollection should not be null"),
                () -> assertEquals(2, result.data().size(), "Data should contain 2 elements"),
                () -> assertEquals("123", result.data().getFirst().isbn(), "First element should have isbn 123"),
                () -> assertEquals("456", result.data().get(1).isbn(), "Second element should have isbn 456")
        );
    }

    @Test
    @DisplayName("Test findAll method returns PagedCollection<BookEntity> with empty data")
    void testFindAllEmpty() {
        int page = 0;
        int size = 10;

        List<BookEntityJpa> bookEntities = List.of();

        Page<BookEntityJpa> pageResult = new PageImpl<>(bookEntities, PageRequest.of(page, size), bookEntities.size());

        when(bookRepositoryJpa.findAll(PageRequest.of(page, size)))
                .thenReturn(pageResult);

        PagedCollection<BookEntity> result = bookRepositoryImpl.findAll(page, size);

        assertAll(
                () -> assertNotNull(result, "PagedCollection should not be null"),
                () -> assertEquals(0, result.data().size(), "Data should be empty"),
                () -> assertEquals(0, result.totalPages(), "Total pages should be 0")
        );
    }

    @Test
    @DisplayName("Test findAll method with page out of range")
    void testFindAllPageOutOfRange() {
        int page = 10; // Página fuera de rango
        int size = 2;

        Page<BookEntityJpa> pageResult = new PageImpl<>(Collections.emptyList(), PageRequest.of(page, size), 19);

        when(bookRepositoryJpa.findAll(PageRequest.of(page, size)))
                .thenReturn(pageResult);

        PagedCollection<BookEntity> result = bookRepositoryImpl.findAll(page, size);

        assertAll(
                () -> assertNotNull(result, "PagedCollection should not be null"),
                () -> assertTrue(result.data().isEmpty(), "Data should be empty"),
                () -> assertEquals(10, result.totalPages(), "Total pages should be 10")
        );
    }

    @Test
    @DisplayName("Test findAll method with a full page")
    void testFindAllFullPage() {
        int page = 0;
        int size = 10;

        List<BookEntityJpa> bookEntities = List.of(
                new BookEntityJpa(1L, "123", "TitleEs 1", "TitleEn 1", "SynopsisEs 1", "SynopsisEn 1", new BigDecimal("10.0"), 5.0, "cover", null),
                new BookEntityJpa(2L, "456", "TitleEs 2", "TitleEn 2", "SynopsisEs 2", "SynopsisEn 2", new BigDecimal("20.0"), 10.0, "cover", null),
                new BookEntityJpa(3L, "789", "TitleEs 3", "TitleEn 3", "SynopsisEs 3", "SynopsisEn 3", new BigDecimal("30.0"), 15.0, "cover", null),
                new BookEntityJpa(4L, "101", "TitleEs 4", "TitleEn 4", "SynopsisEs 4", "SynopsisEn 4", new BigDecimal("40.0"), 20.0, "cover", null),
                new BookEntityJpa(5L, "112", "TitleEs 5", "TitleEn 5", "SynopsisEs 5", "SynopsisEn 5", new BigDecimal("50.0"), 25.0, "cover", null),
                new BookEntityJpa(6L, "131", "TitleEs 6", "TitleEn 6", "SynopsisEs 6", "SynopsisEn 6", new BigDecimal("60.0"), 30.0, "cover", null),
                new BookEntityJpa(7L, "141", "TitleEs 7", "TitleEn 7", "SynopsisEs 7", "SynopsisEn 7", new BigDecimal("70.0"), 35.0, "cover", null),
                new BookEntityJpa(8L, "151", "TitleEs 8", "TitleEn 8", "SynopsisEs 8", "SynopsisEn 8", new BigDecimal("80.0"), 40.0, "cover", null),
                new BookEntityJpa(9L, "161", "TitleEs 9", "TitleEn 9", "SynopsisEs 9", "SynopsisEn 9", new BigDecimal("90.0"), 45.0, "cover", null),
                new BookEntityJpa(10L, "171", "TitleEs 10", "TitleEn 10", "SynopsisEs 10", "SynopsisEn 10", new BigDecimal("100.0"), 50.0, "cover", null)
        );

        Page<BookEntityJpa> pageResult = new PageImpl<>(bookEntities, PageRequest.of(page, size), bookEntities.size());

        when(bookRepositoryJpa.findAll(PageRequest.of(page, size)))
                .thenReturn(pageResult);

        PagedCollection<BookEntity> result = bookRepositoryImpl.findAll(page, size);

        assertAll(
                () -> assertNotNull(result, "PagedCollection should not be null"),
                () -> assertEquals(10, result.data().size(), "Data should contain 10 elements"),
                () -> assertEquals(1, result.totalPages(), "Total pages should be 1")
        );
    }

    @Test
    @DisplayName("Test findAll method with total pages greater than 1")
    void testFindAllMultiplePages() {
        int page = 1;
        int size = 5;

        List<BookEntityJpa> bookEntities = List.of(
                new BookEntityJpa(1L, "123", "TitleEs 1", "TitleEn 1", "SynopsisEs 1", "SynopsisEn 1", new BigDecimal("10.0"), 5.0, "cover", null),
                new BookEntityJpa(2L, "456", "TitleEs 2", "TitleEn 2", "SynopsisEs 2", "SynopsisEn 2", new BigDecimal("20.0"), 10.0, "cover", null),
                new BookEntityJpa(3L, "789", "TitleEs 3", "TitleEn 3", "SynopsisEs 3", "SynopsisEn 3", new BigDecimal("30.0"), 15.0, "cover", null),
                new BookEntityJpa(4L, "101", "TitleEs 4", "TitleEn 4", "SynopsisEs 4", "SynopsisEn 4", new BigDecimal("40.0"), 20.0, "cover", null),
                new BookEntityJpa(5L, "112", "TitleEs 5", "TitleEn 5", "SynopsisEs 5", "SynopsisEn 5", new BigDecimal("50.0"), 25.0, "cover", null)
        );

        Page<BookEntityJpa> pageResult = new PageImpl<>(bookEntities, PageRequest.of(page, size), 10);

        when(bookRepositoryJpa.findAll(PageRequest.of(page, size)))
                .thenReturn(pageResult);

        PagedCollection<BookEntity> result = bookRepositoryImpl.findAll(page, size);

        assertAll(
                () -> assertNotNull(result, "PagedCollection should not be null"),
                () -> assertEquals(5, result.data().size(), "Data should contain 5 elements"),
                () -> assertEquals(2, result.totalPages(), "Total pages should be 2"),
                () -> assertEquals(10, result.totalElements(), "Total elements should be 10")
        );

    }

    @Test
    @DisplayName("Test findAll method with last page having fewer elements")
    void testFindAllLastPageFewerElements() {
        int page = 1;
        int size = 5;

        List<BookEntityJpa> bookEntities = List.of(
                new BookEntityJpa(1L, "123", "TitleEs 1", "TitleEn 1", "SynopsisEs 1", "SynopsisEn 1", new BigDecimal("10.0"), 5.0, "cover", null),
                new BookEntityJpa(2L, "456", "TitleEs 2", "TitleEn 2", "SynopsisEs 2", "SynopsisEn 2", new BigDecimal("20.0"), 10.0, "cover", null),
                new BookEntityJpa(3L, "789", "TitleEs 3", "TitleEn 3", "SynopsisEs 3", "SynopsisEn 3", new BigDecimal("30.0"), 15.0, "cover", null),
                new BookEntityJpa(4L, "101", "TitleEs 4", "TitleEn 4", "SynopsisEs 4", "SynopsisEn 4", new BigDecimal("40.0"), 20.0, "cover", null)
        );

        Page<BookEntityJpa> pageResult = new PageImpl<>(bookEntities, PageRequest.of(page, size), 9);

        when(bookRepositoryJpa.findAll(PageRequest.of(page, size)))
                .thenReturn(pageResult);

        PagedCollection<BookEntity> result = bookRepositoryImpl.findAll(page, size);

        assertAll(
                () -> assertNotNull(result, "PagedCollection should not be null"),
                () -> assertEquals(4, result.data().size(), "Data should contain 4 elements"),
                () -> assertEquals(2, result.totalPages(), "Total pages should be 2"),
                () -> assertEquals(9, result.totalElements(), "Total elements should be 9")
        );
    }


    @Test
    @DisplayName("Test findByIsbn method returns Optional<BookEntity>")
    void testFindByIsbn() {
        String isbn = "123";

        BookEntityJpa bookEntityJpa = new BookEntityJpa(
                1L,
                isbn,
                "TitleEs 1",
                "TitleEn 1",
                "SynopsisEs 1",
                "SynopsisEn 1",
                new BigDecimal("10.0"),
                5.0,
                "cover",
                null
        );

        when(bookRepositoryJpa.findByIsbn(isbn))
                .thenReturn(java.util.Optional.of(bookEntityJpa));

        var result = bookRepositoryImpl.findByIsbn(isbn);

        assertAll(
                () -> assertTrue(result.isPresent(), "Optional should not be empty"),
                () -> assertEquals(isbn, result.get().isbn(), "ISBN should be 123")
        );
    }

    @Test
    @DisplayName("Test findByIsbn method returns empty Optional<BookEntity>")
    void testFindByIsbnEmpty() {
        String isbn = "123";

        when(bookRepositoryJpa.findByIsbn(isbn))
                .thenReturn(java.util.Optional.empty());

        var result = bookRepositoryImpl.findByIsbn(isbn);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test findByIsbn method with null isbn")
    void testFindByIsbnNull() {
        String isbn = null;

        var result = bookRepositoryImpl.findByIsbn(isbn);

        assertTrue(result.isEmpty());
    }




}