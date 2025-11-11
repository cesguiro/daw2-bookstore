package es.cesguiro.daw2_bookstore.controller.webModel.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record BookDetailResponse(
        String isbn,
        String titleEs,
        String titleEn,
        String synopsisEs,
        String synopsisEn,
        BigDecimal basePrice,
        BigDecimal discountPercentage,
        BigDecimal price,
        String cover,
        LocalDate publicationDate,
        PublisherSummaryResponse publisher,
        List<AuthorSummaryResponse> authors
)
{}
