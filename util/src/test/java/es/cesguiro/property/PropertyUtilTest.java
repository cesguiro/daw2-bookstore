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

    /*@Mock
    private PropertyProvider mockPropertyProvider;*/

    @BeforeAll
    static void setUp() {
        PropertyProvider mockPropertyProvider = Mockito.mock(PropertyProvider.class);
        PropertyUtil.setPropertyProvider(mockPropertyProvider);
    }


    @Test
    @DisplayName("Test set null PropertyProvider should throw PropertyUtilException")
    void testSetPropertyProviderNull() {
        assertThrows(PropertyUtilException.class, () -> PropertyUtil.setPropertyProvider(null),
                "getInstance with null PropertyProvider should throw PropertyUtilException");
    }


    @Test
    @DisplayName("Test getProperty should return correct value if key exists")
    void testGetProperty() {
        String property = PropertyUtil.getProperty("app.name");

        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("util", property)
        );
    }

    @Test
    @DisplayName("Test get non-existing property should throw KeyNotFoundException")
    void testGetPropertyKeyNotFound() {
        assertThrows(KeyNotFoundException.class, () -> PropertyUtil.getProperty("app.name"),
                "getProperty should throw KeyNotFoundException if key is not found");
    }

    @Test
    @DisplayName("Test get property with empty key should throw KeyNotFoundException")
    void testGetPropertyEmptyKey() {
        assertThrows(KeyNotFoundException.class, () -> PropertyUtil.getProperty(""),
                "getProperty should throw KeyNotFoundException if key is empty");
    }

    @Test
    @DisplayName("Test get property with null key should throw KeyNotFoundException")
    void testGetPropertyNullKey() {
        assertThrows(KeyNotFoundException.class, () -> PropertyUtil.getProperty(null),
                "getProperty should throw KeyNotFoundException if key is null");
    }


}