package es.cesguiro.property;

import es.cesguiro.exception.AppFileNotFoundException;
import es.cesguiro.exception.KeyNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;

class PropertyUtilTest {

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
    @DisplayName("Test setPropertyProvider should throw IllegalArgumentException if null is passed")
    void testSetPropertyProviderNull() {
        PropertyUtil propertyUtil = PropertyUtil.getInstance();

        // Verifica que el proveedor de propiedades no puede ser nulo
        assertThrows(IllegalArgumentException.class, () -> propertyUtil.setPropertyProvider(null),
                "setPropertyProvider should throw IllegalArgumentException if null is passed");
    }

    @Test
    @DisplayName("Test getProperty should throw AppFileNotFoundException if application.properties is missing")
    void testGetPropertyAppFileNotFoundException() {
        PropertyProvider mockPropertyProvider = Mockito.mock(PropertyProvider.class);

        // Simulamos que al intentar cargar el archivo, se lanza la excepción AppFileNotFoundException
        doThrow(new AppFileNotFoundException("application.properties"))
                .when(mockPropertyProvider).getProperty(anyString());

        PropertyUtil propertyUtil = PropertyUtil.getInstance();
        propertyUtil.setPropertyProvider(mockPropertyProvider);

        assertThrows(AppFileNotFoundException.class, () -> propertyUtil.getProperty("app.name"),
                "getProperty should throw AppFileNotFoundException if application.properties is missing");
    }

    @Test
    @DisplayName("Test getProperty should return correct value from default PropertyProvider")
    void testGetProperty() {

        PropertyProvider mockPropertyProvider = Mockito.mock(PropertyProvider.class);
        PropertyUtil propertyUtil = PropertyUtil.getInstance();
        propertyUtil.setPropertyProvider(mockPropertyProvider);
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
        PropertyProvider mockPropertyProvider = Mockito.mock(PropertyProvider.class);
        PropertyUtil propertyUtil = PropertyUtil.getInstance();
        propertyUtil.setPropertyProvider(mockPropertyProvider);
        Mockito.when(mockPropertyProvider.getProperty("app.name")).thenThrow(KeyNotFoundException.class);

        assertThrows(KeyNotFoundException.class, () -> propertyUtil.getProperty("app.name"),
                "getProperty should throw KeyNotFoundException if key is not found");
    }

    @Test
    @DisplayName("Test get property with empty key should throw KeyNotFoundException")
    void testGetPropertyEmptyKey() {
        PropertyProvider mockPropertyProvider = Mockito.mock(PropertyProvider.class);
        PropertyUtil propertyUtil = PropertyUtil.getInstance();
        propertyUtil.setPropertyProvider(mockPropertyProvider);
        Mockito.when(mockPropertyProvider.getProperty("")).thenThrow(KeyNotFoundException.class);

        assertThrows(KeyNotFoundException.class, () -> propertyUtil.getProperty(""),
                "getProperty should throw KeyNotFoundException if key is empty");
    }

    @Test
    @DisplayName("Test get property with null key should throw KeyNotFoundException")
    void testGetPropertyNullKey() {
        PropertyProvider mockPropertyProvider = Mockito.mock(PropertyProvider.class);
        PropertyUtil propertyUtil = PropertyUtil.getInstance();
        propertyUtil.setPropertyProvider(mockPropertyProvider);
        Mockito.when(mockPropertyProvider.getProperty(null)).thenThrow(KeyNotFoundException.class);

        assertThrows(KeyNotFoundException.class, () -> propertyUtil.getProperty(null),
                "getProperty should throw KeyNotFoundException if key is null");
    }


}