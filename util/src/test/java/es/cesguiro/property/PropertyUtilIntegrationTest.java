package es.cesguiro.property;

import es.cesguiro.exception.KeyNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyUtilIntegrationTest {



    @Test
    @DisplayName("Test getProperty should return correct value from default PropertyProvider")
    void testGetProperty() {
        String property = PropertyUtil.getInstance().getProperty("app.name");
        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("util", property)
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
    }
}
