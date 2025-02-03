package es.cesguiro.repository.mapper;

import es.cesguiro.dao.jpa.entity.CategoryEntityJpa;
import es.cesguiro.repository.model.CategoryEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    @Test
    @DisplayName("Test toCategoryEntity with null should return null")
    void testToCategoryEntityWithNull() {
        assertNull(CategoryMapper.toCategoryEntity(null));
    }

    @Test
    @DisplayName("Test map CategoryEntityJpa to toCategoryEntity")
    void testToCategoryEntityWithLocaleEn() {
        CategoryEntityJpa categoryEntityJpa = new CategoryEntityJpa();
        categoryEntityJpa.setNameEs("Nombre Categoría");
        categoryEntityJpa.setNameEn("Category Name");
        categoryEntityJpa.setSlug("category-name");

        CategoryEntity categoryEntity = CategoryMapper.toCategoryEntity(categoryEntityJpa);

        assertAll(
                () -> assertEquals("Nombre Categoría", categoryEntity.nameEs()),
                () -> assertEquals("Category Name", categoryEntity.nameEn()),
                () -> assertEquals("category-name", categoryEntity.slug())
        );
    }

}