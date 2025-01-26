package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.model.Genre;
import es.cesguiro.repository.model.GenreEntity;
import es.cesguiro.usecase.book.query.model.GenreQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenreMapperTest {

    @Test
    @DisplayName("Test map GenreEntity to Genre")
    void toGenre() {
        GenreEntity genreEntity = new GenreEntity("Fantasy", "fantasy");
        Genre genre = GenreMapper.toGenre(genreEntity);
        assertAll(
                () -> assertEquals(genreEntity.name(), genre.getName(), "Names should match"),
                () -> assertEquals(genreEntity.slug(), genre.getSlug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null GenreEntity returns null Genre")
    void toGenreNull() {
        Genre genre = GenreMapper.toGenre(null);
        assertNull(genre, "Mapping null GenreEntity should return null Genre");
    }

    @Test
    @DisplayName("Test map List of GenreEntity to List of Genre")
    void toGenreList() {
        List<GenreEntity> genreEntities = List.of(
                new GenreEntity("Fantasy", "fantasy"),
                new GenreEntity("Science Fiction", "science-fiction")
        );
        List<Genre> genres = genreEntities.stream()
                .map(GenreMapper::toGenre)
                .toList();
        assertAll(
                () -> assertEquals(genreEntities.getFirst().name(), genres.getFirst().getName(), "Names should match"),
                () -> assertEquals(genreEntities.getFirst().slug(), genres.getFirst().getSlug(), "Slugs should match"),
                () -> assertEquals(genreEntities.get(1).name(), genres.get(1).getName(), "Names should match"),
                () -> assertEquals(genreEntities.get(1).slug(), genres.get(1).getSlug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test map empty List of GenreEntity to empty List of Genre")
    void toGenreListEmpty() {
        List<GenreEntity> genreEntities = List.of();
        List<Genre> genres = genreEntities.stream()
                .map(GenreMapper::toGenre)
                .toList();
        assertTrue(genres.isEmpty(), "Mapping empty List of GenreEntity should return empty List of Genre");
    }

    @Test
    @DisplayName("Test map Genre to GenreDto")
    void toGenreDto() {
        Genre genre = new Genre("Fantasy", "fantasy");
        GenreQuery genreQuery = GenreMapper.toGenreDto(genre);
        assertAll(
                () -> assertEquals(genre.getName(), genreQuery.name(), "Names should match"),
                () -> assertEquals(genre.getSlug(), genreQuery.slug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null Genre returns null GenreDto")
    void toGenreDtoNull() {
        GenreQuery genreQuery = GenreMapper.toGenreDto(null);
        assertNull(genreQuery, "Mapping null Genre should return null GenreDto");
    }

    @Test
    @DisplayName("Test map List of Genre to List of GenreDto")
    void toGenreDtoList() {
        List<Genre> genres = List.of(
                new Genre("Fantasy", "fantasy"),
                new Genre("Science Fiction", "science-fiction")
        );
        List<GenreQuery> genreQueries = genres.stream()
                .map(GenreMapper::toGenreDto)
                .toList();
        assertAll(
                () -> assertEquals(genres.getFirst().getName(), genreQueries.getFirst().name(), "Names should match"),
                () -> assertEquals(genres.getFirst().getSlug(), genreQueries.getFirst().slug(), "Slugs should match"),
                () -> assertEquals(genres.get(1).getName(), genreQueries.get(1).name(), "Names should match"),
                () -> assertEquals(genres.get(1).getSlug(), genreQueries.get(1).slug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test map empty List of Genre to empty List of GenreDto")
    void toGenreDtoListEmpty() {
        List<Genre> genres = List.of();
        List<GenreQuery> genreQueries = genres.stream()
                .map(GenreMapper::toGenreDto)
                .toList();
        assertTrue(genreQueries.isEmpty(), "Mapping empty List of Genre should return empty List of GenreDto");
    }

}