package es.cesguiro.model.book.query.mapper;

import es.cesguiro.handler.BookHandler;
import es.cesguiro.handler.GenreHandler;
import es.cesguiro.model.book.query.GenreResponse;
import es.cesguiro.property.PropertyUtil;
import es.cesguiro.usecase.book.query.model.GenreQuery;

import java.util.Map;

public class GenreMapper {

    private GenreMapper() {
    }

    public static GenreResponse toGenreResponse(GenreQuery genreQuery) {
        if (genreQuery == null) {
            return null;
        }
        return new GenreResponse(
                genreQuery.name(),
                Map.of("books", PropertyUtil.getInstance().getProperty("app.base.url")
                                + GenreHandler.RESOURCE_PATH + "/"
                                + genreQuery.slug() + "/"
                                + BookHandler.RESOURCE
                )
        );
    }
}
