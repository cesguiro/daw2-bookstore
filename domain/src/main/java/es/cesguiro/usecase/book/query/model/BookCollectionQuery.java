package es.cesguiro.usecase.book.query.model;

import java.math.BigDecimal;
import java.util.List;

public record BookCollectionQuery(
        String isbn,
        String title,
        BigDecimal basePrice,
        double discount,
        BigDecimal finalPrice,
        String cover,
        List<AuthorCollectionQuery> authors
) {}
