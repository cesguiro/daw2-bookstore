package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.model.Author;
import es.cesguiro.model.Book;
import es.cesguiro.repository.model.BookEntity;
import es.cesguiro.usecase.book.query.model.BookCollectionQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {

    @Test
    @DisplayName("Test map BookEntity to Book")
    void toBook() {
        // Arrange
        BookEntity bookEntity = new BookEntity(
                "123456789",
                "Book Title",
                "Book Synopsis",
                new BigDecimal("29.99"),
                0.1,
                "cover.jpg",
                LocalDate.of(2023, 1, 1)
        );

        // Act
        Book book = BookMapper.toBook(bookEntity);

        // Assert
        assertAll(
                () -> assertEquals(bookEntity.isbn(), book.getIsbn(), "ISBNs should match"),
                () -> assertEquals(bookEntity.title(), book.getTitle(), "Titles should match"),
                () -> assertEquals(bookEntity.synopsis(), book.getSynopsis(), "Synopses should match"),
                () -> assertEquals(bookEntity.basePrice(), book.getBasePrice(), "Base prices should match"),
                () -> assertEquals(bookEntity.discountPercentage(), book.getDiscountPercentage(), "Discounts should match"),
                () -> assertEquals(bookEntity.cover(), book.getCover(), "Covers should match"),
                () -> assertEquals(bookEntity.publicationDate(), book.getPublicationDate(), "Publication dates should match")
        );
    }

    @Test
    @DisplayName("Test null BookEntity returns null Book")
    void toBookNull() {
        // Act
        Book book = BookMapper.toBook(null);

        // Assert
        assertNull(book, "Mapping null BookEntity should return null Book");
    }

    @Test
    @DisplayName("Test map Book to BookCollectionDto")
    void toBookCollectionDto() {
        // Arrange
        Author author1 = new Author("Author 1", "Nationality 1", "Bio 1", 1970, 2000, "slug1");
        Author author2 = new Author("Author 2", "Nationality 2", "Bio 2", 1980, null, "slug2");

        Book book = new Book(
                "123456789",
                "Book Title",
                "Book Synopsis",
                new BigDecimal("29.99"),
                0.1,
                "cover.jpg",
                LocalDate.of(2023, 1, 1)
        );
        book.setAuthors(List.of(author1, author2));

        // Act
        BookCollectionQuery dto = BookMapper.toBookCollectionDto(book);

        // Assert
        assertAll(
                () -> assertEquals(book.getIsbn(), dto.isbn(), "ISBNs should match"),
                () -> assertEquals(book.getTitle(), dto.title(), "Titles should match"),
                () -> assertEquals(book.getBasePrice(), dto.basePrice(), "Base prices should match"),
                () -> assertEquals(book.getDiscountPercentage(), dto.discount(), "Discounts should match"),
                () -> assertEquals(book.getCover(), dto.cover(), "Covers should match"),
                () -> assertEquals(book.calculateFinalPrice(), dto.finalPrice(), "Final prices should match"),
                () -> assertEquals(
                        book.getAuthors().size(),
                        dto.authors().size(),
                        "Number of authors should match"
                )
        );
    }

    @Test
    @DisplayName("Test null Book returns null BookCollectionDto")
    void toBookCollectionDtoNull() {
        // Act
        BookCollectionQuery dto = BookMapper.toBookCollectionDto(null);

        // Assert
        assertNull(dto, "Mapping null Book should return null BookCollectionDto");
    }

    @Test
    @DisplayName("Test map list of BookEntity to list of Book")
    void toBookList() {
        // Arrange
        List<BookEntity> bookEntities = List.of(
                new BookEntity("123", "Title 1", "Synopsis 1", new BigDecimal("10.00"), 0.05, "cover1.jpg", LocalDate.of(2020, 1, 1)),
                new BookEntity("456", "Title 2", "Synopsis 2", new BigDecimal("20.00"), 0.10, "cover2.jpg", LocalDate.of(2021, 6, 15))
        );

        // Act
        List<Book> books = bookEntities.stream()
                .map(BookMapper::toBook)
                .toList();

        // Assert
        assertAll(
                () -> assertEquals(bookEntities.size(), books.size(), "List sizes should match"),
                () -> assertEquals(bookEntities.get(0).isbn(), books.get(0).getIsbn(), "First book's ISBN should match"),
                () -> assertEquals(bookEntities.get(1).title(), books.get(1).getTitle(), "Second book's title should match")
        );
    }

    @Test
    @DisplayName("Test map empty list of BookEntity returns empty list of Book")
    void toBookEmptyList() {
        // Arrange
        List<BookEntity> bookEntities = Collections.emptyList();

        // Act
        List<Book> books = bookEntities.stream()
                .map(BookMapper::toBook)
                .toList();

        // Assert
        assertTrue(books.isEmpty(), "Mapping an empty list should return an empty list");
    }

    @Test
    @DisplayName("Test map list of Book to list of BookCollectionDto")
    void toBookCollectionDtoList() {
        // Arrange
        Book book1 = new Book(
                "123456789",
                "Book 1",
                "Synopsis 1",
                new BigDecimal("30.00"),
                0.2,
                "cover1.jpg",
                LocalDate.of(2020, 5, 20)
        );
        Book book2 = new Book(
                "987654321",
                "Book 2",
                "Synopsis 2",
                new BigDecimal("40.00"),
                0.15,
                "cover2.jpg",
                LocalDate.of(2021, 8, 15)
        );

        book1.setAuthors(Collections.emptyList());
        book2.setAuthors(Collections.emptyList());

        List<Book> books = List.of(book1, book2);

        // Act
        List<BookCollectionQuery> dtos = books.stream()
                .map(BookMapper::toBookCollectionDto)
                .toList();

        // Assert
        assertAll(
                () -> assertEquals(books.size(), dtos.size(), "List sizes should match"),
                () -> assertEquals(books.get(0).getIsbn(), dtos.get(0).isbn(), "First book's ISBN should match"),
                () -> assertEquals(books.get(1).getTitle(), dtos.get(1).title(), "Second book's title should match")
        );
    }

    @Test
    @DisplayName("Test map empty list of Book returns empty list of BookCollectionDto")
    void toBookCollectionDtoEmptyList() {
        // Arrange
        List<Book> books = Collections.emptyList();

        // Act
        List<BookCollectionQuery> dtos = books.stream()
                .map(BookMapper::toBookCollectionDto)
                .toList();

        // Assert
        assertTrue(dtos.isEmpty(), "Mapping an empty list should return an empty list");
    }

}