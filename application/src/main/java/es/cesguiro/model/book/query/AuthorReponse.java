package es.cesguiro.model.book.query;

import java.util.Map;

public record AuthorReponse (
        String name,
        Map<String, String> links
) {
}
