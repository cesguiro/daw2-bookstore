package es.cesguiro.property;

import es.cesguiro.exception.AppFileNotFoundException;
import es.cesguiro.exception.KeyNotFoundException;
import es.cesguiro.exception.PropertyUtilException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PropertyUtilTest {

    @Mock
    private PropertyProvider mockPropertyProvider;

    @BeforeEach
    void resetPropertyUtil() {
        PropertyUtil.resetInstance();
    }

    @Test
    @DisplayName("Test getInstance should return the same instance")
    void testSingleton() {
        PropertyUtil instance1 = PropertyUtil.getInstance();
        PropertyUtil instance2 = PropertyUtil.getInstance();

        assertSame(instance1, instance2, "getInstance should return the same instance");
    }

    @Test
    @DisplayName("Test resetInstance should reset the singleton instance")
    void testResetInstance() {
        PropertyUtil propertyUtil1 = PropertyUtil.getInstance();
        PropertyUtil.resetInstance();
        PropertyUtil propertyUtil2 = PropertyUtil.getInstance();
        assertNotSame(propertyUtil1, propertyUtil2, "resetInstance should create a new instance");
    }

    @Test
    @DisplayName("Test getInstance with null PropertyProvider should throw PropertyUtilException")
    void testSetPropertyProviderNull() {
        assertThrows(PropertyUtilException.class, () -> PropertyUtil.getInstance((PropertyProvider) null),
                "getInstance with null PropertyProvider should throw PropertyUtilException");
    }

    @Test
    @DisplayName("Test getInstance with empty property file should throw PropertyUtilException")
    void testSetPropertyProviderEmpty() {
        assertThrows(PropertyUtilException.class, () -> PropertyUtil.getInstance("", mockPropertyProvider),
                "getInstance with empty property file should throw PropertyUtilException");
    }

    @Test
    @DisplayName("Test getProperty should throw AppFileNotFoundException if properties file is missing")
    void testGetPropertyAppFileNotFoundException() {
        assertThrows(AppFileNotFoundException.class, () -> PropertyUtil.getInstance("non-existing.properties", mockPropertyProvider),
                "getProperty should throw AppFileNotFoundException if application.properties is missing");
    }

    @Test
    @DisplayName("Test getProperty should return correct value from custom PropertyProvider")
    void testGetProperty() {
        PropertyUtil propertyUtil = PropertyUtil.getInstance(mockPropertyProvider);
        Mockito.when(mockPropertyProvider.getProperty("app.name")).thenReturn("util");

        String property = propertyUtil.getProperty("app.name");

        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("util", property)
        );
    }

    @Test
    @DisplayName("Test get non-existing property should throw KeyNotFoundException")
    void testGetPropertyKeyNotFound() {
        PropertyUtil propertyUtil = PropertyUtil.getInstance(mockPropertyProvider);
        Mockito.when(mockPropertyProvider.getProperty("app.name")).thenThrow(KeyNotFoundException.class);

        assertThrows(KeyNotFoundException.class, () -> propertyUtil.getProperty("app.name"),
                "getProperty should throw KeyNotFoundException if key is not found");
    }

    @Test
    @DisplayName("Test get property with empty key should throw KeyNotFoundException")
    void testGetPropertyEmptyKey() {
        PropertyUtil propertyUtil = PropertyUtil.getInstance(mockPropertyProvider);
        Mockito.when(mockPropertyProvider.getProperty("")).thenThrow(KeyNotFoundException.class);

        assertThrows(KeyNotFoundException.class, () -> propertyUtil.getProperty(""),
                "getProperty should throw KeyNotFoundException if key is empty");
    }

    @Test
    @DisplayName("Test get property with null key should throw KeyNotFoundException")
    void testGetPropertyNullKey() {
        PropertyUtil propertyUtil = PropertyUtil.getInstance(mockPropertyProvider);
        Mockito.when(mockPropertyProvider.getProperty(null)).thenThrow(KeyNotFoundException.class);

        assertThrows(KeyNotFoundException.class, () -> propertyUtil.getProperty(null),
                "getProperty should throw KeyNotFoundException if key is null");
    }


}