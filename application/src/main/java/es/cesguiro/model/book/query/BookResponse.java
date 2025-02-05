package es.cesguiro.model.book.query;

import java.math.BigDecimal;
import java.util.List;

public record BookResponse (
        String isbn,
        String title,
        String synopsis,
        BigDecimal basePrice,
        double discount,
        BigDecimal finalPrice,
        String cover,
        String publicationDate,
        PublisherResponse publisher,
        CategoryResponse category,
        List<GenreResponse> genres,
        List<AuthorReponse> authors

) {
}
