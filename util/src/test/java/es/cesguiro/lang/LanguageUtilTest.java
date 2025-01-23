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
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class LanguageUtilTest {

    @AfterEach
    void resetSingleton() {
        LanguageUtil.resetInstance();
        PropertyUtil.resetInstance();
    }

    @Test
    @DisplayName("Test getInstance returns the same instance")
    void testSingletonInstance() {
        LanguageUtil instance1 = LanguageUtil.getInstance();
        LanguageUtil instance2 = LanguageUtil.getInstance();
        assertSame(instance1, instance2, "getInstance should return the same instance");
    }

    @Test
    @DisplayName("Test getLanguage with custom LanguageProvider")
    void testGetLanguageWithCustomProvider() {
        LanguageUtil languageUtil = LanguageUtil.getInstance();
        LanguageProvider mockProvider = Mockito.mock(LanguageProvider.class);
        Mockito.when(mockProvider.getLanguage()).thenReturn("fr");
        languageUtil.setLanguageProvider(mockProvider);

        String language = languageUtil.getLanguage();
        assertEquals("fr", language, "LanguageUtil should use the custom LanguageProvider");
    }

    @Test
    @DisplayName("Test resetInstance resets the singleton")
    void testResetInstance() {
        LanguageUtil instance1 = LanguageUtil.getInstance();
        LanguageUtil.resetInstance();
        LanguageUtil instance2 = LanguageUtil.getInstance();
        assertNotSame(instance1, instance2, "resetInstance should create a new instance");
    }

    @Test
    @DisplayName("Test setLanguageProvider after resetInstance")
    void testSetLanguageProviderAfterReset() {
        LanguageUtil.resetInstance(); // Resetear el Singleton
        LanguageUtil languageUtil = LanguageUtil.getInstance();

        LanguageProvider mockProvider = Mockito.mock(LanguageProvider.class);
        Mockito.when(mockProvider.getLanguage()).thenReturn("it");

        languageUtil.setLanguageProvider(mockProvider);
        String language = languageUtil.getLanguage();

        assertEquals("it", language, "LanguageUtil should use the new LanguageProvider after reset");
    }

    @Test
    @DisplayName("Test getInstance is thread-safe")
    void testSingletonThreadSafety() throws InterruptedException {
        final Set<LanguageUtil> instances = ConcurrentHashMap.newKeySet();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executor.submit(() -> instances.add(LanguageUtil.getInstance()));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        assertEquals(1, instances.size(), "Only one instance should be created in a multithreaded environment");
    }

    @Test
    @DisplayName("Test setLanguageProvider with null")
    void testSetLanguageProviderWithNull() {
        LanguageUtil languageUtil = LanguageUtil.getInstance();

        assertThrows(IllegalArgumentException.class, () -> languageUtil.setLanguageProvider(null),
                "setLanguageProvider should throw NullPointerException when null is provided");
    }

    @Test
    @DisplayName("Test getLanguage should return es")
    void testGetLanguageShouldReturnEs() {
        LanguageUtil languageUtil = LanguageUtil.getInstance();
        LanguageProvider mockProvider = Mockito.mock(LanguageProvider.class);
        Mockito.when(mockProvider.getLanguage()).thenReturn("es");
        languageUtil.setLanguageProvider(mockProvider);

        String language = languageUtil.getLanguage();
        assertEquals("es", language, "LanguageUtil should return the default language");
    }

    @Test
    @DisplayName("Test getLanguage should throw LanguageException when language is not supported and app.default.language is set to not supported language")
    void getLanguageWithUnsupportedLanguage() {
        PropertyProvider mockProvider = Mockito.mock(PropertyProvider.class);
        Mockito.when(mockProvider.getProperty("app.default.language")).thenReturn("fr");
        PropertyUtil.getInstance().setPropertyProvider(mockProvider);
        Locale.setDefault(Locale.of("it"));
        assertThrows(LanguageException.class, () -> LanguageUtil.getInstance().getLanguage());
    }

    @Test
    @DisplayName("Test getLanguage should throw KeyNotFoundException when language is not supported and app.default.language is not set")
    void getLanguageWithUnsupportedLanguageAndAppDefaultLanguageIsNotSet() {
        PropertyProvider mockProvider = Mockito.mock(PropertyProvider.class);
        Mockito.when(mockProvider.getProperty("app.default.language")).thenThrow(KeyNotFoundException.class);
        PropertyUtil.getInstance().setPropertyProvider(mockProvider);
        Locale.setDefault(Locale.of("it"));
        assertThrows(KeyNotFoundException.class, () -> LanguageUtil.getInstance().getLanguage());
    }

    @Test
    @DisplayName("Test getLanguage should throw LanguageException when language is not supported and application.properties is missing")
    void getLanguageWithUnsupportedLanguageAndMissingApplicationProperties() {
        PropertyProvider mockProvider = Mockito.mock(PropertyProvider.class);
        Mockito.when(mockProvider.getProperty("app.default.language")).thenThrow(new AppFileNotFoundException("application.properties"));

        PropertyUtil.getInstance().setPropertyProvider(mockProvider);

        Locale.setDefault(Locale.of("it"));

        assertThrows(AppFileNotFoundException.class, () -> LanguageUtil.getInstance().getLanguage());
    }

}