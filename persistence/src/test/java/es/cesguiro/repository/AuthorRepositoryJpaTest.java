package es.cesguiro.repository;

import es.cesguiro.dao.AuthorDao;
import es.cesguiro.dao.jpa.entity.AuthorEntityJpa;
import es.cesguiro.repository.data.AuthorData;
import es.cesguiro.repository.model.AuthorEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorRepositoryJpaTest {

    @Mock
    AuthorDao authorDao;

    @InjectMocks
    AuthorRepositoryJpa authorRepositoryJpa;

    @Test
    @DisplayName("Test findAllByBookIsbn method returns a list of AuthorEntity")
    void testFindAllByBookIsbn() {
        String isbn = "123";

        AuthorEntity authorEntity1 = AuthorData.getAuthorEntity(0);
        AuthorEntity authorEntity2 = AuthorData.getAuthorEntity(1);

        when(authorDao.findAllByBookIsbn(isbn)).thenReturn(List.of(authorEntity1, authorEntity2));

        List<AuthorEntity> expected = List.of(authorEntity1, authorEntity2);
        List<AuthorEntity> result = authorRepositoryJpa.findAllByBookIsbn(isbn);

        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals(expected.getFirst().name(), result.getFirst().name()),
                () -> assertEquals(expected.getFirst().biographyEn(), result.getFirst().biographyEn()),
                () -> assertEquals(expected.getFirst().biographyEs(), result.getFirst().biographyEs()),
                () -> assertEquals(expected.getFirst().slug(), result.getFirst().slug()),
                () -> assertEquals(expected.getLast().name(), result.get(1).name()),
                () -> assertEquals(expected.getLast().biographyEn(), result.get(1).biographyEn()),
                () -> assertEquals(expected.getLast().biographyEs(), result.get(1).biographyEs()),
                () -> assertEquals(expected.getLast().slug(), result.get(1).slug())
        );
    }

    @Test
    @DisplayName("Test findAllByBookIsbn method returns a list of single AuthorEntity")
    void testFindAllByBookIsbnOneAuthor() {
        String isbn = "123";

        AuthorEntity authorEntity1 = AuthorData.getAuthorEntity(0);

        when(authorDao.findAllByBookIsbn(isbn)).thenReturn(List.of(authorEntity1));

        List<AuthorEntity> expected = List.of(authorEntity1);
        List<AuthorEntity> result = authorRepositoryJpa.findAllByBookIsbn(isbn);

        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals(expected.getFirst().name(), result.getFirst().name()),
                () -> assertEquals(expected.getFirst().biographyEn(), result.getFirst().biographyEn()),
                () -> assertEquals(expected.getFirst().biographyEs(), result.getFirst().biographyEs()),
                () -> assertEquals(expected.getFirst().slug(), result.getFirst().slug())
        );
    }

    @Test
    @DisplayName("Test findAllByBookIsbn method with non existing isbn returns a empty list of AuthorEntity")
    void testFindAll() {
        String isbn = "123";
        when(authorRepositoryJpa.findAllByBookIsbn(isbn)).thenReturn(List.of());

        List<AuthorEntity> result = authorRepositoryJpa.findAllByBookIsbn(isbn);
        assertEquals(0, result.size());
    }

}