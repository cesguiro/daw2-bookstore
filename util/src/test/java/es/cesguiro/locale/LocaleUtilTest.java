package es.cesguiro.locale;

import es.cesguiro.exception.LocaleException;
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
class LocaleUtilTest {

    @Mock
    private LocaleProvider mockLocaleProvider;

    //private LanguageUtil languageUtil = LanguageUtil.getInstance(mockLanguageProvider);

    @AfterEach
    void resetSingleton() {
        LocaleUtil.resetInstance();
        PropertyUtil.resetInstance();
    }

    @Test
    @DisplayName("Test getInstance returns the same instance")
    void testSingletonInstance() {
        LocaleUtil instance1 = LocaleUtil.getInstance(mockLocaleProvider);
        LocaleUtil instance2 = LocaleUtil.getInstance(mockLocaleProvider);
        assertSame(instance1, instance2, "getInstance should return the same instance");
    }

    @Test
    @DisplayName("Test getLanguage with custom LanguageProvider")
    void testGetLanguageWithCustomProvider() {
        LocaleUtil localeUtil = LocaleUtil.getInstance(mockLocaleProvider);
        Mockito.when(mockLocaleProvider.getLanguage()).thenReturn("fr");

        String language = localeUtil.getLanguage();
        assertEquals("fr", language, "LanguageUtil should use the custom LanguageProvider");
    }

    @Test
    @DisplayName("Test resetInstance resets the singleton")
    void testResetInstance() {
        LocaleUtil instance1 = LocaleUtil.getInstance(mockLocaleProvider);
        LocaleUtil.resetInstance();
        LocaleUtil instance2 = LocaleUtil.getInstance(mockLocaleProvider);
        assertNotSame(instance1, instance2, "resetInstance should create a new instance");
    }

    @Test
    @DisplayName("Test setLanguageProvider after resetInstance")
    void testSetLanguageProviderAfterReset() {
        LocaleUtil localeUtil = LocaleUtil.getInstance(mockLocaleProvider);
        Mockito.when(mockLocaleProvider.getLanguage()).thenReturn("it");

        String language = localeUtil.getLanguage();

        assertEquals("it", language, "LanguageUtil should use the new LanguageProvider after reset");
    }

    @Test
    @DisplayName("Test LanguageUtil.getInstance with null")
    void testSetLanguageProviderWithNull() {
        assertThrows(LocaleException.class, () -> LocaleUtil.getInstance(null),
                "LanguageUtil.getInstance should throw NullPointerException when null is provided");
    }

    @Test
    @DisplayName("Test getLanguage should return es")
    void testGetLanguageShouldReturnEs() {
        LocaleUtil localeUtil = LocaleUtil.getInstance(mockLocaleProvider);
        Mockito.when(mockLocaleProvider.getLanguage()).thenReturn("es");
        String language = localeUtil.getLanguage();
        assertEquals("es", language, "LanguageUtil should return the default language");
    }
}