package es.cesguiro.model.book.query.mapper;

import es.cesguiro.handler.impl.AuthorHandlerImpl;
import es.cesguiro.handler.impl.BookHandlerImpl;
import es.cesguiro.model.book.query.AuthorCollectionResponse;
import es.cesguiro.model.book.query.AuthorReponse;
import es.cesguiro.property.PropertyUtil;
import es.cesguiro.usecase.book.query.model.AuthorCollectionQuery;
import es.cesguiro.usecase.book.query.model.AuthorQuery;

import java.util.Map;

public class AuthorMapper {

    private AuthorMapper() {
    }

    public static AuthorCollectionResponse toAuthorCollectionResponse(AuthorCollectionQuery authorCollectionQuery) {
        if (authorCollectionQuery == null) {
            return null;
        }
        return new AuthorCollectionResponse(
                authorCollectionQuery.name(),
                Map.of("_self", PropertyUtil.getPropertyProvider().getProperty("app.base.url")
                                + AuthorHandlerImpl.RESOURCE_PATH + "/"
                                + authorCollectionQuery.slug(),
                        "books", PropertyUtil.getPropertyProvider().getProperty("app.base.url")
                                + AuthorHandlerImpl.RESOURCE_PATH + "/"
                                + authorCollectionQuery.slug() + "/"
                                + BookHandlerImpl.RESOURCE
                )
        );
    }

    public static AuthorReponse toAuthorResponse(AuthorQuery authorQuery) {
        if (authorQuery == null) {
            return null;
        }
        return new AuthorReponse(
                authorQuery.name(),
                Map.of("_self", PropertyUtil.getPropertyProvider().getProperty("app.base.url")
                                + AuthorHandlerImpl.RESOURCE_PATH + "/"
                                + authorQuery.slug(),
                        "books", PropertyUtil.getPropertyProvider().getProperty("app.base.url")
                                + AuthorHandlerImpl.RESOURCE_PATH + "/"
                                + authorQuery.slug() + "/"
                                + BookHandlerImpl.RESOURCE
                )
        );
    }
}
