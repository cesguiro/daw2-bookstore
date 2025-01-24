package es.cesguiro.lang;

import es.cesguiro.exception.AppFileNotFoundException;
import es.cesguiro.exception.KeyNotFoundException;
import es.cesguiro.exception.LanguageException;
import es.cesguiro.property.PropertyProvider;
import es.cesguiro.property.PropertyUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class DefaultLanguageProviderTest {

    private final DefaultLanguageProvider defaultLanguageProvider = new DefaultLanguageProvider();
    private PropertyUtil propertyUtil;

    @AfterEach
    void resetSingleton() {
        PropertyUtil.resetInstance();
    }

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
        PropertyProvider mockProvider = Mockito.mock(PropertyProvider.class);
        Mockito.when(mockProvider.getProperty("app.default.language")).thenReturn("es");
        PropertyUtil.getInstance(mockProvider);
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

        PropertyProvider mockProvider = Mockito.mock(PropertyProvider.class);
        Mockito.when(mockProvider.getProperty("app.default.language")).thenReturn("es");


        String language = defaultLanguageProvider.getLanguage();
        assertAll(
                () -> assertNotNull(language),
                () -> assertEquals("es", language) // Retorna el idioma por defecto
        );
    }

}