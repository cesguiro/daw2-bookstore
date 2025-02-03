package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.locale.LocaleUtil;
import es.cesguiro.model.Category;
import es.cesguiro.model.vo.LocaleString;
import es.cesguiro.repository.model.CategoryEntity;
import es.cesguiro.usecase.book.query.model.CategoryQuery;

public class CategoryMapper {


    public static Category toCategory(CategoryEntity categoryEntity) {
        if(categoryEntity == null) {
            return null;
        }
        return new Category(
                new LocaleString(categoryEntity.nameEs(), categoryEntity.nameEn()),
                categoryEntity.slug()
        );
    }

    public static CategoryQuery toCategoryQuery(Category category) {
        if(category == null){
            return null;
        }
        return new CategoryQuery(
                category.getName(LocaleUtil.getInstance().getLanguage()),
                category.getSlug()
        );
    }
}
