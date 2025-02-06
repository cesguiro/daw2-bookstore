package es.cesguiro.property;

import es.cesguiro.exception.KeyNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyUtilIntegrationTest {

    /*@AfterEach
    void resetPropertyUtil() {
        PropertyUtil.resetInstance();
    }

    @Test
    @DisplayName("Test getProperty with default propertiesFile should return correct value from default PropertyProvider")
    void testGetProperty() {
        String property = PropertyUtil.getInstance().getProperty("app.name");
        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("app.default", property)
        );
    }

    @Test
    @DisplayName("Test getProperty with custom propertiesFile should return correct value from custom PropertyProvider")
    void testGetPropertyCustom() {
        PropertyProvider propertyProvider = new DefaultPropertyProvider("custom.properties");
        String property = PropertyUtil.getInstance(propertyProvider).getProperty("app.name");
        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("app.custom", property)
        );
    }

    @Test
    @DisplayName("Test get non-existing property should throw KeyNotFoundException")
    void testGetNonExistingProperty() {
        assertThrows(KeyNotFoundException.class, () -> PropertyUtil.getInstance().getProperty("non.existing"));
    }

    @Test
    @DisplayName("Test get property with empty key should throw KeyNotFoundException")
    void testGetPropertyEmptyKey() {
        assertThrows(KeyNotFoundException.class, () -> PropertyUtil.getInstance().getProperty(""));
    }

    @Test
    @DisplayName("Test get property with null key should throw KeyNotFoundException")
    void testGetPropertyNullKey() {
        assertThrows(KeyNotFoundException.class, () -> PropertyUtil.getInstance().getProperty(null));
    }*/
}
