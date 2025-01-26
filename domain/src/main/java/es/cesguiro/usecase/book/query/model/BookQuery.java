package es.cesguiro.usecase.book.query.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record BookQuery(
        String isbn,
        String title,
        String synopsis,
        BigDecimal basePrice,
        double discount,
        BigDecimal finalPrice,
        String cover,
        LocalDate publicationDate,
        PublisherQuery publisher,
        CategoryQuery category,
        List<GenreQuery> genres,
        List<AuthorQuery> authors

) {
}
