package es.cesguiro.model.book.query;

import java.util.Map;

public record GenreResponse (
        String name,
        Map<String, String> links
) {
}
