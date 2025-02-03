package es.cesguiro.model.book.query;

import es.cesguiro.property.PropertyUtil;

import java.util.Map;

public record AuthorCollectionResponse(
        String name,
        Map<String, String> links
) {
}
