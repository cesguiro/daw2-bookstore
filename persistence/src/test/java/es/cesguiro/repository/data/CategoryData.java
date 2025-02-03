package es.cesguiro.repository.data;

import es.cesguiro.dao.jpa.entity.CategoryEntityJpa;
import es.cesguiro.repository.model.CategoryEntity;

public class CategoryData {

    private static final CategoryEntityJpa categoryEntityJpa = new CategoryEntityJpa(1L, "CategoryEs 1", "CategoryEn 1", "category-1");
    private static final CategoryEntity categoryEntity = new CategoryEntity("CategoryEs 1", "CategoryEn 1", "category-1");

    public static CategoryEntityJpa getCategoryEntityJpa() {
        return categoryEntityJpa;
    }

    public static CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }
}
