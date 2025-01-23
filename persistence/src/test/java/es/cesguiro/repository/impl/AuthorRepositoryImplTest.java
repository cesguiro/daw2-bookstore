package es.cesguiro.repository.impl;

import es.cesguiro.dao.jpa.entity.AuthorEntityJpa;
import es.cesguiro.dao.jpa.repository.AuthorRepositoryJpa;
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
class AuthorRepositoryImplTest {

    @Mock
    AuthorRepositoryJpa authorRepositoryJpa;

    @InjectMocks
    AuthorRepositoryImpl authorRepositoryImpl;

    @Test
    @DisplayName("Test findAllByBookIsbn method returns a list of AuthorEntity")
    void testFindAllByBookIsbn() {
        String isbn = "123";

        AuthorEntityJpa authorEntityJpa1 = new AuthorEntityJpa(
                1L,
                "Author 1",
                "english",
                "BiographyEs 1",
                "BiographyEn 1",
                1900,
                2000,
                "slug"
        );
        AuthorEntityJpa authorEntityJpa2 = new AuthorEntityJpa(
                2L,
                "Author 2",
                "english",
                "BiographyEs 2",
                "BiographyEn 2",
                1900,
                2000,
                "slug"
        );

        when(authorRepositoryJpa.findAllByBookIsbn(isbn)).thenReturn(List.of(authorEntityJpa1, authorEntityJpa2));

        List<AuthorEntity> authorEntities = authorRepositoryImpl.findAllByBookIsbn(isbn);

        assertAll(
                () -> assertEquals(2, authorEntities.size()),
                () -> assertEquals(authorEntityJpa1.getName(), authorEntities.getFirst().name()),
                () -> assertEquals(authorEntityJpa2.getName(), authorEntities.get(1).name())
        );
    }

    @Test
    @DisplayName("Test findAllByBookIsbn method returns a list of single AuthorEntity")
    void testFindAllByBookIsbnOneAuthor() {
        String isbn = "123";

        AuthorEntityJpa authorEntityJpa1 = new AuthorEntityJpa(
                1L,
                "Author 1",
                "english",
                "BiographyEs 1",
                "BiographyEn 1",
                1900,
                2000,
                "slug"
        );

        when(authorRepositoryJpa.findAllByBookIsbn(isbn)).thenReturn(List.of(authorEntityJpa1));

        List<AuthorEntity> authorEntities = authorRepositoryImpl.findAllByBookIsbn(isbn);

        assertAll(
                () -> assertEquals(1, authorEntities.size()),
                () -> assertEquals(authorEntityJpa1.getName(), authorEntities.getFirst().name())
        );
    }

    @Test
    @DisplayName("Test findAllByBookIsbn method returns a empty list of AuthorEntity")
    void testFindAll() {
        String isbn = "123";
        when(authorRepositoryJpa.findAllByBookIsbn(isbn)).thenReturn(List.of());

        List<AuthorEntity> authorEntities = authorRepositoryImpl.findAllByBookIsbn(isbn);
        assertEquals(0, authorEntities.size());
    }

}