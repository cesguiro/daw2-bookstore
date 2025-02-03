package es.cesguiro.model.book.query.mapper;

import es.cesguiro.handler.BookHandler;
import es.cesguiro.handler.PublisherHandler;
import es.cesguiro.model.book.query.PublisherResponse;
import es.cesguiro.property.PropertyUtil;
import es.cesguiro.usecase.book.query.model.PublisherQuery;

import java.util.Map;

public class PublisherMapper {

    private PublisherMapper() {
    }

    public static PublisherResponse toPublisherResponse(PublisherQuery publisherQuery) {
        if (publisherQuery == null) {
            return null;
        }
        return new PublisherResponse(
                publisherQuery.name(),
                Map.of("books", PropertyUtil.getInstance().getProperty("app.base.url")
                                + PublisherHandler.RESOURCE_PATH + "/"
                                + publisherQuery.slug() + "/"
                                + BookHandler.RESOURCE
                )
        );
    }
}
