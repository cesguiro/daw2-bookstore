package es.cesguiro.repository.mapper;

import es.cesguiro.dao.jpa.entity.AuthorEntityJpa;
import es.cesguiro.repository.model.AuthorEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class AuthorMapperTest {

    @Test
    @DisplayName("Test toAuthorEntity with null should return null")
    void testToAuthorEntityWithNull() {
        assertNull(AuthorMapper.toAuthorEntity(null));
    }

    @Test
    @DisplayName("Test map AuthorEntityJpa to toAuthorEntity")
    void testToAuthorEntityWithLocaleEn() {
        AuthorEntityJpa authorEntityJpa = new AuthorEntityJpa();
        authorEntityJpa.setName("Author Name");
        authorEntityJpa.setNationality("Nationality");
        authorEntityJpa.setBiographyEn("Biography in English");
        authorEntityJpa.setBiographyEs("Biografía en Español");
        authorEntityJpa.setBirthYear(1900);
        authorEntityJpa.setDeathYear(2000);
        authorEntityJpa.setSlug("author-name");

        AuthorEntity authorEntity = AuthorMapper.toAuthorEntity(authorEntityJpa);

        assertAll(
                () -> assertEquals("Author Name", authorEntity.name()),
                () -> assertEquals("Nationality", authorEntity.nationality()),
                () -> assertEquals("Biografía en Español", authorEntity.biographyEs()),
                () -> assertEquals("Biography in English", authorEntity.biographyEn()),
                () -> assertEquals(1900, authorEntity.birthYear()),
                () -> assertEquals(2000, authorEntity.deathYear()),
                () -> assertEquals("author-name", authorEntity.slug())
        );
    }


}