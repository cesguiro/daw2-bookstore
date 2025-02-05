package es.cesguiro.controller.data;

import es.cesguiro.model.book.query.BookCollectionResponse;
import es.cesguiro.model.book.query.BookResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class BookData {

    private static final String BASE_LINK = "http://localhost:8080/api/books";

    private static List<BookCollectionResponse> bookCollectionResponsesEs = List.of(
            new BookCollectionResponse(
                    "isbn1",
                    "titleEs1",
                    new BigDecimal("29.99"),
                    10,
                    new BigDecimal("26.99"),
                    "cover1",
                    List.of(AuthorData.getAuthorCollectionResponse(2)),
                    Map.of("_self", BASE_LINK + "/isbn1")
            ),
            new BookCollectionResponse(
                    "isbn2",
                    "titleEs2",
                    new BigDecimal("19.99"),
                    20,
                    new BigDecimal("15.99"),
                    "cover2",
                    List.of(AuthorData.getAuthorCollectionResponse(0), AuthorData.getAuthorCollectionResponse(3)),
                    Map.of("_self", BASE_LINK + "/isbn2")
            ),
            new BookCollectionResponse(
                    "isbn3",
                    "titleEs3",
                    new BigDecimal("9.99"),
                    15,
                    new BigDecimal("8.49"),
                    "cover3",
                    List.of(AuthorData.getAuthorCollectionResponse(1), AuthorData.getAuthorCollectionResponse(2)),
                    Map.of("_self", BASE_LINK + "/isbn3")
            ),
            new BookCollectionResponse(
                    "isbn4",
                    "titleEs4",
                    new BigDecimal("39.99"),
                    7,
                    new BigDecimal("37.20"),
                    "cover4",
                    List.of(AuthorData.getAuthorCollectionResponse(0), AuthorData.getAuthorCollectionResponse(2), AuthorData.getAuthorCollectionResponse(3)),
                    Map.of("_self", BASE_LINK + "/isbn4")
            ),
            new BookCollectionResponse(
                    "isbn5",
                    "titleEs5",
                    new BigDecimal("49.99"),
                    5,
                    new BigDecimal("47.49"),
                    "cover5",
                    List.of(AuthorData.getAuthorCollectionResponse(1), AuthorData.getAuthorCollectionResponse(3)),
                    Map.of("_self", BASE_LINK + "/isbn5")
            )
    );

    private static final List<BookCollectionResponse> bookCollectionReponsesEn = List.of(
            new BookCollectionResponse(
                    "isbn1",
                    "titleEn1",
                    new BigDecimal("29.99"),
                    10,
                    new BigDecimal("26.99"),
                    "cover1",
                    List.of(AuthorData.getAuthorCollectionResponse(2)),
                    Map.of("_self", BASE_LINK + "/isbn1")
            ),
            new BookCollectionResponse(
                    "isbn2",
                    "titleEn2",
                    new BigDecimal("19.99"),
                    20,
                    new BigDecimal("15.99"),
                    "cover2",
                    List.of(AuthorData.getAuthorCollectionResponse(0), AuthorData.getAuthorCollectionResponse(3)),
                    Map.of("_self", BASE_LINK + "/isbn2")
            ),
            new BookCollectionResponse(
                    "isbn3",
                    "titleEn3",
                    new BigDecimal("9.99"),
                    15,
                    new BigDecimal("8.49"),
                    "cover3",
                    List.of(AuthorData.getAuthorCollectionResponse(1), AuthorData.getAuthorCollectionResponse(2)),
                    Map.of("_self", BASE_LINK + "/isbn3")
            ),
            new BookCollectionResponse(
                    "isbn4",
                    "titleEn4",
                    new BigDecimal("39.99"),
                    7,
                    new BigDecimal("37.20"),
                    "cover4",
                    List.of(AuthorData.getAuthorCollectionResponse(0), AuthorData.getAuthorCollectionResponse(2), AuthorData.getAuthorCollectionResponse(3)),
                    Map.of("_self", BASE_LINK + "/isbn4")
            ),
            new BookCollectionResponse(
                    "isbn5",
                    "titleEn5",
                    new BigDecimal("49.99"),
                    5,
                    new BigDecimal("47.49"),
                    "cover5",
                    List.of(AuthorData.getAuthorCollectionResponse(1), AuthorData.getAuthorCollectionResponse(3)),
                    Map.of("_self", BASE_LINK + "/isbn5")
            )
    );

    private static List<BookResponse> bookResponsesEs = List.of(
            new BookResponse(
                    "isbn1",
                    "titleEs1",
                    "synopsisEs1",
                    new BigDecimal("29.99"),
                    10,
                    new BigDecimal("26.99"),
                    "cover1",
                    "01/01/2023",
                    PublisherData.getPublisherResponse(1),
                    CategoryData.getCategoryResponseEs(0),
                    List.of(GenreData.getGenreResponseEs(1), GenreData.getGenreResponseEs(2)),
                    List.of(AuthorData.getAuthorReponse(2))
            ),
            new BookResponse(
                    "isbn2",
                    "titleEs2",
                    "synopsisEs2",
                    new BigDecimal("19.99"),
                    20,
                    new BigDecimal("15.99"),
                    "cover2",
                    "01/02/2023",
                    PublisherData.getPublisherResponse(0),
                    CategoryData.getCategoryResponseEs(2),
                    List.of(GenreData.getGenreResponseEs(0)),
                    List.of(AuthorData.getAuthorReponse(0), AuthorData.getAuthorReponse(3))
            ),
            new BookResponse(
                    "isbn3",
                    "titleEs3",
                    "synopsisEs3",
                    new BigDecimal("9.99"),
                    15,
                    new BigDecimal("8.49"),
                    "cover3",
                    "01/03/2023",
                    PublisherData.getPublisherResponse(1),
                    CategoryData.getCategoryResponseEs(0),
                    List.of(GenreData.getGenreResponseEs(2), GenreData.getGenreResponseEs(3)),
                    List.of(AuthorData.getAuthorReponse(1), AuthorData.getAuthorReponse(2))
            ),
            new BookResponse(
                    "isbn4",
                    "titleEs4",
                    "synopsisEs4",
                    new BigDecimal("39.99"),
                    7,
                    new BigDecimal("37.20"),
                    "cover4",
                    "01/04/2023",
                    PublisherData.getPublisherResponse(2),
                    CategoryData.getCategoryResponseEs(3),
                    List.of(GenreData.getGenreResponseEs(1), GenreData.getGenreResponseEs(2), GenreData.getGenreResponseEs(3)),
                    List.of(AuthorData.getAuthorReponse(0), AuthorData.getAuthorReponse(2), AuthorData.getAuthorReponse(3))
            ),
            new BookResponse(
                    "isbn5",
                    "titleEs5",
                    "synopsisEs5",
                    new BigDecimal("49.99"),
                    5,
                    new BigDecimal("47.49"),
                    "cover5",
                    "01/05/2023",
                    PublisherData.getPublisherResponse(3),
                    CategoryData.getCategoryResponseEs(1),
                    List.of(GenreData.getGenreResponseEs(2)),
                    List.of(AuthorData.getAuthorReponse(1), AuthorData.getAuthorReponse(3))
            )
    );

    private static final List<BookResponse> bookResponsesEn = List.of(
            new BookResponse(
                    "isbn1",
                    "titleEn1",
                    "synopsisEn1",
                    new BigDecimal("29.99"),
                    10,
                    new BigDecimal("26.99"),
                    "cover1",
                    "01/01/2023",
                    PublisherData.getPublisherResponse(1),
                    CategoryData.getCategoryResponseEn(0),
                    List.of(GenreData.getGenreResponseEn(1), GenreData.getGenreResponseEn(2)),
                    List.of(AuthorData.getAuthorReponse(2))
            ),
            new BookResponse(
                    "isbn2",
                    "titleEn2",
                    "synopsisEn2",
                    new BigDecimal("19.99"),
                    20,
                    new BigDecimal("15.99"),
                    "cover2",
                    "01/02/2023",
                    PublisherData.getPublisherResponse(0),
                    CategoryData.getCategoryResponseEn(2),
                    List.of(GenreData.getGenreResponseEn(0)),
                    List.of(AuthorData.getAuthorReponse(0), AuthorData.getAuthorReponse(3))
            ),
            new BookResponse(
                    "isbn3",
                    "titleEn3",
                    "synopsisEn3",
                    new BigDecimal("9.99"),
                    15,
                    new BigDecimal("8.49"),
                    "cover3",
                    "01/03/2023",
                    PublisherData.getPublisherResponse(1),
                    CategoryData.getCategoryResponseEn(0),
                    List.of(GenreData.getGenreResponseEn(2), GenreData.getGenreResponseEn(3)),
                    List.of(AuthorData.getAuthorReponse(1), AuthorData.getAuthorReponse(2))
            ),
            new BookResponse(
                    "isbn4",
                    "titleEn4",
                    "synopsisEn4",
                    new BigDecimal("39.99"),
                    7,
                    new BigDecimal("37.20"),
                    "cover4",
                    "01/04/2023",
                    PublisherData.getPublisherResponse(2),
                    CategoryData.getCategoryResponseEn(3),
                    List.of(GenreData.getGenreResponseEn(1), GenreData.getGenreResponseEn(2), GenreData.getGenreResponseEn(3)),
                    List.of(AuthorData.getAuthorReponse(0), AuthorData.getAuthorReponse(2), AuthorData.getAuthorReponse(3))
            ),
            new BookResponse(
                    "isbn5",
                    "titleEn5",
                    "synopsisEn5",
                    new BigDecimal("49.99"),
                    5,
                    new BigDecimal("47.49"),
                    "cover5",
                    "01/05/2023",
                    PublisherData.getPublisherResponse(3),
                    CategoryData.getCategoryResponseEn(1),
                    List.of(GenreData.getGenreResponseEn(2)),
                    List.of(AuthorData.getAuthorReponse(1), AuthorData.getAuthorReponse(3))
            )
    );

    public static List<BookCollectionResponse> getBookCollectionResponsesEs() {
        return bookCollectionResponsesEs;
    }

    public static BookCollectionResponse getBookCollectionResponseEs(int index) {
        return bookCollectionResponsesEs.get(index);
    }

    public static List<BookCollectionResponse> getBookCollectionReponsesEn() {
        return bookCollectionReponsesEn;
    }

    public static BookCollectionResponse getBookCollectionReponseEn(int index) {
        return bookCollectionReponsesEn.get(index);
    }

    public static List<BookResponse> getBookResponsesEs() {
        return bookResponsesEs;
    }

    public static BookResponse getBookResponseEs(int index) {
        return bookResponsesEs.get(index);
    }

    public static List<BookResponse> getBookResponsesEn() {
        return bookResponsesEn;
    }

    public static BookResponse getBookResponseEn(int index) {
        return bookResponsesEn.get(index);
    }

}
