package es.cesguiro.usecase.book.query.data;

import es.cesguiro.model.Genre;
import es.cesguiro.model.vo.LocaleString;
import es.cesguiro.repository.model.GenreEntity;
import es.cesguiro.usecase.book.query.model.GenreQuery;

import java.util.List;

public class GenreData {

    private static final List<GenreEntity> genreEntities = List.of(
            new GenreEntity("genreEs1", "genreEn1", "genre1"),
            new GenreEntity("genreEs2", "genreEn2", "genre2"),
            new GenreEntity("genreEs3", "genreEn3", "genre3"),
            new GenreEntity("genreEs4", "genreEn4" , "genre4")
    );

    private static final List<Genre> genres = List.of(
            new Genre(new LocaleString("genreEs1", "genreEs1"), "genre1"),
            new Genre(new LocaleString("genreEs2", "genreEs2"), "genre2"),
            new Genre(new LocaleString("genreEs3", "genreEs3"), "genre3"),
            new Genre(new LocaleString("genreEs4", "genreEs4"), "genre4")
    );

    private static final List<GenreQuery> genreQueriesEs = List.of(
            new GenreQuery("genreEs1", "genre1"),
            new GenreQuery("genreEs2", "genre2"),
            new GenreQuery("genreEs3", "genre3"),
            new GenreQuery("genreEs4", "genre4")
    );

    private static final List<GenreQuery> genreQueriesEn = List.of(
            new GenreQuery("genreEn1", "genre1"),
            new GenreQuery("genreEn2", "genre2"),
            new GenreQuery("genreEn3", "genre3"),
            new GenreQuery("genreEn4", "genre4")
    );

    public static List<GenreEntity> getGenreEntities() {
        return List.copyOf(genreEntities);
    }

    public static GenreEntity getGenreEntity(int position) {
        return genreEntities.get(position);
    }

    public static List<Genre> getGenres() {
        return List.copyOf(genres);
    }

    public static Genre getGenre(int position) {
        return genres.get(position);
    }

    public static List<GenreQuery> getGenreQueriesEs() {
        return List.copyOf(genreQueriesEs);
    }

    public static GenreQuery getGenreQueryEs(int position) {
        return genreQueriesEs.get(position);
    }

    public static List<GenreQuery> getGenreQueriesEn() {
        return List.copyOf(genreQueriesEn);
    }

    public static GenreQuery getGenreQueryEn(int position) {
        return genreQueriesEn.get(position);
    }
}
