package es.cesguiro.repository.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookEntity(
        String isbn,
        String title,
        String synopsis,
        BigDecimal basePrice,
        double discountPercentage,
        String cover,
        LocalDate publicationDate
) {
}
