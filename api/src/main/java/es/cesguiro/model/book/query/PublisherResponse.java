package es.cesguiro.model.book.query;

import java.util.Map;

public record PublisherResponse (
        String name,
        Map<String, String> links
){
}
