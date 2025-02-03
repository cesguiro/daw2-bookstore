package es.cesguiro.repository.mapper;

import es.cesguiro.dao.jpa.entity.GenreEntityJpa;
import es.cesguiro.repository.model.GenreEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreMapperTest {

    @Test
    @DisplayName("Test toGenreEntity with null should return null")
    void testToGenreEntityWithNull() {
        assertNull(GenreMapper.toGenreEntity(null));
    }

    @Test
    @DisplayName("Test map GenreEntityJpa to toGenreEntity")
    void testToGenreEntityWithLocaleEn() {
        GenreEntityJpa genreEntityJpa = new GenreEntityJpa();
        genreEntityJpa.setNameEs("Nombre Género");
        genreEntityJpa.setNameEn("Genre Name");
        genreEntityJpa.setSlug("genre-name");

        GenreEntity genreEntity = GenreMapper.toGenreEntity(genreEntityJpa);

        assertAll(
                () -> assertEquals("Nombre Género", genreEntity.nameEs()),
                () -> assertEquals("Genre Name", genreEntity.nameEn()),
                () -> assertEquals("genre-name", genreEntity.slug())
        );
    }

}