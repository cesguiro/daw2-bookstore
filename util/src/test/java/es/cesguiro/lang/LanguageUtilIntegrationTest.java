package es.cesguiro.lang;

import es.cesguiro.exception.LanguageException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class LanguageUtilIntegrationTest {

    private final LanguageUtil languageUtil = LanguageUtil.getInstance();

    @Test
    @DisplayName("Test getLanguage should return es")
    void testGetLanguage() {
        LanguageUtil languageUtil = LanguageUtil.getInstance();
        Locale.setDefault(Locale.of("es"));
        String language = languageUtil.getLanguage();
        assertEquals("es", language, "LanguageUtil should return the default language");
    }

    @Test
    @DisplayName("Test getLanguage should return en")
    void testGetLanguageEn() {
        Locale.setDefault(Locale.ENGLISH);
        String language = languageUtil.getLanguage();
        assertEquals("en", language, "LanguageUtil should return the default language");
    }

    @Test
    @DisplayName("Test getLanguage should return app.default.language property when language Locale is not supported")
    void testGetLanguageWithUnsupportedLanguageLocale() {
        Locale.setDefault(Locale.FRANCE);
        String language = languageUtil.getLanguage();
        assertEquals("es", language, "LanguageUtil should return the default language");
    }



}
