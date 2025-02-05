package es.cesguiro.controller.data;

import es.cesguiro.model.book.query.GenreResponse;

import java.util.List;
import java.util.Map;

public class GenreData {

    private static final String BASE_LINK = "http://localhost:8080/api/genres";

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
