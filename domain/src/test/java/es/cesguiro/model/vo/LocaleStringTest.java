package es.cesguiro.model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocaleStringTest {

    @Test
    @DisplayName("Test getValue(es) method should return es value")
    void testGetValueEs() {
        LocaleString localeString = new LocaleString("es", "en");
        assertEquals("es", localeString.getValue("es"));
    }

    @Test
    @DisplayName("Test getValue(en) method should return en value")
    void testGetValueEn() {
        LocaleString localeString = new LocaleString("es", "en");
        assertEquals("en", localeString.getValue("en"));
    }

}