package es.cesguiro.repository.data;

import es.cesguiro.repository.model.CategoryEntity;

public class CategoryData {

    private static final CategoryEntity categoryEntity = new CategoryEntity("CategoryEs 1", "CategoryEn 1", "category-1");

    public static CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }
}
