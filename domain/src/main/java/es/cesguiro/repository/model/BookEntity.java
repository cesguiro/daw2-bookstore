package es.cesguiro.repository.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookEntity(
        String isbn,
        String titleEs,
        String titleEn,
        String synopsisEs,
        String synopsisEn,
        BigDecimal basePrice,
        double discountPercentage,
        String cover,
        LocalDate publicationDate
) {
}
