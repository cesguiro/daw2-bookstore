package es.cesguiro.usecase.book.query.impl;

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
    @DisplayName("Test execute method returns list of BookCollectionDto")
    void executeReturnsBookCollectionDtoList() {
        // Arrange
        int page = 0;
        int size = 2;

        // Mock books
        BookEntity bookEntity1 = new BookEntity("123", "Title 1", "Synopsis 1", new BigDecimal("10.00"), 5.00, "cover1.jpg", LocalDate.of(2020, 1, 1));
        BookEntity bookEntity2 = new BookEntity("456", "Title 2", "Synopsis 2", new BigDecimal("20.00"), 10.00, "cover2.jpg", LocalDate.of(2021, 6, 15));
        List<BookEntity> bookEntities = List.of(bookEntity1, bookEntity2);
        when(bookRepository.findAll(page, size)).thenReturn(bookEntities);

        // Mock authors
        AuthorEntity authorEntity1 = new AuthorEntity("Author 1", "Nationality 1", "Bio 1", 1970, null, "slug1");
        AuthorEntity authorEntity2 = new AuthorEntity("Author 2", "Nationality 2", "Bio 2", 1980, null, "slug2");
        List<AuthorEntity> authorEntities1 = List.of(authorEntity1);
        List<AuthorEntity> authorEntities2 = List.of(authorEntity2);
        when(authorRepository.findAllByBookIsbn("123")).thenReturn(authorEntities1);
        when(authorRepository.findAllByBookIsbn("456")).thenReturn(authorEntities2);

        // Act
        List<BookCollectionDto> result = findAllService.execute(page, size);

        // Assert
        assertAll(
                () -> assertEquals(2, result.size(), "Result list size should match"),
                () -> assertEquals("123", result.getFirst().isbn(), "First book ISBN should match"),
                () -> assertEquals("456", result.get(1).isbn(), "Second book ISBN should match"),
                () -> assertEquals(1, result.getFirst().authors().size(), "First book authors size should match"),
                () -> assertEquals(1, result.get(1).authors().size(), "Second book authors size should match"),
                () -> assertEquals("Author 1", result.getFirst().authors().getFirst().name(), "First book author name should match"),
                () -> assertEquals("Author 2", result.get(1).authors().getFirst().name(), "Second book author name should match")
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
        when(bookRepository.findAll(0, 10)).thenReturn(Collections.emptyList());

        // Act
        List<BookCollectionDto> result = findAllService.execute(0, 10);

        // Assert
        assertTrue(result.isEmpty(), "The result should be an empty list when no books are found");
    }

    @Test
    @DisplayName("Test when authorRepository returns an empty list for all books")
    void testEmptyAuthorList() {
        // Arrange
        BookEntity bookEntity = new BookEntity("isbn1", "title", "synopsis", BigDecimal.TEN, 10.00, "cover", LocalDate.now());
        when(bookRepository.findAll(0, 10)).thenReturn(List.of(bookEntity)); // Cambiar a BookEntity
        when(authorRepository.findAllByBookIsbn(bookEntity.isbn())).thenReturn(Collections.emptyList());

        // Act
        List<BookCollectionDto> result = findAllService.execute(0, 10);

        // Assert
        assertFalse(result.isEmpty(), "The result should not be empty");
        assertTrue(result.get(0).authors().isEmpty(), "The authors list should be empty for books with no authors");
    }

    @Test
    @DisplayName("Test when both bookRepository and authorRepository return valid data")
    void testValidData() {
        // Arrange
        AuthorEntity authorEntity = new AuthorEntity("authorName", "authorNationality", "biography", 1980, 2020, "slug");
        BookEntity bookEntity = new BookEntity("isbn1", "title", "synopsis", BigDecimal.TEN, 10.00, "cover", LocalDate.now());
        when(bookRepository.findAll(0, 10)).thenReturn(List.of(bookEntity));
        when(authorRepository.findAllByBookIsbn(bookEntity.isbn())).thenReturn(List.of(authorEntity));

        // Act
        List<BookCollectionDto> result = findAllService.execute(0, 10);

        // Assert
        assertFalse(result.isEmpty(), "The result should not be empty");
        assertEquals(1, result.size(), "The result should contain one book");
        assertEquals(1, result.get(0).authors().size(), "The book should have one author");
        assertEquals(authorEntity.slug(), result.get(0).authors().get(0).slug(), "The author's slug should match");
    }

    @Test
    @DisplayName("Test pagination with different page and size values")
    void testPagination() {
        // Arrange
        BookEntity bookEntity1 = new BookEntity("isbn1", "title1", "synopsis1", BigDecimal.TEN, 10.00, "cover", LocalDate.now());
        BookEntity bookEntity2 = new BookEntity("isbn2", "title2", "synopsis2", BigDecimal.TEN, 10.00, "cover", LocalDate.now());
        when(bookRepository.findAll(0, 2)).thenReturn(List.of(bookEntity1, bookEntity2));

        // Act
        List<BookCollectionDto> result = findAllService.execute(0, 2);

        // Assert
        assertEquals(2, result.size(), "The result should contain two books for the first page with size 2");
        assertEquals(bookEntity1.isbn(), result.get(0).isbn(), "The first book's ISBN should match");
        assertEquals(bookEntity2.isbn(), result.get(1).isbn(), "The second book's ISBN should match");
    }

    @Test
    @DisplayName("Test when books have no authors associated")
    void testBooksWithoutAuthors() {
        // Arrange
        BookEntity bookEntity = new BookEntity("isbn1", "title", "synopsis", BigDecimal.TEN, 10.00, "cover", LocalDate.now());
        when(bookRepository.findAll(0, 10)).thenReturn(List.of(bookEntity));
        when(authorRepository.findAllByBookIsbn(bookEntity.isbn())).thenReturn(Collections.emptyList());

        // Act
        List<BookCollectionDto> result = findAllService.execute(0, 10);

        // Assert
        assertFalse(result.isEmpty(), "The result should not be empty");
        assertTrue(result.get(0).authors().isEmpty(), "Books with no authors should have an empty authors list");
    }

    @Test
    @DisplayName("Test when book has multiple authors")
    void testMultipleAuthors() {
        // Arrange
        AuthorEntity author1 = new AuthorEntity("authorName1", "authorNationality", "biography", 1980, 2020, "slug1");
        AuthorEntity author2 = new AuthorEntity("authorName2", "authorNationality", "biography", 1980, 2020, "slug2");
        BookEntity bookEntity = new BookEntity("isbn1", "title", "synopsis", BigDecimal.TEN, 10.00, "cover", LocalDate.now());
        when(bookRepository.findAll(0, 10)).thenReturn(List.of(bookEntity));
        when(authorRepository.findAllByBookIsbn(bookEntity.isbn())).thenReturn(List.of(author1, author2));

        // Act
        List<BookCollectionDto> result = findAllService.execute(0, 10);

        // Assert
        assertFalse(result.isEmpty(), "The result should not be empty");
        assertEquals(2, result.get(0).authors().size(), "The book should have two authors");
        assertEquals("slug1", result.get(0).authors().get(0).slug(), "The first author's slug should match");
        assertEquals("slug2", result.get(0).authors().get(1).slug(), "The second author's slug should match");
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
        when(bookRepository.findAll(0, 10)).thenReturn(List.of(bookEntity));
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
        when(bookRepository.findAll(0, 10)).thenReturn(List.of(bookEntity1, bookEntity2));

        // Act
        List<BookCollectionDto> result = findAllService.execute(0, 10);

        // Assert
        assertEquals(new BigDecimal("9.00"), result.get(0).finalPrice(), "The final price should be correctly calculated with 10% discount");
        assertEquals(new BigDecimal("8.00"), result.get(1).finalPrice(), "The final price should be correctly calculated with 20% discount");
    }

    @Test
    @DisplayName("Test when books have incomplete information")
    void testBooksWithIncompleteInformation() {
        // Arrange
        BookEntity bookEntity = new BookEntity("isbn1", "title1", null, BigDecimal.ZERO, 10.00, null, LocalDate.now());
        when(bookRepository.findAll(0, 10)).thenReturn(List.of(bookEntity));

        // Act
        List<BookCollectionDto> result = findAllService.execute(0, 10);

        // Assert
        assertFalse(result.isEmpty(), "The result should not be empty");
        assertEquals(BigDecimal.ZERO, result.get(0).basePrice(), "The book's basePrice should be BigDecimal.ZERO when it is null in the entity");
        assertNull(result.getFirst().cover(), "The book's cover should be null");
    }

    @Test
    @DisplayName("Test edge case pagination with page = 0 and size = 0")
    void testEdgePaginationZeroPageSize() {
        // Arrange
        BookEntity bookEntity = new BookEntity("isbn1", "title", "synopsis", BigDecimal.TEN, 10.00, "cover", LocalDate.now());
        // Simulamos que el repositorio devuelve una lista vacía cuando page = 0 y size = 0
        when(bookRepository.findAll(0, 0)).thenReturn(Collections.emptyList());

        // Act
        List<BookCollectionDto> result = findAllService.execute(0, 0);

        // Assert
        assertTrue(result.isEmpty(), "The result should be empty when page = 0 and size = 0");
    }

    @Test
    @DisplayName("Test edge case pagination with page = Integer.MAX_VALUE and size = Integer.MAX_VALUE")
    void testEdgePaginationMaxValues() {
        // Arrange
        BookEntity bookEntity = new BookEntity("isbn1", "title", "synopsis", BigDecimal.TEN, 10.00, "cover", LocalDate.now());
        when(bookRepository.findAll(Integer.MAX_VALUE, Integer.MAX_VALUE)).thenReturn(List.of(bookEntity));

        // Act
        List<BookCollectionDto> result = findAllService.execute(Integer.MAX_VALUE, Integer.MAX_VALUE);

        // Assert
        assertEquals(1, result.size(), "The result should contain one book with max page and size");
    }

    @Test
    @DisplayName("Test negative page value in pagination")
    void testNegativePageValue() {
        // Arrange
        BookEntity bookEntity = new BookEntity("isbn1", "title", "synopsis", BigDecimal.TEN, 10.00, "cover", LocalDate.now());
        when(bookRepository.findAll(-1, 10)).thenReturn(List.of());

        // Act
        List<BookCollectionDto> result = findAllService.execute(-1, 10);

        // Assert
        assertTrue(result.isEmpty(), "The result should be empty when page is negative");
    }

    @Test
    @DisplayName("Test large lists performance")
    void testLargeListPerformance() {
        // Arrange
        List<BookEntity> largeBookList = IntStream.range(0, 1000)
                .mapToObj(i -> new BookEntity("isbn" + i, "title" + i, "synopsis", BigDecimal.TEN, 10.00, "cover", LocalDate.now()))
                .collect(Collectors.toList());
        when(bookRepository.findAll(0, 1000)).thenReturn(largeBookList);

        // Act
        List<BookCollectionDto> result = findAllService.execute(0, 1000);

        // Assert
        assertEquals(1000, result.size(), "The result should contain 1000 books");
    }


}
