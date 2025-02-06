package es.cesguiro.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultPropertyProviderTest {

    DefaultPropertyProvider provider = new DefaultPropertyProvider();

    @BeforeAll
    static void setUp() {
        System.setProperty("app.name", "name");
        System.getenv().put("app.alias", "alias");
    }

    @AfterEach
    void tearDown() {
        System.clearProperty("app.name");
        System.getenv().remove("app.alias");
    }

    @Test
    @DisplayName("get system property should return correct value")
    void testGetProperty() {


        String property = provider.getProperty("app.name");

        assertAll(
                () -> assertNotNull(property),
                () -> assertEquals(System.getProperty("name"), property)
        );
    }


}