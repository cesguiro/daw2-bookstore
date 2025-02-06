package es.cesguiro.property;

import es.cesguiro.exception.KeyNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultPropertyProviderTest {

    DefaultPropertyProvider provider = new DefaultPropertyProvider();

    @Test
    @DisplayName("getProperty should return correct value if key exists in system properties")
    void testGetPropertyFromSystemProperties() {
        System.setProperty("app.name", "App Name");
        String property = provider.getProperty("app.name");

        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("App Name", property)
        );
        System.clearProperty("app.name");
    }

    @Test
    @DisplayName("getProperty should return correct value if key exists in application.properties")
    void testGetPropertyFromPropertiesFile() {
        String property = provider.getProperty("app.version");

        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("1.0", property)
        );
    }

    @Test
    @DisplayName("getProperty should return default value if key is not found")
    void testGetPropertyDefaultValue() {
        String property = provider.getProperty("app.nonexistent", "default");

        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("default", property)
        );
    }

    @Test
    @DisplayName("getProperty should throw KeyNotFoundException if key is empty")
    void testGetPropertyEmptyKey() {
        assertThrows(KeyNotFoundException.class, () -> provider.getProperty(""),
                "getProperty should throw KeyNotFoundException if key is empty");
    }

    @Test
    @DisplayName("getProperty should throw KeyNotFoundException if key is not found")
    void testGetPropertyKeyNotFound() {
        assertThrows(KeyNotFoundException.class, () -> provider.getProperty("app.nonexistent"),
                "getProperty should throw KeyNotFoundException if key is not found");
    }

    @Test
    @DisplayName("getProperty should throw KeyNotFoundException if key is null")
    void testGetPropertyNullKey() {
        assertThrows(KeyNotFoundException.class, () -> provider.getProperty(null),
                "getProperty should throw KeyNotFoundException if key is null");
    }

    @Test
    @DisplayName("getProperty should return correct value app.active.profile is set in application.properties and exists application-profile.properties")
    void testGetPropertyProfile() {
        String property = provider.getProperty("app.profile");

        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("test", property)
        );
    }
}