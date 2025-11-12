package es.cesguiro.daw2_bookstore.controller.webModel.request;

public record BookInsertRequest(
        String isbn,
        String titleEs,
        String titleEn,
        String synopsisEs,
        String synopsisEn,
        String cover,
        String publicationDate,
        Double basePrice,
        Double discountPercentage,
        Long publisherId,
        Long[] authorIds
) {
}
