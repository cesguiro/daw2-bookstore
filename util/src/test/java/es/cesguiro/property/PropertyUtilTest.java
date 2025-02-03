package es.cesguiro.property;

import es.cesguiro.exception.KeyNotFoundException;
import es.cesguiro.exception.PropertyUtilException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PropertyUtilTest {

    @Mock
    private PropertyProvider mockPropertyProvider;

    @AfterEach
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
    @DisplayName("Test getInstance with no PropertyProvider should return instance with DefaultPropertyProvider")
    void testSetPropertyProviderDefault() {
        PropertyUtil propertyUtil = PropertyUtil.getInstance();
        assertEquals(DefaultPropertyProvider.class, propertyUtil.getPropertyProvider().getClass(),
                "getInstance with no PropertyProvider should return instance with DefaultPropertyProvider");
    }

    @Test
    @DisplayName("Test getInstance with custom PropertyProvider should return instance with custom PropertyProvider")
    void testSetPropertyProviderCustom() {
        PropertyUtil propertyUtil = PropertyUtil.getInstance(mockPropertyProvider);
        assertEquals(mockPropertyProvider, propertyUtil.getPropertyProvider(),
                "getInstance with custom PropertyProvider should return instance with custom PropertyProvider");
    }


    @Test
    @DisplayName("Test getProperty should return correct value if key exists")
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