package es.cesguiro.locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class LocaleUtilIntegrationTest {

    @Test
    @DisplayName("Test getLanguage should return es")
    void testGetLanguage() {
        Locale.setDefault(Locale.of("es"));
        String language = LocaleUtil.getLocaleProvider().getLanguage();
        assertEquals("es", language, "LanguageUtil should return the default language");
    }

    @Test
    @DisplayName("Test getLanguage should return en")
    void testGetLanguageEn() {
        Locale.setDefault(Locale.ENGLISH);
        String language = LocaleUtil.getLocaleProvider().getLanguage();
        assertEquals("en", language, "LanguageUtil should return the default language");
    }

    @Test
    @DisplayName("Test getLanguage should return app.default.language property when language Locale is not supported")
    void testGetLanguageWithUnsupportedLanguageLocale() {
        Locale.setDefault(Locale.FRANCE);
        String language = LocaleUtil.getLocaleProvider().getLanguage();
        assertEquals("es", language, "LanguageUtil should return the default language");
    }

    @Test
    @DisplayName("Test getLanguage should handle case-insensitive language codes")
    void testGetLanguageCaseInsensitive() {
        Locale.setDefault(Locale.of("ES"));
        String language = LocaleUtil.getLocaleProvider().getLanguage();
        assertEquals("es", language, "LanguageUtil should return the default language");
    }

}
