package es.cesguiro.service.book.query;

import es.cesguiro.exception.DomainException;
import es.cesguiro.exception.ResourceNotFoundException;
import es.cesguiro.locale.LocaleProvider;
import es.cesguiro.locale.LocaleUtil;
import es.cesguiro.pagination.Page;
import es.cesguiro.repository.*;
import es.cesguiro.repository.model.*;
import es.cesguiro.usecase.book.query.data.*;
import es.cesguiro.usecase.book.query.model.BookCollectionQuery;
import es.cesguiro.usecase.book.query.model.BookQuery;
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
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindBookByCriteriaServiceTest {

    @Mock
    private LocaleProvider mockLocaleProvider;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private GenreRepository genreRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private FindBookByCriteriaService findByCriterialService;

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
    @DisplayName("Test execute method return BookQuery")
    void testFindByIsbnMethodReturnBookQuery() {
        when(mockLocaleProvider.getLanguage()).thenReturn("es");

        String isbn = "isbn1";
        when(bookRepository.findByIsbn(isbn)).thenReturn(
                Optional.of(BookData.getBookEntity(0))
        );
        when(authorRepository.findAllByBookIsbn(isbn)).thenReturn(
                List.of(
                        AuthorData.getAuthorEntity(0),
                        AuthorData.getAuthorEntity(1)
                )
        );
        when(genreRepository.findAllByBookIsbn(isbn)).thenReturn(
                List.of(
                        GenreData.getGenreEntity(0),
                        GenreData.getGenreEntity(1)
                )
        );
        when(publisherRepository.findByBookIsbn(isbn)).thenReturn(
                Optional.of(PublisherData.getPublisherEntity(0))
        );
        when(categoryRepository.findByBookIsbn(isbn)).thenReturn(
                Optional.of(CategoryData.getCategoryEntity(0))
        );

        BookQuery result = findByCriterialService.findByIsbn(isbn);
        // Assert
        assertAll("bookQuery",
                () -> assertNotNull(result),
                () -> assertEquals(isbn, result.isbn()),
                () -> assertNotNull(result.title()),
                () -> assertNotNull(result.authors()),
                () -> assertNotNull(result.genres()),
                () -> assertNotNull(result.publisher()),
                () -> assertNotNull(result.category()),
                () -> assertEquals(AuthorData.getAuthorEntity(0).name(), result.authors().getFirst().name()),
                () -> assertEquals(AuthorData.getAuthorEntity(1).name(), result.authors().get(1).name()),
                () -> assertEquals(GenreData.getGenreEntity(0).nameEs(), result.genres().getFirst().name()),
                () -> assertEquals(GenreData.getGenreEntity(1).nameEs(), result.genres().get(1).name()),
                () -> assertEquals(PublisherData.getPublisherEntity(0).name(), result.publisher().name()),
                () -> assertEquals(CategoryData.getCategoryEntity(0).nameEs(), result.category().name()),
                () -> assertEquals(0, result.finalPrice().compareTo(new BigDecimal("26.99")))
        );
    }

    @Test
    @DisplayName("Test when book not found then throw ResourceNotFoundException")
    void testWhenBookNotFoundThenThrowIllegalArgumentException() {
        // Arrange
        String isbn = "978-84-376-0494-7";
        // Act
        when(bookRepository.findByIsbn(isbn)).thenReturn(Optional.empty());
        // Assert
        assertThrows(ResourceNotFoundException.class, () -> findByCriterialService.findByIsbn(isbn));
    }


}