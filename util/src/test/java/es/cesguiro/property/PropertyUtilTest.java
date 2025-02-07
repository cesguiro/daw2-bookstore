package es.cesguiro.property;

import es.cesguiro.exception.KeyNotFoundException;
import es.cesguiro.exception.PropertyUtilException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyUtilTest {

    @Mock
    private PropertyProvider mockPropertyProvider;

    @BeforeEach
    void setUp() {
        PropertyUtil.setPropertyProvider(mockPropertyProvider);
    }

    @AfterEach
    void tearDown() {
        PropertyUtil.resetPropertyProvider();
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
        when(mockPropertyProvider.getProperty("app.name")).thenReturn("App Name");
        String property = PropertyUtil.getPropertyProvider().getProperty("app.name");

        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("App Name", property)
        );
    }

    @Test
    @DisplayName("Test get non-existing property should throw KeyNotFoundException")
    void testGetPropertyKeyNotFound() {
        when(mockPropertyProvider.getProperty("app.name")).thenThrow(KeyNotFoundException.class);

        assertThrows(KeyNotFoundException.class, () -> PropertyUtil.getPropertyProvider().getProperty("app.name"),
                "getProperty should throw KeyNotFoundException if key is not found");
    }

    @Test
    @DisplayName("Test get property with empty key should throw KeyNotFoundException")
    void testGetPropertyEmptyKey() {
        when(mockPropertyProvider.getProperty("")).thenThrow(KeyNotFoundException.class);
        assertThrows(KeyNotFoundException.class, () -> PropertyUtil.getPropertyProvider().getProperty(""),
                "getProperty should throw KeyNotFoundException if key is empty");
    }

    @Test
    @DisplayName("Test get property with null key should throw KeyNotFoundException")
    void testGetPropertyNullKey() {
        when(mockPropertyProvider.getProperty(null)).thenThrow(KeyNotFoundException.class);
        assertThrows(KeyNotFoundException.class, () -> PropertyUtil.getPropertyProvider().getProperty(null),
                "getProperty should throw KeyNotFoundException if key is null");
    }

    @Test
    @DisplayName("Test getProperty should return default value if key is not found")
    void testGetPropertyDefaultValue() {
        when(mockPropertyProvider.getProperty("app.nonexistent", "default")).thenReturn("default");
        String property = PropertyUtil.getPropertyProvider().getProperty("app.nonexistent", "default");

        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals("default", property)
        );
    }

}