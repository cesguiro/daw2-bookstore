package es.cesguiro.data;

import es.cesguiro.model.book.query.GenreResponse;
import es.cesguiro.usecase.book.query.model.GenreQuery;

import java.util.List;
import java.util.Map;

public class GenreData {

    private static final String BASE_LINK = "http://localhost:8080/api/genres";

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

    private static final List<GenreResponse> genreResponsesEs = List.of(
            new GenreResponse("genreEs1", Map.of("books", BASE_LINK + "/genre1/books")),
            new GenreResponse("genreEs2", Map.of("books", BASE_LINK + "/genre2/books")),
            new GenreResponse("genreEs3", Map.of("books", BASE_LINK + "/genre3/books")),
            new GenreResponse("genreEs4", Map.of("books", BASE_LINK + "/genre4/books"))
    );

    private static final List<GenreResponse> genreResponsesEn = List.of(
            new GenreResponse("genreEn1", Map.of("books", BASE_LINK + "/genre1/books")),
            new GenreResponse("genreEn2", Map.of("books", BASE_LINK + "/genre2/books")),
            new GenreResponse("genreEn3", Map.of("books", BASE_LINK + "/genre3/books")),
            new GenreResponse("genreEn4", Map.of("books", BASE_LINK + "/genre4/books"))
    );

    public static List<GenreQuery> getGenreQueriesEs() {
        return genreQueriesEs;
    }

    public static GenreQuery getGenreQueryEs(int index) {
        return genreQueriesEs.get(index);
    }

    public static List<GenreQuery> getGenreQueriesEn() {
        return genreQueriesEn;
    }

    public static GenreQuery getGenreQueryEn(int index) {
        return genreQueriesEn.get(index);
    }

    public static List<GenreResponse> getGenreResponsesEs() {
        return genreResponsesEs;
    }

    public static GenreResponse getGenreResponseEs(int index) {
        return genreResponsesEs.get(index);
    }

    public static List<GenreResponse> getGenreResponsesEn() {
        return genreResponsesEn;
    }

    public static GenreResponse getGenreResponseEn(int index) {
        return genreResponsesEn.get(index);
    }

}
