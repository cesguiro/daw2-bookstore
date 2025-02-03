package es.cesguiro.model.book.query.mapper;

import es.cesguiro.data.CategoryData;
import es.cesguiro.model.book.query.CategoryResponse;
import es.cesguiro.usecase.book.query.model.CategoryQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryMapperTest {

    @Test
    @DisplayName("Test toCategoryResponse should return CategoryResponse")
    void testToCategoryResponse() {

        CategoryQuery categoryQuery = CategoryData.getCategoryQueryEs(1);

        CategoryResponse expected = CategoryData.getCategoryResponseEs(1);
        CategoryResponse result = CategoryMapper.toCategoryResponse(categoryQuery);

        assertAll(
                () -> assertEquals(expected.name(), result.name()),
                () -> assertEquals(expected.links().size(), result.links().size()),
                () -> assertEquals(expected.links().get("books"), result.links().get("books"))
        );
    }

    @Test
    @DisplayName("Test toCategoryResponse should return null")
    void testToCategoryResponseNull() {

        CategoryQuery categoryQuery = null;

        CategoryResponse result = CategoryMapper.toCategoryResponse(categoryQuery);

        assertEquals(null, result);
    }

}