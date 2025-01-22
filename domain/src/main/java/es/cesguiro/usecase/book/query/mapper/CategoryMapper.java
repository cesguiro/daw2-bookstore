package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.model.Category;
import es.cesguiro.repository.model.CategoryEntity;
import es.cesguiro.usecase.book.query.model.CategoryDto;

public class CategoryMapper {

    public static Category toCategory(CategoryEntity categoryEntity) {
        if(categoryEntity == null) {
            return null;
        }
        return new Category(
                categoryEntity.name(),
                categoryEntity.slug()
        );
    }

    public static CategoryDto toCategoryDto(Category category) {
        if(category == null){
            return null;
        }
        return new CategoryDto(
                category.getName(),
                category.getSlug()
        );
    }
}
