package es.cesguiro.daw2_bookstore.controller.webModel.response;

public record AuthorDetailResponse(
        String name,
        String nationality,
        String biographyEs,
        String biographyEn,
        Integer birthYear,
        Integer deathYear,
        String slug
) {
}
