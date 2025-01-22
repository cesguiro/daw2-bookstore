package es.cesguiro.repository.model;

public record AuthorEntity(
        String name,
        String nationality,
        String biography,
        int birthYear,
        Integer deathYear,
        String slug
) {
}
