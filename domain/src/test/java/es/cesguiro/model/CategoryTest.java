package es.cesguiro.model;

import es.cesguiro.model.vo.LocaleString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    @DisplayName("Test create Category")
    void testCreateCategory() {
        String nameEs = "Nombre de la categoría";
        String nameEn = "Category name";
        LocaleString name = new LocaleString(nameEs, nameEn);
        String slug = "category-slug";
        Category category = new Category(name, slug);
        assertAll(
            () -> assertEquals(nameEs, category.getName("es"), "Names should match"),
            () -> assertEquals(nameEn, category.getName("en"), "Names should match"),
            () -> assertEquals(slug, category.getSlug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test getName in Spanish")
    void testGetNameWithLocaleEs() {
        String nameEs = "Nombre de la categoría";
        String nameEn = "Category name";
        LocaleString name = new LocaleString(nameEs, nameEn);
        String slug = "category-slug";
        Category category = new Category(name, slug);
        assertEquals(nameEs, category.getName("es"), "Names should match");
    }

    @Test
    @DisplayName("Test getName in English")
    void testGetNameWithLocaleEn() {
        String nameEs = "Nombre de la categoría";
        String nameEn = "Category name";
        LocaleString name = new LocaleString(nameEs, nameEn);
        String slug = "category-slug";
        Category category = new Category(name, slug);
        assertEquals(nameEn, category.getName("en"), "Names should match");
    }

}