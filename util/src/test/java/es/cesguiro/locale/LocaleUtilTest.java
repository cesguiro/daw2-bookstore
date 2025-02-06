package es.cesguiro.locale;

import es.cesguiro.exception.LocaleException;
import es.cesguiro.property.PropertyUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LocaleUtilTest {

    @Mock
    private LocaleProvider mockLocaleProvider;

    @BeforeEach
    void setUp() {
        LocaleUtil.setLocaleProvider(mockLocaleProvider);
    }

    @AfterEach
    void tearDown() {
        LocaleUtil.resetLocaleProvider();
    }

    @Test
    @DisplayName("Test getLanguage should return es")
    void testGetLanguageShouldReturnEs() {
        Mockito.when(mockLocaleProvider.getLanguage()).thenReturn("es");
        String language = LocaleUtil.getLanguage();
        assertEquals("es", language, "LanguageUtil should return the default language");
    }

    @Test
    @DisplayName("Test getLanguage should return en")
    void testGetLanguageShouldReturnEn() {
        Mockito.when(mockLocaleProvider.getLanguage()).thenReturn("en");
        String language = LocaleUtil.getLanguage();
        assertEquals("en", language, "LanguageUtil should return the default language");
    }

    @Test
    @DisplayName("Test formatDate should return dd/MM/yyyy")
    void testGetDateFormatShouldReturnDdMmYyyy() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Mockito.when(mockLocaleProvider.formatDate(date)).thenReturn("01/01/2021");
        String dateFormat = LocaleUtil.formatDate(date);
        assertEquals("01/01/2021", dateFormat, "LanguageUtil should return dd/mm/yyyy date format");
    }

    @Test
    @DisplayName("Test formatDate should return yyyy/MM/dd")
    void testGetDateFormatShouldReturnYyyyMmDd() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Mockito.when(mockLocaleProvider.formatDate(date)).thenReturn("2021/01/01");
        String dateFormat = LocaleUtil.formatDate(date);
        assertEquals("2021/01/01", dateFormat, "LanguageUtil should return yyyy/mm/dd date format");
    }

    @Test
    @DisplayName("Test set null LocaleProvider should throw LocaleException")
    void testSetLocaleProviderNull() {
        assertThrows(LocaleException.class, () -> LocaleUtil.setLocaleProvider(null),
                "getInstance with null LocaleProvider should throw LocaleException");
    }
}