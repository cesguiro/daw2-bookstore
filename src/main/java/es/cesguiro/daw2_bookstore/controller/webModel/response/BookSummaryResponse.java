package es.cesguiro.daw2_bookstore.controller.webModel.response;

import java.math.BigDecimal;

public record BookSummaryResponse(
        String isbn,
        String titleEs,
        String titleEn,
        BigDecimal basePrice,
        BigDecimal discountPercentage,
        BigDecimal price,
        String cover
) {
}
