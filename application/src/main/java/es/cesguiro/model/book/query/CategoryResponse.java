package es.cesguiro.model.book.query;

import java.util.Map;

public record CategoryResponse (
        String name,
        Map<String, String> links
) {
}
