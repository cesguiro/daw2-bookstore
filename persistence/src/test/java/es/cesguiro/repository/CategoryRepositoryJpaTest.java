package es.cesguiro.repository;

import es.cesguiro.dao.CategoryDao;
import es.cesguiro.dao.jpa.entity.CategoryEntityJpa;
import es.cesguiro.dao.jpa.entity.GenreEntityJpa;
import es.cesguiro.repository.data.CategoryData;
import es.cesguiro.repository.model.CategoryEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryRepositoryJpaTest {

    @Mock
    CategoryDao categoryDao;

    @InjectMocks
    CategoryRepositoryJpa categoryRepositoryJpa;

    @Test
    @DisplayName("Test findByBookIsbn method returns Optional<CategoryEntity>")
    void testFindByIsbnBook() {
        String isbn = "123";

        CategoryEntityJpa categoryEntityJpa = CategoryData.getCategoryEntityJpa();
        CategoryEntity categoryEntity = CategoryData.getCategoryEntity();

        when(categoryDao.findByBookIsbn(isbn)).thenReturn(Optional.of(categoryEntityJpa));

        Optional<CategoryEntity> expected = Optional.of(categoryEntity);
        Optional<CategoryEntityJpa> result = categoryDao.findByBookIsbn(isbn);

        assertAll(
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(expected.get().nameEs(), result.get().getNameEs()),
                () -> assertEquals(expected.get().nameEn(), result.get().getNameEn()),
                () -> assertEquals(expected.get().slug(), result.get().getSlug())
        );

    }

    @Test
    @DisplayName("Test findByBookIsbn method returns Optional.empty()")
    void testByIsbnBookEmpty() {
        String isbn = "123";

        when(categoryDao.findByBookIsbn(isbn)).thenReturn(Optional.empty());

        assertTrue(categoryRepositoryJpa.findByBookIsbn(isbn).isEmpty());
    }

}