package es.cesguiro.usecase.book.query.data;

import es.cesguiro.model.Book;
import es.cesguiro.model.vo.LocaleString;
import es.cesguiro.repository.model.BookEntity;
import es.cesguiro.usecase.book.query.model.BookCollectionQuery;
import es.cesguiro.usecase.book.query.model.BookQuery;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BookData {

    private static final List<BookEntity> bookEntities = List.of(
            new BookEntity("isbn1", "titleEs1", "titleEn1", "synopsisEs1", "synopsisEn1", new BigDecimal("29.99"), 10, "cover1", LocalDate.of(2023, 1, 1)),
            new BookEntity("isbn2", "titleEs2", "titleEn2", "synopsisEs2", "synopsisEn2", new BigDecimal("19.99"), 20, "cover2", LocalDate.of(2023, 2, 1)),
            new BookEntity("isbn3", "titleEs3", "titleEn3", "synopsisEs3", "synopsisEn3", new BigDecimal("9.99"), 15, "cover3", LocalDate.of(2023, 3, 1)),
            new BookEntity("isbn4", "titleEs4", "titleEn4", "synopsisEs4", "synopsisEn4", new BigDecimal("39.99"), 7, "cover4", LocalDate.of(2023, 4, 1))
    );

    private static final List<Book> books = List.of(
            new Book("isbn1", new LocaleString("titleEs1", "titleEn1"), new LocaleString("synopsisEs1", "synopsisEn1"), new BigDecimal("29.99"), 10, "cover1", LocalDate.of(2023, 1, 1)),
            new Book("isbn2", new LocaleString("titleEs2", "titleEn2"), new LocaleString("synopsisEs2", "synopsisEn2"), new BigDecimal("19.99"), 20, "cover2", LocalDate.of(2023, 2, 1)),
            new Book("isbn3", new LocaleString("titleEs3", "titleEn3"), new LocaleString("synopsisEs3", "synopsisEn3"), new BigDecimal("9.99"), 15, "cover3", LocalDate.of(2023, 3, 1)),
            new Book("isbn4", new LocaleString("titleEs4", "titleEn4"), new LocaleString("synopsisEs4", "synopsisEn4"), new BigDecimal("39.99"), 7, "cover4", LocalDate.of(2023, 4, 1))
    );

    private static final List<BookCollectionQuery> bookCollectionQueriesEs = List.of(
            new BookCollectionQuery("isbn1", "titleEs1", new BigDecimal("29.99"), 10, new BigDecimal("26.99"), "cover1", List.of(AuthorData.getAuthorCollectionQuery(0))),
            new BookCollectionQuery("isbn2", "titleEs2", new BigDecimal("19.99"), 20, new BigDecimal("15.99"), "cover2", List.of(AuthorData.getAuthorCollectionQuery(1))),
            new BookCollectionQuery("isbn3", "titleEs3", new BigDecimal("9.99"), 15, new BigDecimal("6.99"), "cover3", List.of(AuthorData.getAuthorCollectionQuery(0), AuthorData.getAuthorCollectionQuery(1))),
            new BookCollectionQuery("isbn4", "titleEs4", new BigDecimal("39.99"), 7, new BigDecimal("23.99"), "cover4", List.of(AuthorData.getAuthorCollectionQuery(2), AuthorData.getAuthorCollectionQuery(3)))
    );

    private static final List<BookCollectionQuery> bookCollectionQueriesEn = List.of(
            new BookCollectionQuery("isbn1", "titleEn1", new BigDecimal("29.99"), 10, new BigDecimal("26.99"), "cover1", List.of(AuthorData.getAuthorCollectionQuery(0))),
            new BookCollectionQuery("isbn2", "titleEn2", new BigDecimal("19.99"), 20, new BigDecimal("15.99"), "cover2", List.of(AuthorData.getAuthorCollectionQuery(1))),
            new BookCollectionQuery("isbn3", "titleEn3", new BigDecimal("9.99"), 15, new BigDecimal("6.99"), "cover3", List.of(AuthorData.getAuthorCollectionQuery(0), AuthorData.getAuthorCollectionQuery(1))),
            new BookCollectionQuery("isbn4", "titleEn4", new BigDecimal("39.99"), 7, new BigDecimal("23.99"), "cover4", List.of(AuthorData.getAuthorCollectionQuery(2), AuthorData.getAuthorCollectionQuery(3)))
    );

    private static final List<BookQuery> bookQueryEs = List.of(
            new BookQuery(
                    "isbn1",
                    "titleEs1",
                    "synopsisEs1",
                    new BigDecimal("29.99"),
                    10,
                    new BigDecimal("26.99"),
                    "cover1",
                    LocalDate.of(2023, 1, 1),
                    PublisherData.getPublisherQuery(0),
                    CategoryData.getCategoryQueryEs(0),
                    List.of(GenreData.getGenreQueryEs(0), GenreData.getGenreQueryEs(1)),
                    List.of(AuthorData.getAuthorQuery(0))),
            new BookQuery(
                    "isbn2",
                    "titleEs2",
                    "synopsisEs2",
                    new BigDecimal("19.99"),
                    20,
                    new BigDecimal("15.99"),
                    "cover2",
                    LocalDate.of(2023, 2, 1),
                    PublisherData.getPublisherQuery(1),
                    CategoryData.getCategoryQueryEs(1),
                    List.of(GenreData.getGenreQueryEs(1), GenreData.getGenreQueryEs(2)),
                    List.of(AuthorData.getAuthorQuery(1))),
            new BookQuery(
                    "isbn3",
                    "titleEs3",
                    "synopsisEs3",
                    new BigDecimal("9.99"),
                    15,
                    new BigDecimal("8.49"),
                    "cover3",
                    LocalDate.of(2023, 3, 1),
                    PublisherData.getPublisherQuery(2),
                    CategoryData.getCategoryQueryEs(2),
                    List.of(GenreData.getGenreQueryEs(2), GenreData.getGenreQueryEs(3)),
                    List.of(AuthorData.getAuthorQuery(0), AuthorData.getAuthorQuery(1))),
            new BookQuery(
                    "isbn4",
                    "titleEs4",
                    "synopsisEs4",
                    new BigDecimal("39.99"),
                    7,
                    new BigDecimal("37.20"),
                    "cover4",
                    LocalDate.of(2023, 4, 1),
                    PublisherData.getPublisherQuery(3),
                    CategoryData.getCategoryQueryEs(3),
                    List.of(GenreData.getGenreQueryEs(3), GenreData.getGenreQueryEs(0)),
                    List.of(AuthorData.getAuthorQuery(2), AuthorData.getAuthorQuery(3))
            )
    );

    private static final List<BookQuery> bookQueryEn = List.of(
            new BookQuery(
                    "isbn1",
                    "titleEn1",
                    "synopsisEn1",
                    new BigDecimal("29.99"),
                    0.1,
                    new BigDecimal("26.99"),
                    "cover1",
                    LocalDate.of(2023, 1, 1),
                    PublisherData.getPublisherQuery(0),
                    CategoryData.getCategoryQueryEn(0),
                    List.of(GenreData.getGenreQueryEn(0), GenreData.getGenreQueryEn(1)),
                    List.of(AuthorData.getAuthorQuery(0))),
            new BookQuery(
                    "isbn2",
                    "titleEn2",
                    "synopsisEn2",
                    new BigDecimal("19.99"),
                    0.2,
                    new BigDecimal("15.99"),
                    "cover2",
                    LocalDate.of(2023, 2, 1),
                    PublisherData.getPublisherQuery(1),
                    CategoryData.getCategoryQueryEn(1),
                    List.of(GenreData.getGenreQueryEn(1), GenreData.getGenreQueryEn(2)),
                    List.of(AuthorData.getAuthorQuery(1))),
            new BookQuery(
                    "isbn3",
                    "titleEn3",
                    "synopsisEn3",
                    new BigDecimal("9.99"),
                    0.3,
                    new BigDecimal("6.99"),
                    "cover3",
                    LocalDate.of(2023, 3, 1),
                    PublisherData.getPublisherQuery(2),
                    CategoryData.getCategoryQueryEn(2),
                    List.of(GenreData.getGenreQueryEn(2), GenreData.getGenreQueryEn(3)),
                    List.of(AuthorData.getAuthorQuery(0), AuthorData.getAuthorQuery(1))),
            new BookQuery(
                    "isbn4",
                    "titleEn4",
                    "synopsisEn4",
                    new BigDecimal("39.99"),
                    0.4,
                    new BigDecimal("23.99"),
                    "cover4",
                    LocalDate.of(2023, 4, 1),
                    PublisherData.getPublisherQuery(3),
                    CategoryData.getCategoryQueryEn(3),
                    List.of(GenreData.getGenreQueryEn(3), GenreData.getGenreQueryEn(0)),
                    List.of(AuthorData.getAuthorQuery(2), AuthorData.getAuthorQuery(3)))
    );

    public static List<BookEntity> getBookEntities() {
        return List.copyOf(bookEntities);
    }

    public static BookEntity getBookEntity(int position) {
        return bookEntities.get(position);
    }

    public static List<Book> getBooks() {
        return List.copyOf(books);
    }

    public static Book getBook(int position) {
        return books.get(position);
    }

    public static List<BookCollectionQuery> getBookCollectionQueriesEs() {
        return List.copyOf(bookCollectionQueriesEs);
    }

    public static BookCollectionQuery getBookCollectionQueryEs(int position) {
        return bookCollectionQueriesEs.get(position);
    }

    public static List<BookCollectionQuery> getBookCollectionQueriesEn() {
        return List.copyOf(bookCollectionQueriesEn);
    }

    public static BookCollectionQuery getBookCollectionQueryEn(int position) {
        return bookCollectionQueriesEn.get(position);
    }

    public static List<BookQuery> getBookQueriesEs() {
        return List.copyOf(bookQueryEs);
    }

    public static BookQuery getBookQueryEs(int position) {
        return bookQueryEs.get(position);
    }

    public static List<BookQuery> getBookQueriesEn() {
        return List.copyOf(bookQueryEn);
    }

    public static BookQuery getBookQueryEn(int position) {
        return bookQueryEn.get(position);
    }
}
