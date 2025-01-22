package es.cesguiro.usecase.book.query.model;

import java.math.BigDecimal;
import java.util.List;

public record BookCollectionDto(
        String isbn,
        String title,
        BigDecimal basePrice,
        double discount,
        BigDecimal finalPrice,
        String cover,
        List<AuthorCollectionDto> authors
) {}
