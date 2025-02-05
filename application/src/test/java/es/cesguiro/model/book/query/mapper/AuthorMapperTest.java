package es.cesguiro.model.book.query.mapper;

import es.cesguiro.data.AuthorData;
import es.cesguiro.model.book.query.AuthorCollectionResponse;
import es.cesguiro.model.book.query.AuthorReponse;
import es.cesguiro.usecase.book.query.model.AuthorCollectionQuery;
import es.cesguiro.usecase.book.query.model.AuthorQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorMapperTest {

    @Test
    @DisplayName("Test map AuthorCollectionQuery to AuthorCollectionResponse")
    void testMapAuthorCollectionQueryToAuthorCollectionResponse() {
        AuthorCollectionQuery authorCollectionQuery = AuthorData.getAuthorCollectionQuery(2);

        AuthorCollectionResponse expected = AuthorData.getAuthorCollectionResponse(2);
        AuthorCollectionResponse result = AuthorMapper.toAuthorCollectionResponse(authorCollectionQuery);

        assertAll(
                () -> assertEquals(expected.name(), result.name()),
                () -> assertEquals(expected.links().size(), result.links().size()),
                () -> assertEquals(expected.links().get("_self"), result.links().get("_self")),
                () -> assertEquals(expected.links().get("books"), result.links().get("books"))
        );
    }

    @Test
    @DisplayName("Test map AuthorCollectionQuery to AuthorCollectionResponse should return null")
    void testMapAuthorCollectionQueryToAuthorCollectionResponseNull() {
        AuthorCollectionQuery authorCollectionQuery = null;

        AuthorCollectionResponse result = AuthorMapper.toAuthorCollectionResponse(authorCollectionQuery);

        assertEquals(null, result);
    }

    @Test
    @DisplayName("Test map AuthorQuery to AuthorResponse")
    void testMapAuthorQueryToAuthorResponse() {
        AuthorQuery authorQuery = AuthorData.getAuthorQuery(2);
        AuthorReponse expected = AuthorData.getAuthorReponse(2);

        AuthorReponse result = AuthorMapper.toAuthorResponse(authorQuery);

        assertAll(
                () -> assertEquals(expected.name(), result.name()),
                () -> assertEquals(expected.links().size(), result.links().size()),
                () -> assertEquals(expected.links().get("_self"), result.links().get("_self")),
                () -> assertEquals(expected.links().get("books"), result.links().get("books"))
        );
    }

    @Test
    @DisplayName("Test map AuthorQuery to AuthorResponse should return null")
    void testMapAuthorQueryToAuthorResponseNull() {
        AuthorQuery authorQuery = null;

        AuthorReponse result = AuthorMapper.toAuthorResponse(authorQuery);

        assertEquals(null, result);
    }

}