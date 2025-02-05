package es.cesguiro.model.book.query.mapper;

import es.cesguiro.data.BookData;
import es.cesguiro.model.book.query.BookCollectionResponse;
import es.cesguiro.model.book.query.BookResponse;
import es.cesguiro.usecase.book.query.model.BookCollectionQuery;
import es.cesguiro.usecase.book.query.model.BookQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {

    @Test
    @DisplayName("Test map BookCollectionQuery to BookCollectionResponse")
    void testMapBookCollectionQueryToBookCollectionResponse() {
        BookCollectionQuery bookCollectionQuery = BookData.getBookCollectionQueryEs(2);

        BookCollectionResponse expected = BookData.getBookCollectionResponseEs(2);
        BookCollectionResponse result = BookMapper.toBookCollectionResponse(bookCollectionQuery);

        assertAll(
                () -> assertEquals(expected.isbn(), result.isbn()),
                () -> assertEquals(expected.title(), result.title()),
                () -> assertEquals(expected.basePrice(), result.basePrice()),
                () -> assertEquals(expected.discount(), result.discount()),
                () -> assertEquals(expected.finalPrice(), result.finalPrice()),
                () -> assertEquals(expected.cover(), result.cover()),
                () -> assertEquals(expected.authors().size(), result.authors().size()),
                () -> assertEquals(expected.links().size(), result.links().size()),
                () -> assertEquals(expected.links().get("_self"), result.links().get("_self"))
        );
    }

    @Test
    @DisplayName("Test map BookCollectionQuery to BookCollectionResponse should return null")
    void testMapBookCollectionQueryToBookCollectionResponseNull() {
        BookCollectionQuery bookCollectionQuery = null;

        BookCollectionResponse result = BookMapper.toBookCollectionResponse(bookCollectionQuery);

        assertEquals(null, result);
    }


    @Test
    @DisplayName("Test map BookQuery to BookResponse")
    void testMapBookQueryToBookResponse() {
        BookQuery bookQuery = BookData.getBookQueryEs(2);

        BookResponse expected = BookData.getBookResponseEs(2);
        BookResponse result = BookMapper.toBookResponse(bookQuery);

        assertAll(
                () -> assertEquals(expected.isbn(), result.isbn()),
                () -> assertEquals(expected.title(), result.title()),
                () -> assertEquals(expected.synopsis(), result.synopsis()),
                () -> assertEquals(expected.basePrice(), result.basePrice()),
                () -> assertEquals(expected.discount(), result.discount()),
                () -> assertEquals(expected.finalPrice(), result.finalPrice()),
                () -> assertEquals(expected.cover(), result.cover()),
                () -> assertEquals(expected.publicationDate(), result.publicationDate()),
                () -> assertEquals(expected.publisher(), result.publisher()),
                () -> assertEquals(expected.category(), result.category()),
                () -> assertEquals(expected.genres().size(), result.genres().size()),
                () -> assertEquals(expected.authors().size(), result.authors().size())
        );
    }

    @Test
    @DisplayName("Test map BookQuery to BookResponse should return null")
    void testMapBookQueryToBookResponseNull() {
        BookQuery bookQuery = null;

        BookResponse result = BookMapper.toBookResponse(bookQuery);

        assertEquals(null, result);
    }

}