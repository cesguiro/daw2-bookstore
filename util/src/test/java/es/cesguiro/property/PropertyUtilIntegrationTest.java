package es.cesguiro.property;

import es.cesguiro.exception.KeyNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyUtilIntegrationTest {


    @Test
    @DisplayName("Test getProperty should return correct value if key exists in system properties")
    void testGetPropertyFromSystemProperties() {
        System.setProperty("app.name", "App Name");
        String property = PropertyUtil.getPropertyProvider().getProperty("app.name");
        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("App Name", property)
        );
    }

    @Test
    @DisplayName("getProperty should return correct value if key exists in application.properties")
    void testGetPropertyFromPropertiesFile() {
        String property = PropertyUtil.getPropertyProvider().getProperty("app.version");
        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("1.0", property)
        );
    }

    @Test
    @DisplayName("Test getProperty should return default value if key is not found")
    void testGetPropertyDefaultValue() {
        String property = PropertyUtil.getPropertyProvider().getProperty("app.nonexistent", "default");
        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("default", property)
        );
    }

    @Test
    @DisplayName("Test get non-existing property should throw KeyNotFoundException")
    void testGetNonExistingProperty() {
        assertThrows(KeyNotFoundException.class, () -> PropertyUtil.getPropertyProvider().getProperty("non.existing"));
    }

    @Test
    @DisplayName("Test get property with empty key should throw KeyNotFoundException")
    void testGetPropertyEmptyKey() {
        assertThrows(KeyNotFoundException.class, () -> PropertyUtil.getPropertyProvider().getProperty(""));
    }

    @Test
    @DisplayName("Test get property with null key should throw KeyNotFoundException")
    void testGetPropertyNullKey() {
        assertThrows(KeyNotFoundException.class, () -> PropertyUtil.getPropertyProvider().getProperty(null));
    }
}
