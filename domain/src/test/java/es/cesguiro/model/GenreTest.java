package es.cesguiro.model;

import es.cesguiro.model.vo.LocaleString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreTest {

    @Test
    @DisplayName("Test create Genre")
    void testCreateGenre() {
        String nameEs = "Nombre del género";
        String nameEn = "Genre name";
        LocaleString name = new LocaleString(nameEs, nameEn);
        Genre genre = new Genre(name, "genre-slug");
        assertAll(
            () -> assertEquals(nameEs, genre.getName("es"), "Names should match"),
            () -> assertEquals(nameEn, genre.getName("en"), "Names should match"),
            () -> assertEquals("genre-slug", genre.getSlug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test getName in Spanish")
    void testGetNameWithLocaleEs() {
        String nameEs = "Nombre del género";
        String nameEn = "Genre name";
        LocaleString name = new LocaleString(nameEs, nameEn);
        Genre genre = new Genre(name, "genre-slug");
        assertEquals(nameEs, genre.getName("es"), "Names should match");
    }

    @Test
    @DisplayName("Test getName in English")
    void testGetNameWithLocaleEn() {
        String nameEs = "Nombre del género";
        String nameEn = "Genre name";
        LocaleString name = new LocaleString(nameEs, nameEn);
        Genre genre = new Genre(name, "genre-slug");
        assertEquals(nameEn, genre.getName("en"), "Names should match");
    }

}