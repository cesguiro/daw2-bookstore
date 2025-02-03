package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.locale.LocaleProvider;
import es.cesguiro.locale.LocaleUtil;
import es.cesguiro.model.Category;
import es.cesguiro.model.vo.LocaleString;
import es.cesguiro.repository.model.CategoryEntity;
import es.cesguiro.usecase.book.query.data.CategoryData;
import es.cesguiro.usecase.book.query.model.CategoryQuery;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryMapperTest {

    @Mock
    private LocaleProvider mockLocaleProvider;

    @BeforeEach
    void setup() {
        LocaleUtil.resetInstance();
        LocaleUtil.getInstance(mockLocaleProvider);
    }

    @AfterEach
    void teardown() {
        LocaleUtil.resetInstance();
    }


    @Test
    @DisplayName("Test map CategoryEntity to Category")
    void toCategory() {
        CategoryEntity categoryEntity = CategoryData.getCategoryEntity(0);
        Category result = CategoryMapper.toCategory(categoryEntity);
        assertAll(
                () -> assertEquals(categoryEntity.nameEs(), result.getName("es"), "Names should match"),
                () -> assertEquals(categoryEntity.nameEn(), result.getName("en"), "Names should match"),
                () -> assertEquals(categoryEntity.slug(), result.getSlug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null CategoryEntity returns null Category")
    void toCategoryNull() {
        Category result = CategoryMapper.toCategory(null);
        assertNull(result, "Mapping null CategoryEntity should return null Category");
    }

    @Test
    @DisplayName("Test map Category to CategoryQuery with Locale es")
    void toCategoryQuery() {
        when(mockLocaleProvider.getLanguage()).thenReturn("es");

        Category category = CategoryData.getCategory(0);
        CategoryQuery result = CategoryMapper.toCategoryQuery(category);
        assertAll(
                () -> assertEquals(category.getName("es"), result.name(), "Names should match"),
                () -> assertEquals(category.getSlug(), result.slug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test map Category to CategoryQuery with Locale en")
    void toCategoryQueryEn() {
        when(mockLocaleProvider.getLanguage()).thenReturn("en");

        Category category = CategoryData.getCategory(0);
        CategoryQuery result = CategoryMapper.toCategoryQuery(category);
        assertAll(
                () -> assertEquals(category.getName("en"), result.name(), "Names should match"),
                () -> assertEquals(category.getSlug(), result.slug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null Category returns null CategoryQuery")
    void toCategoryQueryNull() {
        CategoryQuery categoryQuery = CategoryMapper.toCategoryQuery(null);
        assertNull(categoryQuery, "Mapping null Category should return null CategoryDto");
    }

}