package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.model.Category;
import es.cesguiro.repository.model.CategoryEntity;
import es.cesguiro.usecase.book.query.model.CategoryQuery;

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

    public static CategoryQuery toCategoryDto(Category category) {
        if(category == null){
            return null;
        }
        return new CategoryQuery(
                category.getName(),
                category.getSlug()
        );
    }
}
