package es.cesguiro.repository;

import es.cesguiro.dao.GenreDao;
import es.cesguiro.dao.jpa.entity.GenreEntityJpa;
import es.cesguiro.repository.data.GenreData;
import es.cesguiro.repository.model.GenreEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreRepositoryJpaTest {

    @Mock
    GenreDao genreDao;

    @InjectMocks
    GenreRepositoryJpa genreRepositoryJpa;

    @Test
    @DisplayName("Test findAllByBookIsbn method returns a list of GenreEntity")
    void testFindAllByBookIsbn() {
        String isbn = "123";

        GenreEntityJpa genreEntityJpa1 = GenreData.getGenreEntityJpas().getFirst();
        GenreEntityJpa genreEntityJpa2 = GenreData.getGenreEntityJpas().get(1);
        GenreEntity genreEntity1 = GenreData.getGenreEntities().getFirst();
        GenreEntity genreEntity2 = GenreData.getGenreEntities().get(1);

        when(genreDao.findAllByBookIsbn(isbn)).thenReturn(List.of(genreEntityJpa1, genreEntityJpa2));

        List<GenreEntity> expected = List.of(genreEntity1, genreEntity2);
        List<GenreEntity> result = genreRepositoryJpa.findAllByBookIsbn(isbn);

        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals(expected.getFirst().nameEs(), result.getFirst().nameEs()),
                () -> assertEquals(expected.getFirst().nameEn(), result.getFirst().nameEn()),
                () -> assertEquals(expected.getFirst().slug(), result.getFirst().slug()),
                () -> assertEquals(expected.get(1).nameEs(), result.get(1).nameEs()),
                () -> assertEquals(expected.get(1).nameEn(), result.get(1).nameEn()),
                () -> assertEquals(expected.get(1).slug(), result.get(1).slug())
        );
    }

    @Test
    @DisplayName("Test findAllByBookIsbn method returns a list of single GenreEntity")
    void testFindAllByBookIsbnSingle() {
        String isbn = "123";

        GenreEntityJpa genreEntityJpa = GenreData.getGenreEntityJpas().getFirst();
        GenreEntity genreEntity = GenreData.getGenreEntities().getFirst();

        when(genreDao.findAllByBookIsbn(isbn)).thenReturn(List.of(genreEntityJpa));

        List<GenreEntity> expected = List.of(genreEntity);
        List<GenreEntity> result = genreRepositoryJpa.findAllByBookIsbn(isbn);

        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals(expected.getFirst().nameEs(), result.getFirst().nameEs()),
                () -> assertEquals(expected.getFirst().nameEn(), result.getFirst().nameEn()),
                () -> assertEquals(expected.getFirst().slug(), result.getFirst().slug())
        );
    }

    @Test
    @DisplayName("Test findAllByBookIsbn with non existing isbn method returns an empty list")
    void testFindAllByBookIsbnEmpty() {
        String isbn = "123";

        when(genreDao.findAllByBookIsbn(isbn)).thenReturn(List.of());

        List<GenreEntity> result = genreRepositoryJpa.findAllByBookIsbn(isbn);

        assertTrue(result.isEmpty());
    }

}