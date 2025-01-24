package es.cesguiro.lang;

import es.cesguiro.exception.LanguageException;
import es.cesguiro.property.PropertyUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LanguageUtilTest {

    @Mock
    private LanguageProvider mockLanguageProvider;

    //private LanguageUtil languageUtil = LanguageUtil.getInstance(mockLanguageProvider);

    @AfterEach
    void resetSingleton() {
        LanguageUtil.resetInstance();
        PropertyUtil.resetInstance();
    }

    @Test
    @DisplayName("Test getInstance returns the same instance")
    void testSingletonInstance() {
        LanguageUtil instance1 = LanguageUtil.getInstance(mockLanguageProvider);
        LanguageUtil instance2 = LanguageUtil.getInstance(mockLanguageProvider);
        assertSame(instance1, instance2, "getInstance should return the same instance");
    }

    @Test
    @DisplayName("Test getLanguage with custom LanguageProvider")
    void testGetLanguageWithCustomProvider() {
        LanguageUtil languageUtil = LanguageUtil.getInstance(mockLanguageProvider);
        Mockito.when(mockLanguageProvider.getLanguage()).thenReturn("fr");

        String language = languageUtil.getLanguage();
        assertEquals("fr", language, "LanguageUtil should use the custom LanguageProvider");
    }

    @Test
    @DisplayName("Test resetInstance resets the singleton")
    void testResetInstance() {
        LanguageUtil instance1 = LanguageUtil.getInstance(mockLanguageProvider);
        LanguageUtil.resetInstance();
        LanguageUtil instance2 = LanguageUtil.getInstance(mockLanguageProvider);
        assertNotSame(instance1, instance2, "resetInstance should create a new instance");
    }

    @Test
    @DisplayName("Test setLanguageProvider after resetInstance")
    void testSetLanguageProviderAfterReset() {
        LanguageUtil languageUtil = LanguageUtil.getInstance(mockLanguageProvider);
        Mockito.when(mockLanguageProvider.getLanguage()).thenReturn("it");

        String language = languageUtil.getLanguage();

        assertEquals("it", language, "LanguageUtil should use the new LanguageProvider after reset");
    }

    @Test
    @DisplayName("Test LanguageUtil.getInstance with null")
    void testSetLanguageProviderWithNull() {
        assertThrows(LanguageException.class, () -> LanguageUtil.getInstance(null),
                "LanguageUtil.getInstance should throw NullPointerException when null is provided");
    }

    @Test
    @DisplayName("Test getLanguage should return es")
    void testGetLanguageShouldReturnEs() {
        LanguageUtil languageUtil = LanguageUtil.getInstance(mockLanguageProvider);
        Mockito.when(mockLanguageProvider.getLanguage()).thenReturn("es");
        String language = languageUtil.getLanguage();
        assertEquals("es", language, "LanguageUtil should return the default language");
    }
}