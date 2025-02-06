package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.locale.DefaultLocaleProvider;
import es.cesguiro.locale.LocaleProvider;
import es.cesguiro.locale.LocaleUtil;
import es.cesguiro.model.Genre;
import es.cesguiro.model.vo.LocaleString;
import es.cesguiro.repository.model.GenreEntity;
import es.cesguiro.usecase.book.query.data.GenreData;
import es.cesguiro.usecase.book.query.model.GenreQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreMapperTest {

    @Mock
    private LocaleProvider mockLocaleProvider;

    @BeforeEach
    void setup() {
        LocaleUtil.setLocaleProvider(mockLocaleProvider);
    }

    @AfterEach
    void teardown() {
        LocaleUtil.resetLocaleProvider();
    }


    @Test
    @DisplayName("Test map GenreEntity to Genre")
    void toGenre() {
        GenreEntity genreEntity = GenreData.getGenreEntity(0);
        Genre result = GenreMapper.toGenre(genreEntity);
        assertAll(
                () -> assertEquals(genreEntity.nameEs(), result.getName("es"), "Names should match"),
                () -> assertEquals(genreEntity.nameEn(), result.getName("en"), "Names should match"),
                () -> assertEquals(genreEntity.slug(), result.getSlug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null GenreEntity returns null Genre")
    void toGenreNull() {
        Genre result = GenreMapper.toGenre(null);
        assertNull(result, "Mapping null GenreEntity should return null Genre");
    }

    @Test
    @DisplayName("Test map List of GenreEntity to List of Genre")
    void toGenreList() {
        List<GenreEntity> genreEntities = List.of(
                GenreData.getGenreEntity(0),
                GenreData.getGenreEntity(1)
        );
        List<Genre> result = genreEntities.stream()
                .map(GenreMapper::toGenre)
                .toList();
        assertAll(
                () -> assertEquals(genreEntities.getFirst().nameEs(), result.getFirst().getName("es"), "Names should match"),
                () -> assertEquals(genreEntities.getFirst().slug(), result.getFirst().getSlug(), "Slugs should match"),
                () -> assertEquals(genreEntities.getLast().nameEn(), result.get(1).getName("en"), "Names should match"),
                () -> assertEquals(genreEntities.getLast().slug(), result.get(1).getSlug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test map empty List of GenreEntity to empty List of Genre")
    void toGenreListEmpty() {
        List<GenreEntity> genreEntities = List.of();
        List<Genre> result = genreEntities.stream()
                .map(GenreMapper::toGenre)
                .toList();
        assertTrue(result.isEmpty(), "Mapping empty List of GenreEntity should return empty List of Genre");
    }

    @Test
    @DisplayName("Test map Genre to GenreQuery with Locale es")
    void toGenreQuery() {
        when(mockLocaleProvider.getLanguage()).thenReturn("es");

        Genre genre = GenreData.getGenre(0);
        GenreQuery result = GenreMapper.toGenreQuery(genre);
        assertAll(
                () -> assertEquals(genre.getName("es"), result.name(), "Names should match"),
                () -> assertEquals(genre.getSlug(), result.slug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test map Genre to GenreQuery with Locale en")
    void toGenreQueryEn() {
        when(mockLocaleProvider.getLanguage()).thenReturn("en");

        Genre genre = GenreData.getGenre(0);
        GenreQuery result = GenreMapper.toGenreQuery(genre);
        assertAll(
                () -> assertEquals(genre.getName("en"), result.name(), "Names should match"),
                () -> assertEquals(genre.getSlug(), result.slug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null Genre returns null GenreQuery")
    void toGenreQueryNull() {
        GenreQuery result = GenreMapper.toGenreQuery(null);
        assertNull(result, "Mapping null Genre should return null GenreDto");
    }

    @Test
    @DisplayName("Test map List of Genre to List of GenreQuery with Locale es")
    void toGenreQueryList() {
        when(mockLocaleProvider.getLanguage()).thenReturn("es");

        List<Genre> genres = List.of(
                GenreData.getGenre(0),
                GenreData.getGenre(1)
        );
        List<GenreQuery> result = genres.stream()
                .map(GenreMapper::toGenreQuery)
                .toList();
        assertAll(
                () -> assertEquals(genres.getFirst().getName("es"), result.getFirst().name(), "Names should match"),
                () -> assertEquals(genres.getFirst().getSlug(), result.getFirst().slug(), "Slugs should match"),
                () -> assertEquals(genres.getLast().getName("en"), result.getLast().name(), "Names should match"),
                () -> assertEquals(genres.getLast().getSlug(), result.getLast().slug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test map List of Genre to List of GenreQuery with Locale en")
    void toGenreQueryListEn() {
        when(mockLocaleProvider.getLanguage()).thenReturn("en");

        List<Genre> genres = List.of(
                GenreData.getGenre(0),
                GenreData.getGenre(1)
        );
        List<GenreQuery> result = genres.stream()
                .map(GenreMapper::toGenreQuery)
                .toList();
        assertAll(
                () -> assertEquals(genres.getFirst().getName("es"), result.getFirst().name(), "Names should match"),
                () -> assertEquals(genres.getFirst().getSlug(), result.getFirst().slug(), "Slugs should match"),
                () -> assertEquals(genres.getLast().getName("es"), result.getLast().name(), "Names should match"),
                () -> assertEquals(genres.getLast().getSlug(), result.getLast().slug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test map empty List of Genre to empty List of GenreQuery")
    void toGenreQueryListEmpty() {
        List<Genre> genres = List.of();
        List<GenreQuery> result = genres.stream()
                .map(GenreMapper::toGenreQuery)
                .toList();
        assertTrue(result.isEmpty(), "Mapping empty List of Genre should return empty List of GenreDto");
    }

}