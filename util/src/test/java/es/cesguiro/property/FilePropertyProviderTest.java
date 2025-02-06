package es.cesguiro.property;

import es.cesguiro.exception.AppFileNotFoundException;
import es.cesguiro.exception.KeyNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class FilePropertyProviderTest {


    @Test
    @DisplayName("Test FilePropertyProvider load correct property")
    void testPropertyLoading() {
        FilePropertyProvider filePropertyProvider = new FilePropertyProvider("application.properties");

        assertAll(
                () -> assertEquals("app.default", filePropertyProvider.getProperty("app.name"))
        );
    }



    @Test
    @DisplayName("Test getProperty with null key should throw KeyNotFoundException")
    void testGetPropertyWithNullKey() {
        FilePropertyProvider filePropertyProvider = new FilePropertyProvider("application.properties");
        assertThrows(KeyNotFoundException.class, () -> filePropertyProvider.getProperty(""));
    }

    @Test
    @DisplayName("Test getProperty with empty value should return empty string")
    void testGetPropertyWithNullValue() {
        FilePropertyProvider filePropertyProvider = new FilePropertyProvider("application.properties");
        String property = filePropertyProvider.getProperty("null.value");
        assertEquals("", property);
    }

    @Test
    @DisplayName("Test non existing file should throw AppFileNotFoundException")
    void testMissingPropertiesFile() {
        assertThrows(AppFileNotFoundException.class, () -> new FilePropertyProvider("non-existing.properties"));
    }

    @Test
    @DisplayName("Test null file should throw AppFileNotFoundException")
    void testNullPropertiesFile() {
        assertThrows(AppFileNotFoundException.class, () -> new FilePropertyProvider(null));
    }

    @Test
    @DisplayName("Test getProperty with default value when property is missing")
    void testGetPropertyWithDefaultValue() {
        FilePropertyProvider filePropertyProvider = new FilePropertyProvider("application.properties");
        String property = filePropertyProvider.getProperty("non-existing.key", "default.value");
        assertEquals("default.value", property);
    }

    @Test
    @DisplayName("Test getProperty with special characters in key")
    void testGetPropertyWithSpecialChars() {
        FilePropertyProvider filePropertyProvider = new FilePropertyProvider("application.properties");
        String property = filePropertyProvider.getProperty("app.special@key#1");
        assertNotNull(property);
    }


}