package es.cesguiro.property;

import es.cesguiro.exception.AppFileNotFoundException;
import es.cesguiro.exception.KeyNotFoundException;
import es.cesguiro.exception.LoadPropertyFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultPropertyProviderTest {

    private String filename = "application.properties";
    DefaultPropertyProvider defaultPropertyProvider = new DefaultPropertyProvider(filename);

    @Test
    @DisplayName("Test get app.name property should return 'util'")
    void getProperty() {
        String property = defaultPropertyProvider.getProperty("app.name");
        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("util", property)
        );
    }

    @Test
    @DisplayName("Test get non-existing property should throw KeyNotFoundException")
    void getNonExistingProperty() {
        assertThrows(KeyNotFoundException.class, () -> defaultPropertyProvider.getProperty("non.existing"));
    }

    @Test
    @DisplayName("Test get property with empty key should throw KeyNotFoundException")
    void getEmptyPropertyName() {
        assertThrows(KeyNotFoundException.class, () -> defaultPropertyProvider.getProperty(""));
    }

    @Test
    @DisplayName("Test getProperty with null key should throw KeyNotFoundException")
    void testGetPropertyWithNullKey() {
        assertThrows(KeyNotFoundException.class, () -> defaultPropertyProvider.getProperty(""));
    }

    @Test
    @DisplayName("Test getProperty with empty value should return empty string")
    void testGetPropertyWithNullValue() {
        String property = defaultPropertyProvider.getProperty("null.value");
        assertEquals("", property);
    }

    @Test
    @DisplayName("Test missing application.properties should throw AppFileNotFoundException")
    void testMissingPropertiesFile() {
        assertThrows(AppFileNotFoundException.class, () -> new DefaultPropertyProvider("non-existing.properties"));
    }

}