package es.cesguiro.usecase.book.query.impl;

import es.cesguiro.exception.DomainException;
import es.cesguiro.exception.PagedCollectionException;
import es.cesguiro.pagination.PagedCollection;
import es.cesguiro.repository.AuthorRepository;
import es.cesguiro.repository.BookRepository;
import es.cesguiro.repository.model.AuthorEntity;
import es.cesguiro.repository.model.BookEntity;
import es.cesguiro.usecase.book.query.model.BookCollectionDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private FindAllService findAllService;

    @Test
    @DisplayName("Test execute method returns PagedCollection of BookCollectionDto")
    void executeReturnsBookCollectionDtoList() {
        // Arrange
        int page = 1;
        int size = 4;

        // Mock books
        BookEntity bookEntity1 = new BookEntity("123", "Title 1", "Synopsis 1", new BigDecimal("10.00"), 5.00, "cover1.jpg", LocalDate.of(2020, 1, 1));
        BookEntity bookEntity2 = new BookEntity("456", "Title 2", "Synopsis 2", new BigDecimal("20.00"), 10.00, "cover2.jpg", LocalDate.of(2021, 6, 15));
        BookEntity bookEntity3 = new BookEntity("789", "Title 3", "Synopsis 3", new BigDecimal("30.00"), 15.00, "cover3.jpg", LocalDate.of(2022, 12, 31));
        BookEntity bookEntity4 = new BookEntity("012", "Title 4", "Synopsis 4", new BigDecimal("40.00"), 20.00, "cover4.jpg", LocalDate.of(2023, 3, 10));
        List<BookEntity> bookEntities = List.of(bookEntity1, bookEntity2, bookEntity3, bookEntity4);
        when(bookRepository.findAll(page, size)).thenReturn(new PagedCollection<>(bookEntities, page, size, bookEntities.size()));

        // Mock authors
        AuthorEntity authorEntity1 = new AuthorEntity("Author 1", "Nationality 1", "Bio 1", 1970, null, "slug1");
        AuthorEntity authorEntity2 = new AuthorEntity("Author 2", "Nationality 2", "Bio 2", 1980, null, "slug2");
        AuthorEntity authorEntity3 = new AuthorEntity("Author 3", "Nationality 3", "Bio 3", 1990, null, "slug3");
        AuthorEntity authorEntity4 = new AuthorEntity("Author 4", "Nationality 4", "Bio 4", 2000, null, "slug4");
        AuthorEntity authorEntity5 = new AuthorEntity("Author 5", "Nationality 5", "Bio 5", 2010, null, "slug5");
        List<AuthorEntity> authorEntities1 = List.of(authorEntity1);
        List<AuthorEntity> authorEntities2 = List.of(authorEntity2);
        List<AuthorEntity> authorEntities3 = List.of(authorEntity3);
        List<AuthorEntity> authorEntities4 = List.of(authorEntity4, authorEntity5);
        when(authorRepository.findAllByBookIsbn("123")).thenReturn(authorEntities1);
        when(authorRepository.findAllByBookIsbn("456")).thenReturn(authorEntities2);
        when(authorRepository.findAllByBookIsbn("789")).thenReturn(authorEntities3);
        when(authorRepository.findAllByBookIsbn("012")).thenReturn(authorEntities4);

        // Act
        PagedCollection<BookCollectionDto> result = findAllService.execute(page, size);

        assertAll(
                () -> assertEquals(4, result.data().size(), "Result list size should match"),
                () -> assertEquals("123", result.data().get(0).isbn(), "First book ISBN should match"),
                () -> assertEquals("456", result.data().get(1).isbn(), "Second book ISBN should match"),
                () -> assertEquals("789", result.data().get(2).isbn(), "Third book ISBN should match"),
                () -> assertEquals("012", result.data().get(3).isbn(), "Fourth book ISBN should match"),
                () -> assertEquals(1, result.data().get(0).authors().size(), "First book authors size should match"),
                () -> assertEquals(1, result.data().get(1).authors().size(), "Second book authors size should match"),
                () -> assertEquals(1, result.data().get(2).authors().size(), "Third book authors size should match"),
                () -> assertEquals(2, result.data().get(3).authors().size(), "Fourth book authors size should match"),
                () -> assertEquals("Author 1", result.data().get(0).authors().get(0).name(), "First book author name should match"),
                () -> assertEquals("Author 2", result.data().get(1).authors().get(0).name(), "Second book author name should match"),
                () -> assertEquals("Author 3", result.data().get(2).authors().get(0).name(), "Third book author name should match"),
                () -> assertEquals("Author 4", result.data().get(3).authors().get(0).name(), "Fourth book first author name should match"),
                () -> assertEquals("Author 5", result.data().get(3).authors().get(1).name(), "Fourth book second author name should match")
        );


        // Verify interactions with repositories
        Mockito.verify(bookRepository).findAll(page, size);
        Mockito.verify(authorRepository).findAllByBookIsbn("123");
        Mockito.verify(authorRepository).findAllByBookIsbn("456");
    }

    @Test
    @DisplayName("Test when bookRepository returns an empty list")
    void testEmptyBookList() {
        // Arrange
        when(bookRepository.findAll(2, 10)).thenReturn(new PagedCollection<>(Collections.emptyList(), 2, 10, 10));

        // Act
        PagedCollection<BookCollectionDto> result = findAllService.execute(2, 10);

        // Assert
        assertTrue(result.data().isEmpty(), "The result should be an empty list when no books are found");
    }

    @Test
    @DisplayName("Test when authorRepository returns an empty list for all books")
    void testEmptyAuthorList() {
        // Arrange
        BookEntity bookEntity = new BookEntity("isbn1", "title", "synopsis", BigDecimal.TEN, 10.00, "cover", LocalDate.now());
        when(bookRepository.findAll(1,2)).thenReturn(new PagedCollection<>(List.of(bookEntity), 1, 2, 1));
        when(authorRepository.findAllByBookIsbn(bookEntity.isbn())).thenReturn(Collections.emptyList());

        // Act
        PagedCollection<BookCollectionDto> result = findAllService.execute(1, 2);

        // Assert
        assertFalse(result.data().isEmpty(), "The result should not be empty");
        assertTrue(result.data().getFirst().authors().isEmpty(), "The authors list should be empty for books with no authors");
    }

    @Test
    @DisplayName(("Test when some books have authors and some don't"))
    void testBooksAndAuthors() {
        // Arrange
        BookEntity bookEntity1 = new BookEntity("isbn1", "title1", "synopsis1", BigDecimal.TEN, 10.00, "cover", LocalDate.now());
        BookEntity bookEntity2 = new BookEntity("isbn2", "title2", "synopsis2", BigDecimal.TEN, 10.00, "cover", LocalDate.now());
        when(bookRepository.findAll(0, 2)).thenReturn(new PagedCollection<>(List.of(bookEntity1, bookEntity2), 0, 2, 2));
        when(authorRepository.findAllByBookIsbn(bookEntity1.isbn())).thenReturn(Collections.emptyList());
        AuthorEntity authorEntity = new AuthorEntity("authorName", "authorNationality", "biography", 1980, 2020, "slug");
        when(authorRepository.findAllByBookIsbn(bookEntity2.isbn())).thenReturn(List.of(authorEntity));

        // Act
        PagedCollection<BookCollectionDto> result = findAllService.execute(0, 2);

        // Assert
        assertEquals(2, result.data().size(), "The result should contain two books");
        assertTrue(result.data().get(0).authors().isEmpty(), "The first book should have no authors");
        assertEquals(1, result.data().get(1).authors().size(), "The second book should have one author");
        assertEquals("authorName", result.data().get(1).authors().get(0).name(), "The author's name should match");
    }


    @Test
    @DisplayName("Test when book has multiple authors")
    void testMultipleAuthors() {
        // Arrange
        BookEntity bookEntity = new BookEntity("isbn1", "title", "synopsis", BigDecimal.TEN, 10.00, "cover", LocalDate.now());
        when(bookRepository.findAll(0, 10)).thenReturn(new PagedCollection<>(List.of(bookEntity), 0, 10, 1));
        AuthorEntity authorEntity1 = new AuthorEntity("author1", "nationality1", "bio1", 1980, 2020, "slug1");
        AuthorEntity authorEntity2 = new AuthorEntity("author2", "nationality2", "bio2", 1990, 2021, "slug2");
        when(authorRepository.findAllByBookIsbn(bookEntity.isbn())).thenReturn(List.of(authorEntity1, authorEntity2));

        // Act
        PagedCollection<BookCollectionDto> result = findAllService.execute(0, 10);

        // Assert
        assertEquals(1, result.data().size(), "The result should contain one book");
        assertEquals(2, result.data().get(0).authors().size(), "The book should have two authors");
        assertEquals("author1", result.data().get(0).authors().get(0).name(), "The first author's name should match");
        assertEquals("author2", result.data().get(0).authors().get(1).name(), "The second author's name should match");
    }

    @Test
    @DisplayName("Test when bookRepository throws an exception")
    void testBookRepositoryException() {
        // Arrange
        when(bookRepository.findAll(0, 10)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> findAllService.execute(0, 10), "Expected exception to be thrown");
    }

    @Test
    @DisplayName("Test when authorRepository throws an exception")
    void testAuthorRepositoryException() {
        // Arrange
        BookEntity bookEntity = new BookEntity("isbn1", "title", "synopsis", BigDecimal.TEN, 10.00, "cover", LocalDate.now());
        when(bookRepository.findAll(0, 10)).thenReturn(new PagedCollection<>(List.of(bookEntity), 0, 10, 1));
        when(authorRepository.findAllByBookIsbn(bookEntity.isbn())).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> findAllService.execute(0, 10), "Expected exception to be thrown");
    }

    @Test
    @DisplayName("Test when books have discounts")
    void testBooksWithDiscount() {
        // Arrange
        BookEntity bookEntity1 = new BookEntity("isbn1", "title1", "synopsis1", BigDecimal.TEN, 10.00, "cover", LocalDate.now());
        BookEntity bookEntity2 = new BookEntity("isbn2", "title2", "synopsis2", BigDecimal.TEN, 20.00, "cover", LocalDate.now());
        when(bookRepository.findAll(0, 10)).thenReturn(new PagedCollection<>(List.of(bookEntity1, bookEntity2), 0, 10, 2));

        // Act
        PagedCollection<BookCollectionDto> result = findAllService.execute(0, 10);

        // Assert
        assertEquals(new BigDecimal("9.00"), result.data().getFirst().finalPrice(), "The final price should be correctly calculated with 10% discount");
        assertEquals(new BigDecimal("8.00"), result.data().get(1).finalPrice(), "The final price should be correctly calculated with 20% discount");
    }

    @Test
    @DisplayName("Test when books have incomplete information")
    void testBooksWithIncompleteInformation() {
        // Arrange
        BookEntity bookEntity = new BookEntity("isbn1", "title1", null, BigDecimal.ZERO, 10.00, null, LocalDate.now());
        when(bookRepository.findAll(0, 10)).thenReturn(new PagedCollection<>(List.of(bookEntity), 0, 10, 1));

        // Act
        PagedCollection<BookCollectionDto> result = findAllService.execute(0, 10);

        // Assert
        assertFalse(result.data().isEmpty(), "The result should not be empty");
        assertEquals(BigDecimal.ZERO, result.data().getFirst().basePrice(), "The book's basePrice should be BigDecimal.ZERO when it is null in the entity");
        assertNull(result.data().getFirst().cover(), "The book's cover should be null");
    }

    @Test
    @DisplayName("Test pagination with size = 0 should throw DomainException")
    void testEdgePaginationZeroPageSize() {
        // Act & Assert
        assertThrows(DomainException.class, () -> findAllService.execute(0, 0), "Expected exception to be thrown");
    }

    @Test
    @DisplayName("Test pagination with negative page value should throw DomainException")
    void testEdgePaginationNegativePageValue() {
        // Act & Assert
        assertThrows(DomainException.class, () -> findAllService.execute(-1, 10), "Expected exception to be thrown");
    }

    @Test
    @DisplayName("Test edge case pagination with page = Integer.MAX_VALUE and size = Integer.MAX_VALUE")
    void testEdgePaginationMaxValues() {
        // Arrange
        List<BookEntity> bookEntities = IntStream.range(0, 10)
                .mapToObj(i -> new BookEntity("isbn" + i, "title" + i, "synopsis" + i, BigDecimal.TEN, 10.00, "cover" + i, LocalDate.now()))
                .collect(Collectors.toList());
        when(bookRepository.findAll(Integer.MAX_VALUE, Integer.MAX_VALUE)).thenReturn(new PagedCollection<>(bookEntities, Integer.MAX_VALUE, Integer.MAX_VALUE, 10));

        // Act
        PagedCollection<BookCollectionDto> result = findAllService.execute(Integer.MAX_VALUE, Integer.MAX_VALUE);

        // Assert
        assertEquals(10, result.data().size(), "The result should contain 10 books");
    }



}
