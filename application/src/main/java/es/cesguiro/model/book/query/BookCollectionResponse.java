package es.cesguiro.model.book.query;

import es.cesguiro.usecase.book.query.model.AuthorCollectionQuery;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record BookCollectionResponse(
        String isbn,
        String title,
        BigDecimal basePrice,
        double discount,
        BigDecimal finalPrice,
        String cover,
        List<AuthorCollectionResponse> authors,
        Map<String, String> links
) {
}
