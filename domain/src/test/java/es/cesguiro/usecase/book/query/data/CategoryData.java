package es.cesguiro.usecase.book.query.data;

import es.cesguiro.model.Category;
import es.cesguiro.model.vo.LocaleString;
import es.cesguiro.repository.model.CategoryEntity;
import es.cesguiro.usecase.book.query.model.CategoryQuery;

import java.util.List;

public class CategoryData {

    private static final List<CategoryEntity> categoryEntities = List.of(
            new CategoryEntity("categoryEs1", "categoryEn1", "category1"),
            new CategoryEntity("categoryEs2", "categoryEn2", "category2"),
            new CategoryEntity("categoryEs3", "categoryEn3", "category3"),
            new CategoryEntity("categoryEs4", "categoryEn4", "category4")
    );

    private static final List<Category> categories = List.of(
            new Category(new LocaleString("categoryEs1", "categoryEn1"), "category1"),
            new Category(new LocaleString("categoryEs2", "categoryEn2"), "category2"),
            new Category(new LocaleString("categoryEs3", "categoryEn3"), "category3"),
            new Category(new LocaleString("categoryEs4", "categoryEn4"), "category4")
    );

    private static final List<CategoryQuery> categoryQueriesEs = List.of(
            new CategoryQuery("categoryEs1", "category1"),
            new CategoryQuery("categoryEs2", "category2"),
            new CategoryQuery("categoryEs3", "category3"),
            new CategoryQuery("categoryEs4", "category4")
    );

    private static final List<CategoryQuery> categoryQueriesEn = List.of(
            new CategoryQuery("categoryEn1", "category1"),
            new CategoryQuery("categoryEn2", "category2"),
            new CategoryQuery("categoryEn3", "category3"),
            new CategoryQuery("categoryEn4", "category4")
    );

    public static List<CategoryEntity> getCategoryEntities() {
        return List.copyOf(categoryEntities);
    }

    public static CategoryEntity getCategoryEntity(int position) {
        return categoryEntities.get(position);
    }

    public static List<Category> getCategories() {
        return List.copyOf(categories);
    }

    public static Category getCategory(int position) {
        return categories.get(position);
    }

    public static List<CategoryQuery> getCategoryQueriesEs() {
        return List.copyOf(categoryQueriesEs);
    }

    public static CategoryQuery getCategoryQueryEs(int position) {
        return categoryQueriesEs.get(position);
    }

    public static List<CategoryQuery> getCategoryQueriesEn() {
        return List.copyOf(categoryQueriesEn);
    }

    public static CategoryQuery getCategoryQueryEn(int position) {
        return categoryQueriesEn.get(position);
    }
}
