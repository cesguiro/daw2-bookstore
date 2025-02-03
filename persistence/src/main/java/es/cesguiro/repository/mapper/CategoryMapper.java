package es.cesguiro.repository.mapper;

import es.cesguiro.dao.jpa.entity.CategoryEntityJpa;
import es.cesguiro.repository.model.CategoryEntity;

public class CategoryMapper {

    private CategoryMapper() {
    }

    public static CategoryEntity toCategoryEntity(CategoryEntityJpa categoryEntityJpa) {
        if (categoryEntityJpa == null) {
            return null;
        }
        return new CategoryEntity(
                categoryEntityJpa.getNameEs(),
                categoryEntityJpa.getNameEn(),
                categoryEntityJpa.getSlug()
        );
    }
}
