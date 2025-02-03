package es.cesguiro.model;

import es.cesguiro.model.vo.LocaleString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    @Test
    @DisplayName("Test create Author")
    void testCreateAuthor() {
        String name = "Author Name";
        String nationality = "Nationality";
        String biographyEs = "Biografía en Español";
        String biographyEn = "Biography in English";
        LocaleString biography = new LocaleString(biographyEs, biographyEn);
        int birthYear = 1900;
        Integer deathYear = 2000;
        String slug = "author-name";

        Author author = new Author(name, nationality, biography, birthYear, deathYear, slug);

        assertAll(
            () -> assertEquals(name, author.getName(), "Names should match"),
            () -> assertEquals(nationality, author.getNationality(), "Nationalities should match"),
            () -> assertEquals(biographyEs, author.getBiography("es"), "Biography ES should match"),
            () -> assertEquals(biographyEn, author.getBiography("en"), "Biography EN should match"),
            () -> assertEquals(birthYear, author.getBirthYear(), "Birth years should match"),
            () -> assertEquals(deathYear, author.getDeathYear(), "Death years should match"),
            () -> assertEquals(slug, author.getSlug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test getBiography in Spanish")
    void testGetBiographyWithLocaleEs() {
        String name = "Author Name";
        String nationality = "Nationality";
        String biographyEs = "Biografía en Español";
        String biographyEn = "Biography in English";
        LocaleString biography = new LocaleString(biographyEs, biographyEn);
        int birthYear = 1900;
        Integer deathYear = 2000;
        String slug = "author-name";

        Author author = new Author(name, nationality, biography, birthYear, deathYear, slug);

        assertEquals(biographyEs, author.getBiography("es"), "Biography ES should match");
    }

    @Test
    @DisplayName("Test getBiography in English")
    void testGetBiographyWithLocaleEn() {
        String name = "Author Name";
        String nationality = "Nationality";
        String biographyEs = "Biografía en Español";
        String biographyEn = "Biography in English";
        LocaleString biography = new LocaleString(biographyEs, biographyEn);
        int birthYear = 1900;
        Integer deathYear = 2000;
        String slug = "author-name";

        Author author = new Author(name, nationality, biography, birthYear, deathYear, slug);

        assertEquals(biographyEn, author.getBiography("en"), "Biography EN should match");
    }
}