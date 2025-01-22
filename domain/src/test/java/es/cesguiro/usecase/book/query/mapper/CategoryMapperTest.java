package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.model.Category;
import es.cesguiro.repository.model.CategoryEntity;
import es.cesguiro.usecase.book.query.model.CategoryDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    @Test
    @DisplayName("Test map CategoryEntity to Category")
    void toCategory() {
        CategoryEntity categoryEntity = new CategoryEntity("Fantasy", "fantasy");
        Category category = CategoryMapper.toCategory(categoryEntity);
        assertAll(
                () -> assertEquals(categoryEntity.name(), category.getName(), "Names should match"),
                () -> assertEquals(categoryEntity.slug(), category.getSlug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null CategoryEntity returns null Category")
    void toCategoryNull() {
        Category category = CategoryMapper.toCategory(null);
        assertNull(category, "Mapping null CategoryEntity should return null Category");
    }

    @Test
    @DisplayName("Test map Category to CategoryDto")
    void toCategoryDto() {
        Category category = new Category("Fantasy", "fantasy");
        CategoryDto categoryDto = CategoryMapper.toCategoryDto(category);
        assertAll(
                () -> assertEquals(category.getName(), categoryDto.name(), "Names should match"),
                () -> assertEquals(category.getSlug(), categoryDto.slug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null Category returns null CategoryDto")
    void toCategoryDtoNull() {
        CategoryDto categoryDto = CategoryMapper.toCategoryDto(null);
        assertNull(categoryDto, "Mapping null Category should return null CategoryDto");
    }


}