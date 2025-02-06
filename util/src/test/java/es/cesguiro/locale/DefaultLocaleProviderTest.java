package es.cesguiro.locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class DefaultLocaleProviderTest {

    private final DefaultLocaleProvider defaultLanguageProvider = new DefaultLocaleProvider();

    @Test
    @DisplayName("Test getLanguage method with Locale('es') should return es")
    void getLanguageShouldReturnEs() {
        Locale.setDefault(Locale.of("es"));
        String language = defaultLanguageProvider.getLanguage();
        assertAll(
                () -> assertNotNull(language),
                () -> assertEquals("es", language)
        );
    }

    @Test
    @DisplayName("Test getLanguage method with Locale('en') should return en")
    void getLanguageShouldReturnEn() {
        Locale.setDefault(Locale.ENGLISH);
        String language = defaultLanguageProvider.getLanguage();
        assertAll(
                () -> assertNotNull(language),
                () -> assertEquals("en", language)
        );
    }

    @Test
    @DisplayName("Test getLanguage method should return app.default.language property when language Locale is not supported")
    void getLanguageWithUnsupportedLanguageLocale() {
        Locale.setDefault(Locale.FRANCE);
        String language = defaultLanguageProvider.getLanguage();
        assertAll(
                () -> assertNotNull(language),
                () -> assertEquals("es", language)
        );
    }


    @Test
    @DisplayName("Test getLanguage should handle case-insensitive language codes")
    void getLanguageCaseInsensitive() {
        Locale.setDefault(Locale.of("ES"));
        String language = defaultLanguageProvider.getLanguage();
        assertAll(
                () -> assertNotNull(language),
                () -> assertEquals("es", language)
        );
    }

    @Test
    @DisplayName("Test getLanguage should handle Locale with language and country")
    void getLanguageWithLocaleAndCountry() {
        Locale.setDefault(Locale.of("es", "ES"));
        String language = defaultLanguageProvider.getLanguage();
        assertAll(
                () -> assertNotNull(language),
                () -> assertEquals("es", language)
        );
    }

    @Test
    @DisplayName("Test getLanguage should return default language when language is close but unsupported")
    void getLanguageWithUnsupportedVariant() {
        Locale.setDefault(Locale.of("en-US"));

        String language = defaultLanguageProvider.getLanguage();
        assertAll(
                () -> assertNotNull(language),
                () -> assertEquals("es", language) // Retorna el idioma por defecto
        );
    }

    @Test
    @DisplayName("Test getDateFormat with Locale('es') should return dd/MM/yyyy")
    void testFormatDateForSpanishLocale() {
        Locale.setDefault(Locale.of("es"));

        LocalDate testDate = LocalDate.of(2025, 5, 2);

        DefaultLocaleProvider defaultLocaleProvider = new DefaultLocaleProvider();

        String formattedDate = defaultLocaleProvider.formatDate(testDate);
        assertEquals("02/05/2025", formattedDate);
    }

    @Test
    @DisplayName("Test getDateFormat with Locale('en') should return yyyy/MM/dd")
    void testFormatDateForEnglishLocale() {
        Locale.setDefault(Locale.of("en"));

        LocalDate testDate = LocalDate.of(2025, 5, 2);

        DefaultLocaleProvider defaultLocaleProvider = new DefaultLocaleProvider();

        String formattedDate = defaultLocaleProvider.formatDate(testDate);
        assertEquals("2025/05/02", formattedDate);
    }


    @Test
    @DisplayName("Test getDateFormat with Locale('fr') should return yyyy/MM/dd")
    void testFormatDateForNotSupportedLocale() {
        Locale.setDefault(Locale.of("fr"));

        LocalDate testDate = LocalDate.of(2025, 5, 2);

        DefaultLocaleProvider defaultLocaleProvider = new DefaultLocaleProvider();

        String formattedDate = defaultLocaleProvider.formatDate(testDate);
        assertEquals("2025/05/02", formattedDate);
    }

}