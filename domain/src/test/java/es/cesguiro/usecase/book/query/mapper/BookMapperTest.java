package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.locale.LocaleProvider;
import es.cesguiro.locale.LocaleUtil;
import es.cesguiro.model.Author;
import es.cesguiro.model.Book;
import es.cesguiro.model.vo.LocaleString;
import es.cesguiro.repository.model.BookEntity;
import es.cesguiro.usecase.book.query.data.AuthorData;
import es.cesguiro.usecase.book.query.data.BookData;
import es.cesguiro.usecase.book.query.model.BookCollectionQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookMapperTest {

    @Mock
    private LocaleProvider mockLocaleProvider;

    @BeforeEach
    void setup() {
        LocaleUtil.resetInstance();
        LocaleUtil.getInstance(mockLocaleProvider);
    }

    @AfterEach
    void teardown() {
        LocaleUtil.resetInstance();
    }

    @Test
    @DisplayName("Test map BookEntity to Book")
    void toBook() {
        BookEntity bookEntity = BookData.getBookEntity(0);

        Book result = BookMapper.toBook(bookEntity);

        assertAll(
                () -> assertEquals(bookEntity.isbn(), result.getIsbn(), "ISBNs should match"),
                () -> assertEquals(bookEntity.titleEs(), result.getTitle("es"), "Titles should match"),
                () -> assertEquals(bookEntity.titleEn(), result.getTitle("en"), "Titles should match"),
                () -> assertEquals(bookEntity.synopsisEs(), result.getSynopsis("es"), "Synopses should match"),
                () -> assertEquals(bookEntity.synopsisEn(), result.getSynopsis("en"), "Synopses should match"),
                () -> assertEquals(bookEntity.basePrice(), result.getBasePrice(), "Base prices should match"),
                () -> assertEquals(bookEntity.discountPercentage(), result.getDiscountPercentage(), "Discounts should match"),
                () -> assertEquals(bookEntity.cover(), result.getCover(), "Covers should match"),
                () -> assertEquals(bookEntity.publicationDate(), result.getPublicationDate(), "Publication dates should match")
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
    @DisplayName("Test map Book to BookCollectionQuery with Locale es")
    void toBookCollectionQuery() {
        when(mockLocaleProvider.getLanguage()).thenReturn("es");

        Book book = BookData.getBooks().getFirst();
        book.setAuthors(List.of(AuthorData.getAuthors().getFirst(), AuthorData.getAuthors().get(1)));

        BookCollectionQuery result = BookMapper.toBookCollectionQuery(book);

        assertAll(
                () -> assertEquals(book.getIsbn(), result.isbn(), "ISBNs should match"),
                () -> assertEquals(book.getTitle("es"), result.title(), "Titles should match"),
                () -> assertEquals(book.getBasePrice(), result.basePrice(), "Base prices should match"),
                () -> assertEquals(book.getDiscountPercentage(), result.discount(), "Discounts should match"),
                () -> assertEquals(book.getCover(), result.cover(), "Covers should match"),
                () -> assertEquals(book.calculateFinalPrice(), result.finalPrice(), "Final prices should match"),
                () -> assertEquals(
                        book.getAuthors().size(),
                        result.authors().size(),
                        "Number of authors should match"
                )
        );
    }

    @Test
    @DisplayName("Test map Book to BookCollectionQuery with Locale en")
    void toBookCollectionQueryEn() {
        when(mockLocaleProvider.getLanguage()).thenReturn("en");

        Book book = BookData.getBooks().get(1);
        book.setAuthors(List.of(AuthorData.getAuthors().get(1), AuthorData.getAuthors().get(2)));

        BookCollectionQuery result = BookMapper.toBookCollectionQuery(book);

        assertAll(
                () -> assertEquals(book.getIsbn(), result.isbn(), "ISBNs should match"),
                () -> assertEquals(book.getTitle("en"), result.title(), "Titles should match"),
                () -> assertEquals(book.getBasePrice(), result.basePrice(), "Base prices should match"),
                () -> assertEquals(book.getDiscountPercentage(), result.discount(), "Discounts should match"),
                () -> assertEquals(book.getCover(), result.cover(), "Covers should match"),
                () -> assertEquals(book.calculateFinalPrice(), result.finalPrice(), "Final prices should match"),
                () -> assertEquals(
                        book.getAuthors().size(),
                        result.authors().size(),
                        "Number of authors should match"
                )
        );
    }

    @Test
    @DisplayName("Test null Book returns null BookCollectionQuery")
    void toBookCollectionQueryNull() {
        BookCollectionQuery result = BookMapper.toBookCollectionQuery(null);

        assertNull(result, "Mapping null Book should return null BookCollectionDto");
    }

    @Test
    @DisplayName("Test map list of BookEntity to list of Book")
    void toBookList() {
        List<BookEntity> bookEntities = List.of(
                BookData.getBookEntity(0),
                BookData.getBookEntity(1)
        );

        // Act
        List<Book> result = bookEntities.stream()
                .map(BookMapper::toBook)
                .toList();

        // Assert
        assertAll(
                () -> assertEquals(bookEntities.size(), result.size(), "List sizes should match"),
                () -> assertEquals(bookEntities.getFirst().isbn(), result.getFirst().getIsbn(), "First book's ISBN should match"),
                () -> assertEquals(bookEntities.getFirst().titleEs(), result.getFirst().getTitle("es"), "First book's title should match"),
                () -> assertEquals(bookEntities.getFirst().synopsisEn(), result.getFirst().getSynopsis("en"), "First book's synopsis should match"),
                () -> assertEquals(bookEntities.get(1).isbn(), result.get(1).getIsbn(), "Second book's ISBN should match"),
                () -> assertEquals(bookEntities.getLast().titleEs(), result.get(1).getTitle("es"), "Second book's title should match"),
                () -> assertEquals(bookEntities.getLast().synopsisEn(), result.get(1).getSynopsis("en"), "Second book's synopsis should match")
        );
    }

    @Test
    @DisplayName("Test map empty list of BookEntity returns empty list of Book")
    void toBookEmptyList() {
        // Arrange
        List<BookEntity> bookEntities = Collections.emptyList();

        // Act
        List<Book> result = bookEntities.stream()
                .map(BookMapper::toBook)
                .toList();

        // Assert
        assertTrue(result.isEmpty(), "Mapping an empty list should return an empty list");
    }

    @Test
    @DisplayName("Test map list of Book to list of BookCollectionQuery with Locale es")
    void toBookCollectionQueryList() {
        when(mockLocaleProvider.getLanguage()).thenReturn("es");

        Book book1 = BookData.getBooks().getFirst();
        Book book2 = BookData.getBooks().get(1);

        book1.setAuthors(Collections.emptyList());
        book2.setAuthors(Collections.emptyList());

        List<Book> books = List.of(book1, book2);

        List<BookCollectionQuery> result = books.stream()
                .map(BookMapper::toBookCollectionQuery)
                .toList();

        assertAll(
                () -> assertEquals(books.size(), result.size(), "List sizes should match"),
                () -> assertEquals(books.getFirst().getIsbn(), result.getFirst().isbn(), "First book's ISBN should match"),
                () -> assertEquals(books.getFirst().getTitle("es"), result.getFirst().title(), "Second book's title should match"),
                () -> assertEquals(books.get(1).getIsbn(), result.get(1).isbn(), "Second book's ISBN should match"),
                () -> assertEquals(books.get(1).getTitle("es"), result.get(1).title(), "Second book's title should match"),
                () -> assertEquals(books.getFirst().calculateFinalPrice(), result.getFirst().finalPrice(), "First book's final price should match"),
                () -> assertEquals(books.get(1).calculateFinalPrice(), result.get(1).finalPrice(), "Second book's final price should match")
        );
    }

    @Test
    @DisplayName("Test map empty list of Book returns empty list of BookCollectionQuery")
    void toBookCollectionQueryEmptyList() {
        // Arrange
        List<Book> books = Collections.emptyList();

        // Act
        List<BookCollectionQuery> result = books.stream()
                .map(BookMapper::toBookCollectionQuery)
                .toList();

        // Assert
        assertTrue(result.isEmpty(), "Mapping an empty list should return an empty list");
    }

}