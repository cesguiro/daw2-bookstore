package es.cesguiro.usecase.book.query.impl;

import es.cesguiro.repository.*;
import es.cesguiro.repository.model.*;
import es.cesguiro.usecase.book.query.model.BookDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindByIsbnServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private GenreRepository genreRepository;
    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private FindByIsbnService findByIsbnService;

    @Test
    @DisplayName("Test execute method return BookDto")
    void testExecuteMethodReturnBookDto() {
        // Arrange
        String isbn = "978-84-376-0494-7";
        // Act
        when(bookRepository.findByIsbn(isbn)).thenReturn(
                Optional.of(
                        new BookEntity(
                                isbn,
                                "title",
                                "synopsis",
                                new BigDecimal("10.00"),
                                5,
                                isbn + ".jpg",
                                LocalDate.of(2021, 1, 1)
                        )
                )
        );
        when(authorRepository.findAllByBookIsbn(isbn)).thenReturn(
                List.of(
                        new AuthorEntity("author1", "nationality1", "Bio 1", 1970, null, "slug1"),
                        new AuthorEntity("author2", "nationality2", "Bio 2", 1980, 2024, "slug2")
                )
        );
        when(genreRepository.findAllByBookIsbn(isbn)).thenReturn(
                List.of(
                        new GenreEntity("genre1", "slug1"),
                        new GenreEntity("genre2", "slug2")
                )
        );
        when(publisherRepository.findByBookIsbn(isbn)).thenReturn(
                Optional.of(new PublisherEntity("publisher", "slug"))
        );
        when(categoryRepository.findByBookIsbn(isbn)).thenReturn(
                Optional.of(new CategoryEntity("category", "slug"))
        );
        BookDto bookDto = findByIsbnService.execute(isbn);
        // Assert
        assertAll("bookDto",
                () -> assertNotNull(bookDto),
                () -> assertEquals(isbn, bookDto.isbn()),
                () -> assertNotNull(bookDto.title()),
                () -> assertNotNull(bookDto.authors()),
                () -> assertNotNull(bookDto.genres()),
                () -> assertNotNull(bookDto.publisher()),
                () -> assertNotNull(bookDto.category()),
                () -> assertEquals("author1", bookDto.authors().getFirst().name()),
                () -> assertEquals("author2", bookDto.authors().get(1).name()),
                () -> assertEquals("genre1", bookDto.genres().getFirst().name()),
                () -> assertEquals("genre2", bookDto.genres().get(1).name()),
                () -> assertEquals("publisher", bookDto.publisher().name()),
                () -> assertEquals("category", bookDto.category().name()),
                () -> assertEquals(0, bookDto.finalPrice().compareTo(new BigDecimal("9.50")))
        );
    }

    @Test
    @DisplayName("Test when book not found then throw IllegalArgumentException")
    void testWhenBookNotFoundThenThrowIllegalArgumentException() {
        // Arrange
        String isbn = "978-84-376-0494-7";
        // Act
        when(bookRepository.findByIsbn(isbn)).thenReturn(Optional.empty());
        // Assert
        assertThrows(IllegalArgumentException.class, () -> findByIsbnService.execute(isbn));
    }

}