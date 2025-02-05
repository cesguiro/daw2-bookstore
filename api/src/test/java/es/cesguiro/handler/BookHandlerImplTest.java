package es.cesguiro.handler;

import es.cesguiro.data.BookData;
import es.cesguiro.handler.impl.BookHandlerImpl;
import es.cesguiro.model.PageResponse;
import es.cesguiro.model.book.query.*;
import es.cesguiro.pagination.Page;
import es.cesguiro.usecase.book.query.FindAllBooksByCriteriaUseCase;
import es.cesguiro.usecase.book.query.FindBookByCriteriaUseCase;
import es.cesguiro.usecase.book.query.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookHandlerImplTest {

    @Mock
    private FindAllBooksByCriteriaUseCase findAllBooksByCriteriaUseCase;

    @Mock
    private FindBookByCriteriaUseCase findBookByCriteriaUseCase;

    @InjectMocks
    private BookHandlerImpl bookHandlerImpl;

    @Test
    @DisplayName("Test findAll method with page = 1 and size = 5 should return a PagedCollectionResponse with all books and no previous and next links")
    void testFindAll() throws Exception {

        Page<BookCollectionQuery> page = new Page<>(
                List.of(
                        BookData.getBookCollectionQueryEs(0),
                        BookData.getBookCollectionQueryEs(1),
                        BookData.getBookCollectionQueryEs(2),
                        BookData.getBookCollectionQueryEs(3),
                        BookData.getBookCollectionQueryEs(4)
                ),
                1,
                5,
                5);

        when(findAllBooksByCriteriaUseCase
                .findAll(1, 5))
                .thenReturn(page);

        PageResponse<BookCollectionResponse> expected =
                new PageResponse<BookCollectionResponse>(
                        List.of(
                                BookData.getBookCollectionResponseEs(0),
                                BookData.getBookCollectionResponseEs(1),
                                BookData.getBookCollectionResponseEs(2),
                                BookData.getBookCollectionResponseEs(3),
                                BookData.getBookCollectionResponseEs(4)
                        ),
                        1,
                        5,
                        5,
                        1,
                        null,
                        null
                );
        PageResponse<BookCollectionResponse> result = bookHandlerImpl.findAll(1, 5);
        assertAll(
                () -> assertEquals(expected.page(), result.page(), "Page should match"),
                () -> assertEquals(expected.pageSize(), result.pageSize(), "Page size should match"),
                () -> assertEquals(expected.totalItems(), result.totalItems(), "Total items should match"),
                () -> assertEquals(expected.totalPages(), result.totalPages(), "Total pages should match"),
                () -> assertEquals(expected.previous(), result.previous(), "Previous link should match"),
                () -> assertEquals(expected.next(), result.next(), "Next link should match"),
                () -> assertEquals(expected.data().getFirst().isbn(), result.data().getFirst().isbn(), "First isbn should march"),
                () -> assertEquals(expected.data().getLast().isbn(), result.data().getLast().isbn(), "Last isbn should march")
        );
    }

    @Test
    @DisplayName("Test findAll method with page = 1 and size = 2 should return a PagedCollectionResponse with 2 books and next link")
    void testFindAllWithNextLink() throws Exception {
        Page<BookCollectionQuery> page = new Page<>(
                List.of(BookData.getBookCollectionQueryEs(0), BookData.getBookCollectionQueryEs(1)),
                1,
                2,
                5);

        when(findAllBooksByCriteriaUseCase
                .findAll(1, 2))
                .thenReturn(page);

        PageResponse<BookCollectionResponse> expected =
                new PageResponse<BookCollectionResponse>(
                        List.of(BookData.getBookCollectionResponseEs(0), BookData.getBookCollectionResponseEs(1)),
                        1,
                        2,
                        5,
                        3,
                        null,
                        "http://localhost:8080/api/books?page=2&size=2"
                );
        PageResponse<BookCollectionResponse> result = bookHandlerImpl.findAll(1, 2);
        assertAll(
                () -> assertEquals(expected.page(), result.page(), "Page should match"),
                () -> assertEquals(expected.pageSize(), result.pageSize(), "Page size should match"),
                () -> assertEquals(expected.totalItems(), result.totalItems(), "Total items should match"),
                () -> assertEquals(expected.totalPages(), result.totalPages(), "Total pages should match"),
                () -> assertEquals(expected.previous(), result.previous(), "Previous link should match"),
                () -> assertEquals(expected.next(), result.next(), "Next link should match"),
                () -> assertEquals(expected.data().getFirst().isbn(), result.data().getFirst().isbn(), "First isbn should march"),
                () -> assertEquals(expected.data().getLast().isbn(), result.data().getLast().isbn(), "Last isbn should march")
        );
    }

    @Test
    @DisplayName("Test findAll method with page = 2 and size = 2 should return a PagedCollectionResponse with 2 book and previous and next link")
    void testFindAllWithPreviousAndNextLink() throws Exception {
        Page<BookCollectionQuery> page = new Page<>(
                List.of(BookData.getBookCollectionQueryEs(2), BookData.getBookCollectionQueryEs(3)),
                2,
                2,
                5);

        when(findAllBooksByCriteriaUseCase
                .findAll(2, 2))
                .thenReturn(page);

        PageResponse<BookCollectionResponse> expected =
                new PageResponse<BookCollectionResponse>(
                        List.of(BookData.getBookCollectionResponseEs(2), BookData.getBookCollectionResponseEs(3)),
                        2,
                        2,
                        5,
                        3,
                        "http://localhost:8080/api/books?page=1&size=2",
                        "http://localhost:8080/api/books?page=3&size=2"
                );
        PageResponse<BookCollectionResponse> result = bookHandlerImpl.findAll(2, 2);
        assertAll(
                () -> assertEquals(expected.page(), result.page(), "Page should match"),
                () -> assertEquals(expected.pageSize(), result.pageSize(), "Page size should match"),
                () -> assertEquals(expected.totalItems(), result.totalItems(), "Total items should match"),
                () -> assertEquals(expected.totalPages(), result.totalPages(), "Total pages should match"),
                () -> assertEquals(expected.previous(), result.previous(), "Previous link should match"),
                () -> assertEquals(expected.next(), result.next(), "Next link should match"),
                () -> assertEquals(expected.data().getFirst().isbn(), result.data().getFirst().isbn(), "First isbn should march"),
                () -> assertEquals(expected.data().getLast().isbn(), result.data().getLast().isbn(), "Last isbn should march")
        );
    }

    @Test
    @DisplayName("Test findAll method with page = 3 and size = 2 should return a PagedCollectionResponse with 1 book and previous link")
    void testFindAllWithPreviousLink() throws Exception {
        Page<BookCollectionQuery> page = new Page<>(
                List.of(BookData.getBookCollectionQueryEs(4)),
                3,
                2,
                5);

        when(findAllBooksByCriteriaUseCase
                .findAll(3, 2))
                .thenReturn(page);

        PageResponse<BookCollectionResponse> expected =
                new PageResponse<BookCollectionResponse>(
                        List.of(BookData.getBookCollectionResponseEs(4)),
                        3,
                        2,
                        5,
                        3,
                        "http://localhost:8080/api/books?page=2&size=2",
                        null
                );
        PageResponse<BookCollectionResponse> result = bookHandlerImpl.findAll(3, 2);
        assertAll(
                () -> assertEquals(expected.page(), result.page(), "Page should match"),
                () -> assertEquals(expected.pageSize(), result.pageSize(), "Page size should match"),
                () -> assertEquals(expected.totalItems(), result.totalItems(), "Total items should match"),
                () -> assertEquals(expected.totalPages(), result.totalPages(), "Total pages should match"),
                () -> assertEquals(expected.previous(), result.previous(), "Previous link should match"),
                () -> assertEquals(expected.next(), result.next(), "Next link should match"),
                () -> assertEquals(expected.data().getFirst().isbn(), result.data().getFirst().isbn(), "First isbn should march"),
                () -> assertEquals(expected.data().getLast().isbn(), result.data().getLast().isbn(), "Last isbn should march")
        );
    }

    @Test
    @DisplayName("Test findAll method without page and size = 5 should return a PagedCollectionResponse with all books and no previous and next links")
    void testFindAllWithoutPage() throws Exception {
        Page<BookCollectionQuery> page = new Page<>(
                BookData.getBookCollectionQueriesEs(),
                1,
                5,
                5);

        when(findAllBooksByCriteriaUseCase
                .findAll(1, 5))
                .thenReturn(page);

        PageResponse<BookCollectionResponse> expected =
                new PageResponse<BookCollectionResponse>(
                        BookData.getBookCollectionResponsesEs(),
                        1,
                        5,
                        5,
                        1,
                        null,
                        null
                );
        PageResponse<BookCollectionResponse> result = bookHandlerImpl.findAll(null, 5);
        assertAll(
                () -> assertEquals(expected.page(), result.page(), "Page should match"),
                () -> assertEquals(expected.pageSize(), result.pageSize(), "Page size should match"),
                () -> assertEquals(expected.totalItems(), result.totalItems(), "Total items should match"),
                () -> assertEquals(expected.totalPages(), result.totalPages(), "Total pages should match"),
                () -> assertEquals(expected.previous(), result.previous(), "Previous link should match"),
                () -> assertEquals(expected.next(), result.next(), "Next link should match"),
                () -> assertEquals(expected.data().getFirst().isbn(), result.data().getFirst().isbn(), "First isbn should march"),
                () -> assertEquals(expected.data().getLast().isbn(), result.data().getLast().isbn(), "Last isbn should march")
        );
    }

    @Test
    @DisplayName("Test findAll method with page = 1 and without size should return a PagedCollectionResponse with all books and no previous and next links")
    void testFindAllWithoutSize() throws Exception {
        Page<BookCollectionQuery> page = new Page<>(
                BookData.getBookCollectionQueriesEs(),
                1,
                10,
                5);

        when(findAllBooksByCriteriaUseCase
                .findAll(1, 10))
                .thenReturn(page);

        PageResponse<BookCollectionResponse> expected =
                new PageResponse<BookCollectionResponse>(
                        BookData.getBookCollectionResponsesEs(),
                        1,
                        10,
                        5,
                        1,
                        null,
                        null
                );
        PageResponse<BookCollectionResponse> result = bookHandlerImpl.findAll(1, null);
        assertAll(
                () -> assertEquals(expected.page(), result.page(), "Page should match"),
                () -> assertEquals(expected.pageSize(), result.pageSize(), "Page size should match"),
                () -> assertEquals(expected.totalItems(), result.totalItems(), "Total items should match"),
                () -> assertEquals(expected.totalPages(), result.totalPages(), "Total pages should match"),
                () -> assertEquals(expected.previous(), result.previous(), "Previous link should match"),
                () -> assertEquals(expected.next(), result.next(), "Next link should match"),
                () -> assertEquals(expected.data().getFirst().isbn(), result.data().getFirst().isbn(), "First isbn should march"),
                () -> assertEquals(expected.data().getLast().isbn(), result.data().getLast().isbn(), "Last isbn should march")
        );
    }

    @Test
    @DisplayName("Test findAll method without page and without size should return a PagedCollectionResponse with all books and no previous and next links")
    void testFindAllWithoutPageAndSize() throws Exception {
        Page<BookCollectionQuery> page = new Page<>(
                BookData.getBookCollectionQueriesEs(),
                1,
                5,
                5);

        when(findAllBooksByCriteriaUseCase
                .findAll(1, 10))
                .thenReturn(page);

        PageResponse<BookCollectionResponse> expected =
                new PageResponse<BookCollectionResponse>(
                        BookData.getBookCollectionResponsesEs(),
                        1,
                        10,
                        5,
                        1,
                        null,
                        null
                );
        PageResponse<BookCollectionResponse> result = bookHandlerImpl.findAll(null, null);
        assertAll(
                () -> assertEquals(expected.page(), result.page(), "Page should match"),
                () -> assertEquals(expected.pageSize(), result.pageSize(), "Page size should match"),
                () -> assertEquals(expected.totalItems(), result.totalItems(), "Total items should match"),
                () -> assertEquals(expected.totalPages(), result.totalPages(), "Total pages should match"),
                () -> assertEquals(expected.previous(), result.previous(), "Previous link should match"),
                () -> assertEquals(expected.next(), result.next(), "Next link should match"),
                () -> assertEquals(expected.data().getFirst().isbn(), result.data().getFirst().isbn(), "First isbn should march"),
                () -> assertEquals(expected.data().getLast().isbn(), result.data().getLast().isbn(), "Last isbn should march")
        );
    }

    @Test
    @DisplayName("Test findByIsbn method with isbn = isbn1 should return a BookResponse with isbn1")
    void testFindByIsbn() throws Exception {
        BookQuery bookQuery = BookData.getBookQueryEs(1);
        when(findBookByCriteriaUseCase
                .findByIsbn("isbn1"))
                .thenReturn(bookQuery);

        BookResponse expected = BookData.getBookResponseEs(1);
        BookResponse result = bookHandlerImpl.findByIsbn("isbn1");

        assertAll(
                () -> assertEquals(expected.isbn(), result.isbn(), "Isbn should match"),
                () -> assertEquals(expected.title(), result.title(), "Title should match"),
                () -> assertEquals(expected.synopsis(), result.synopsis(), "Synopsis should match"),
                () -> assertEquals(expected.basePrice(), result.basePrice(), "Base price should match"),
                () -> assertEquals(expected.discount(), result.discount(), "Discount should match"),
                () -> assertEquals(expected.finalPrice(), result.finalPrice(), "Final price should match"),
                () -> assertEquals(expected.cover(), result.cover(), "Cover should match"),
                () -> assertEquals(expected.publicationDate(), result.publicationDate(), "Publication date should match"),
                () -> assertEquals(expected.category().name(), result.category().name()),
                () -> assertEquals(expected.category().links(), result.category().links()),
                () -> assertEquals(expected.publisher().name(), result.publisher().name()),
                () -> assertEquals(expected.publisher().links(), result.publisher().links()),
                () -> assertEquals(expected.genres().getFirst().name(), result.genres().getFirst().name()),
                () -> assertEquals(expected.genres().getFirst().links(), result.genres().getFirst().links()),
                () -> assertEquals(expected.authors().getFirst().name(), result.authors().getFirst().name()),
                () -> assertEquals(expected.authors().getFirst().links(), result.authors().getFirst().links()),
                () -> assertEquals(expected.authors().get(1).name(), result.authors().get(1).name()),
                () -> assertEquals(expected.authors().get(1).links(), result.authors().get(1).links())
        );
    }

}