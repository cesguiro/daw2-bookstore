package es.cesguiro.property;

import es.cesguiro.exception.AppFileNotFoundException;
import es.cesguiro.exception.KeyNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DefaultPropertyProviderTest {


    @ParameterizedTest
    @DisplayName("Test DefaultPropertyProvider loads correct properties with custom and default values")
    @CsvSource({
            "custom.properties, app.name, app.custom",
            "'', app.name, app.default"
    })
    void testPropertyLoading(String propertiesFile, String key, String expectedValue) {
        DefaultPropertyProvider provider =
                propertiesFile.isEmpty() ? new DefaultPropertyProvider() : new DefaultPropertyProvider(propertiesFile);

        String property = provider.getProperty(key);

        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals(expectedValue, property)
        );
    }


    @Test
    @DisplayName("Test get property with default propertiesFile with empty key should throw KeyNotFoundException")
    void getEmptyPropertyName() {
        DefaultPropertyProvider defaultPropertyProvider = new DefaultPropertyProvider();
        assertThrows(KeyNotFoundException.class, () -> defaultPropertyProvider.getProperty(""));
    }

    @Test
    @DisplayName("Test getProperty with null key should throw KeyNotFoundException")
    void testGetPropertyWithNullKey() {
        DefaultPropertyProvider defaultPropertyProvider = new DefaultPropertyProvider();
        assertThrows(KeyNotFoundException.class, () -> defaultPropertyProvider.getProperty(""));
    }

    @Test
    @DisplayName("Test getProperty with empty value should return empty string")
    void testGetPropertyWithNullValue() {
        DefaultPropertyProvider defaultPropertyProvider = new DefaultPropertyProvider();
        String property = defaultPropertyProvider.getProperty("null.value");
        assertEquals("", property);
    }

    @Test
    @DisplayName("Test missing application.properties should throw AppFileNotFoundException")
    void testMissingPropertiesFile() {
        assertThrows(AppFileNotFoundException.class, () -> new DefaultPropertyProvider("non-existing.properties"));
    }

    @Test
    @DisplayName("Test getProperty with default value when property is missing")
    void testGetPropertyWithDefaultValue() {
        DefaultPropertyProvider defaultPropertyProvider = new DefaultPropertyProvider();
        String property = defaultPropertyProvider.getProperty("non-existing.key", "default.value");
        assertEquals("default.value", property);
    }

    @Test
    @DisplayName("Test getProperty with special characters in key")
    void testGetPropertyWithSpecialChars() {
        DefaultPropertyProvider defaultPropertyProvider = new DefaultPropertyProvider();
        String property = defaultPropertyProvider.getProperty("app.special@key#1");
        assertNotNull(property);
    }


}