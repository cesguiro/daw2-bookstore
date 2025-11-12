package es.cesguiro.daw2_bookstore.controller.webModel.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record AuthorSummaryResponse(
        String name,
        String slug
) {
}
