package es.cesguiro.usecase.book.query.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record BookDto(
        String isbn,
        String title,
        String synopsis,
        BigDecimal basePrice,
        double discount,
        BigDecimal finalPrice,
        String cover,
        LocalDate publicationDate,
        PublisherDto publisher,
        CategoryDto category,
        List<GenreDto> genres,
        List<AuthorDto> authors

) {
}
