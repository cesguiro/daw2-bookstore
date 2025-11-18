package es.cesguiro.daw2_bookstore.controller.webModel.request;

import com.fasterxml.jackson.annotation.JsonFormat;

public record BookUpdateRequest(
        Long id,
        String isbn,
        String titleEs,
        String titleEn,
        String synopsisEs,
        String synopsisEn,
        String cover,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        String publicationDate,
        Double basePrice,
        Double discountPercentage,
        Long publisherId,
        Long[] authorIds
) {
}
