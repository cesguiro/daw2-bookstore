package es.cesguiro.repository.data;

import es.cesguiro.dao.jpa.entity.BookEntityJpa;
import es.cesguiro.repository.model.BookEntity;

import java.math.BigDecimal;
import java.util.List;

public class BookData {

    private static final List<BookEntityJpa> bookEntityJpas = List.of(
            new BookEntityJpa(
                    1L,
                    "123",
                    "TitleEs 1",
                    "TitleEn 1",
                    "SynopsisEs 1",
                    "SynopsisEn 1",
                    new BigDecimal("10.0"),
                    5.0,
                    "cover 1",
                    null
            ),
            new BookEntityJpa(
                    2L,
                    "456",
                    "TitleEs 2",
                    "TitleEn 2",
                    "SynopsisEs 2",
                    "SynopsisEn 2",
                    new BigDecimal("20.0"),
                    4.0,
                    "cover 2",
                    null
            ),
            new BookEntityJpa(
                    3L,
                    "789",
                    "TitleEs 3",
                    "TitleEn 3",
                    "SynopsisEs 3",
                    "SynopsisEn 3",
                    new BigDecimal("30.0"),
                    3.0,
                    "cover 3",
                    null
            ),
            new BookEntityJpa(
                    4L,
                    "012",
                    "TitleEs 4",
                    "TitleEn 4",
                    "SynopsisEs 4",
                    "SynopsisEn 4",
                    new BigDecimal("40.0"),
                    2.0,
                    "cover 4",
                    null
            ),
            new BookEntityJpa(
                    5L,
                    "345",
                    "TitleEs 5",
                    "TitleEn 5",
                    "SynopsisEs 5",
                    "SynopsisEn 5",
                    new BigDecimal("50.0"),
                    1.0,
                    "cover 5",
                    null
            )
    );

    private static final List<BookEntity> bookEntities = List.of(
            new BookEntity(
                    "123",
                    "TitleEs 1",
                    "TitleEn 1",
                    "SynopsisEs 1",
                    "SynopsisEn 1",
                    new BigDecimal("10.0"),
                    5.0,
                    "cover 1",
                    null
            ),
            new BookEntity(
                    "456",
                    "TitleEs 2",
                    "TitleEn 2",
                    "SynopsisEs 2",
                    "SynopsisEn 2",
                    new BigDecimal("20.0"),
                    4.0,
                    "cover 2",
                    null
            ),
            new BookEntity(
                    "789",
                    "TitleEs 3",
                    "TitleEn 3",
                    "SynopsisEs 3",
                    "SynopsisEn 3",
                    new BigDecimal("30.0"),
                    3.0,
                    "cover 3",
                    null
            ),
            new BookEntity(
                    "012",
                    "TitleEs 4",
                    "TitleEn 4",
                    "SynopsisEs 4",
                    "SynopsisEn 4",
                    new BigDecimal("40.0"),
                    2.0,
                    "cover 4",
                    null
            ),
            new BookEntity(
                    "345",
                    "TitleEs 5",
                    "TitleEn 5",
                    "SynopsisEs 5",
                    "SynopsisEn 5",
                    new BigDecimal("50.0"),
                    1.0,
                    "cover 5",
                    null
            )
    );

    public static List<BookEntityJpa> getBookEntityJpas() {
        return bookEntityJpas;
    }

    public static BookEntityJpa getBookEntityJpa(int position) {
        return bookEntityJpas.get(position);
    }

    public static List<BookEntity> getBookEntities() {
        return bookEntities;
    }

    public static BookEntity getBookEntity(int position) {
        return bookEntities.get(position);
    }

}
