package es.cesguiro.model.book.query.mapper;

import es.cesguiro.data.GenreData;
import es.cesguiro.model.book.query.GenreResponse;
import es.cesguiro.usecase.book.query.model.GenreQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreMapperTest {

    @Test
    @DisplayName("Test toGenreResponse should return GenreResponse")
    void testToGenreResponse() {

        GenreQuery genreQuery = GenreData.getGenreQueryEs(1);

        GenreResponse expected = GenreData.getGenreResponseEs(1);
        GenreResponse result = GenreMapper.toGenreResponse(genreQuery);

        assertAll(
                () -> assertEquals(expected.name(), result.name()),
                () -> assertEquals(expected.links().size(), result.links().size()),
                () -> assertEquals(expected.links().get("books"), result.links().get("books"))
        );
    }

    @Test
    @DisplayName("Test toGenreResponse should return null")
    void testToGenreResponseNull() {

        GenreQuery genreQuery = null;

        GenreResponse result = GenreMapper.toGenreResponse(genreQuery);

        assertEquals(null, result);
    }

}