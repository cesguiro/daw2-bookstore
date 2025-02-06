package es.cesguiro.service.book.query;

import es.cesguiro.exception.DomainException;
import es.cesguiro.locale.DefaultLocaleProvider;
import es.cesguiro.locale.LocaleProvider;
import es.cesguiro.locale.LocaleUtil;
import es.cesguiro.pagination.Page;
import es.cesguiro.repository.*;
import es.cesguiro.repository.model.AuthorEntity;
import es.cesguiro.repository.model.BookEntity;
import es.cesguiro.usecase.book.query.data.AuthorData;
import es.cesguiro.usecase.book.query.data.BookData;
import es.cesguiro.usecase.book.query.model.BookCollectionQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
class FindAllBooksByCriteriaServiceTest {

    @Mock
    private LocaleProvider mockLocaleProvider;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private FindAllBooksByCriteriaService findAllBooksByCriteriaService;

    @BeforeEach
    void setup() {
        LocaleUtil.setLocaleProvider(mockLocaleProvider);
    }

    @AfterEach
    void teardown() {
        LocaleUtil.resetLocaleProvider();
    }

    @Test
    @DisplayName("Test execute method returns Page of BookCollectionQuery")
    void findAllReturnsBookCollectionQueryList() {
        when(mockLocaleProvider.getLanguage()).thenReturn("es");

        int page = 1;
        int size = 4;


        List<BookEntity> bookEntities = List.of(
                BookData.getBookEntity(0),
                BookData.getBookEntity(1),
                BookData.getBookEntity(2),
                BookData.getBookEntity(3)
        );
        when(bookRepository.findAll(page, size)).thenReturn(new Page<>(bookEntities, page, size, bookEntities.size()));

        List<AuthorEntity> authorEntities1 = List.of(AuthorData.getAuthorEntity(0));
        List<AuthorEntity> authorEntities2 = List.of(AuthorData.getAuthorEntity(1));
        List<AuthorEntity> authorEntities3 = List.of(AuthorData.getAuthorEntity(2));
        List<AuthorEntity> authorEntities4 = List.of(AuthorData.getAuthorEntity(1), AuthorData.getAuthorEntity(3));
        when(authorRepository.findAllByBookIsbn(bookEntities.getFirst().isbn())).thenReturn(authorEntities1);
        when(authorRepository.findAllByBookIsbn(bookEntities.get(1).isbn())).thenReturn(authorEntities2);
        when(authorRepository.findAllByBookIsbn(bookEntities.get(2).isbn())).thenReturn(authorEntities3);
        when(authorRepository.findAllByBookIsbn(bookEntities.get(3).isbn())).thenReturn(authorEntities4);

        Page<BookCollectionQuery> result = findAllBooksByCriteriaService.findAll(page, size);

        assertAll(
                () -> assertEquals(4, result.data().size(), "Result list size should match"),
                () -> assertEquals(bookEntities.getFirst().isbn(), result.data().getFirst().isbn(), "First book ISBN should match"),
                () -> assertEquals(bookEntities.get(1).isbn(), result.data().get(1).isbn(), "Second book ISBN should match"),
                () -> assertEquals(bookEntities.get(2).isbn(), result.data().get(2).isbn(), "Third book ISBN should match"),
                () -> assertEquals(bookEntities.get(3).isbn(), result.data().get(3).isbn(), "Fourth book ISBN should match"),
                () -> assertEquals(bookEntities.getFirst().titleEs(), result.data().getFirst().title(), "First book title should match"),
                () -> assertEquals(1, result.data().getFirst().authors().size(), "First book authors size should match"),
                () -> assertEquals(1, result.data().get(1).authors().size(), "Second book authors size should match"),
                () -> assertEquals(1, result.data().get(2).authors().size(), "Third book authors size should match"),
                () -> assertEquals(2, result.data().get(3).authors().size(), "Fourth book authors size should match"),
                () -> assertEquals(authorEntities1.getFirst().name(), result.data().getFirst().authors().getFirst().name(), "First book author name should match"),
                () -> assertEquals(authorEntities2.getFirst().name(), result.data().get(1).authors().getFirst().name(), "Second book author name should match"),
                () -> assertEquals(authorEntities3.getFirst().name(), result.data().get(2).authors().getFirst().name(), "Third book author name should match"),
                () -> assertEquals(authorEntities4.getFirst().name(), result.data().get(3).authors().getFirst().name(), "Fourth book first author name should match"),
                () -> assertEquals(authorEntities4.get(1).name(), result.data().get(3).authors().get(1).name(), "Fourth book second author name should match")
        );


        // Verify interactions with repositories
        Mockito.verify(bookRepository).findAll(page, size);
        Mockito.verify(authorRepository).findAllByBookIsbn(bookEntities.getFirst().isbn());
        Mockito.verify(authorRepository).findAllByBookIsbn(bookEntities.get(1).isbn());
    }

    @Test
    @DisplayName("Test when bookRepository returns an empty list")
    void testEmptyBookList() {
        when(bookRepository.findAll(2, 10)).thenReturn(new Page<>(Collections.emptyList(), 2, 10, 10));

        Page<BookCollectionQuery> result = findAllBooksByCriteriaService.findAll(2, 10);

        assertTrue(result.data().isEmpty(), "The result should be an empty list when no books are found");
    }

    @Test
    @DisplayName("Test when authorRepository returns an empty list for all books")
    void testEmptyAuthorList() {
        when(mockLocaleProvider.getLanguage()).thenReturn("es");

        BookEntity bookEntity = BookData.getBookEntity(0);
        when(bookRepository.findAll(1,2)).thenReturn(new Page<>(List.of(bookEntity), 1, 2, 1));
        when(authorRepository.findAllByBookIsbn(bookEntity.isbn())).thenReturn(Collections.emptyList());

        Page<BookCollectionQuery> result = findAllBooksByCriteriaService.findAll(1, 2);

        assertFalse(result.data().isEmpty(), "The result should not be empty");
        assertTrue(result.data().getFirst().authors().isEmpty(), "The authors list should be empty for books with no authors");
    }

    @Test
    @DisplayName(("Test when some books have authors and some don't"))
    void testBooksAndAuthors() {
        when(mockLocaleProvider.getLanguage()).thenReturn("es");

        BookEntity bookEntity1 = BookData.getBookEntity(0);
        BookEntity bookEntity2 = BookData.getBookEntity(1);
        when(bookRepository.findAll(1, 2)).thenReturn(new Page<>(List.of(bookEntity1, bookEntity2), 1, 2, 2));
        when(authorRepository.findAllByBookIsbn(bookEntity1.isbn())).thenReturn(Collections.emptyList());
        when(bookRepository.findAll(1, 2)).thenReturn(new Page<>(List.of(bookEntity1, bookEntity2), 1, 2, 2));
        when(authorRepository.findAllByBookIsbn(bookEntity1.isbn())).thenReturn(Collections.emptyList());
        AuthorEntity authorEntity = AuthorData.getAuthorEntity(0);
        when(authorRepository.findAllByBookIsbn(bookEntity2.isbn())).thenReturn(List.of(authorEntity));

        Page<BookCollectionQuery> result = findAllBooksByCriteriaService.findAll(1, 2);

        assertEquals(2, result.data().size(), "The result should contain two books");
        assertTrue(result.data().get(0).authors().isEmpty(), "The first book should have no authors");
        assertEquals(1, result.data().get(1).authors().size(), "The second book should have one author");
        assertEquals(authorEntity.name(), result.data().get(1).authors().getFirst().name(), "The author's name should match");
    }


    @Test
    @DisplayName("Test when book has multiple authors")
    void testMultipleAuthors() {
        when(mockLocaleProvider.getLanguage()).thenReturn("es");

        BookEntity bookEntity = BookData.getBookEntity(0);
        when(bookRepository.findAll(1, 10)).thenReturn(new Page<>(List.of(bookEntity), 1, 10, 1));
        AuthorEntity authorEntity1 = AuthorData.getAuthorEntity(0);
        AuthorEntity authorEntity2 = AuthorData.getAuthorEntity(1);
        when(authorRepository.findAllByBookIsbn(bookEntity.isbn())).thenReturn(List.of(authorEntity1, authorEntity2));

        Page<BookCollectionQuery> result = findAllBooksByCriteriaService.findAll(1, 10);

        assertEquals(1, result.data().size(), "The result should contain one book");
        assertEquals(2, result.data().getFirst().authors().size(), "The book should have two authors");
        assertEquals(authorEntity1.name(), result.data().getFirst().authors().get(0).name(), "The first author's name should match");
        assertEquals(authorEntity2.name(), result.data().getFirst().authors().get(1).name(), "The second author's name should match");
    }

    @Test
    @DisplayName("Test when books have discounts")
    void testBooksWithDiscount() {
        when(mockLocaleProvider.getLanguage()).thenReturn("es");

        BookEntity bookEntity1 = BookData.getBookEntity(0);
        BookEntity bookEntity2 = BookData.getBookEntity(1);
        when(bookRepository.findAll(1, 10)).thenReturn(new Page<>(List.of(bookEntity1, bookEntity2), 1, 10, 2));

        Page<BookCollectionQuery> result = findAllBooksByCriteriaService.findAll(1, 10);

        assertEquals(new BigDecimal("26.99"), result.data().getFirst().finalPrice(), "The final price should be correctly calculated with 10% discount");
        assertEquals(new BigDecimal("15.99"), result.data().get(1).finalPrice(), "The final price should be correctly calculated with 20% discount");
    }

    @Test
    @DisplayName("Test when books have incomplete information")
    void testBooksWithIncompleteInformation() {
        when(mockLocaleProvider.getLanguage()).thenReturn("es");

        BookEntity bookEntity = new BookEntity("isbn1", "title1", null, "synopsisEs", null, BigDecimal.ZERO, 10.00, null, LocalDate.now());
        when(bookRepository.findAll(1, 10)).thenReturn(new Page<>(List.of(bookEntity), 0, 10, 1));

        Page<BookCollectionQuery> result = findAllBooksByCriteriaService.findAll(1, 10);

        assertFalse(result.data().isEmpty(), "The result should not be empty");
        assertEquals(BigDecimal.ZERO, result.data().getFirst().basePrice(), "The book's basePrice should be BigDecimal.ZERO when it is null in the entity");
        assertNull(result.data().getFirst().cover(), "The book's cover should be null");
    }

    @Test
    @DisplayName("Test pagination with size = 0 should throw DomainException")
    void testEdgePaginationZeroPageSize() {
        // Act & Assert
        assertThrows(DomainException.class, () -> findAllBooksByCriteriaService.findAll(0, 0), "Expected exception to be thrown");
    }

    @Test
    @DisplayName("Test pagination with negative page value should throw DomainException")
    void testEdgePaginationNegativePageValue() {
        // Act & Assert
        assertThrows(DomainException.class, () -> findAllBooksByCriteriaService.findAll(-1, 10), "Expected exception to be thrown");
    }

    @Test
    @DisplayName("Test edge case pagination with page = Integer.MAX_VALUE and size = Integer.MAX_VALUE")
    void testEdgePaginationMaxValues() {
        when(mockLocaleProvider.getLanguage()).thenReturn("es");

        List<BookEntity> bookEntities = IntStream.range(0, 10)
                .mapToObj(i -> new BookEntity("isbn" + i, "titleEs" + i, "titleEn" + i,"synopsisEs" + i, "synopsisEn" + i,  BigDecimal.TEN, 10.00, "cover" + i, LocalDate.now()))
                .collect(Collectors.toList());
        when(bookRepository.findAll(Integer.MAX_VALUE, Integer.MAX_VALUE)).thenReturn(new Page<>(bookEntities, Integer.MAX_VALUE, Integer.MAX_VALUE, 10));

        Page<BookCollectionQuery> result = findAllBooksByCriteriaService.findAll(Integer.MAX_VALUE, Integer.MAX_VALUE);

        assertEquals(10, result.data().size(), "The result should contain 10 books");
    }


}