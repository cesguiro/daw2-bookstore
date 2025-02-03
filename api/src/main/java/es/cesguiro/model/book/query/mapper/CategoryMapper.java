package es.cesguiro.model.book.query.mapper;

import es.cesguiro.handler.BookHandler;
import es.cesguiro.handler.CategoryHandler;
import es.cesguiro.model.book.query.CategoryResponse;
import es.cesguiro.property.PropertyUtil;
import es.cesguiro.usecase.book.query.model.CategoryQuery;

import java.util.Map;

public class CategoryMapper {

    private CategoryMapper() {
    }

    public static CategoryResponse toCategoryResponse(CategoryQuery categoryQuery) {
        if (categoryQuery == null) {
            return null;
        }
        return new CategoryResponse(
                categoryQuery.name(),
                Map.of("books", PropertyUtil.getInstance().getProperty("app.base.url")
                                + CategoryHandler.RESOURCE_PATH + "/"
                                + categoryQuery.slug() + "/"
                                + BookHandler.RESOURCE
                )
        );
    }
}
