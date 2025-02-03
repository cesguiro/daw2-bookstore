package es.cesguiro.model;

import es.cesguiro.model.vo.LocaleString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookTest{

    @Test
    @DisplayName("Test create Book")
    void testCreateBook(){
        // Arrange
        String isbn = "978-84-376-0494-7";
        String titleEs = "titleEs";
        String titleEn = "titleEn";
        LocaleString title = new LocaleString(titleEs, titleEn);
        String synopsisEs = "biographyEs";
        String synopsisEn = "biographyEn";
        LocaleString synopsis = new LocaleString(synopsisEs, synopsisEn);
        BigDecimal basePrice = new BigDecimal("10.00");
        double discountPercentage = 5.00;
        String cover = "978-84-376-0494-7.jpg";
        LocalDate publicationDate = LocalDate.of(2021, 1, 1);
        // Act
        Book book = new Book(isbn, title, synopsis, basePrice, discountPercentage, cover, publicationDate);
        // Assert
        assertAll(
            () -> assertEquals(isbn, book.getIsbn(), "ISBNs should match"),
            () -> assertEquals(titleEs, book.getTitle("es"), "Title ES should match"),
            () -> assertEquals(titleEn, book.getTitle("en"), "Title EN should match"),
            () -> assertEquals(synopsisEs, book.getSynopsis("es"), "Synopse ES should match"),
            () -> assertEquals(synopsisEn, book.getSynopsis("en"), "Synopse EN should match"),
            () -> assertEquals(basePrice, book.getBasePrice(), "Base prices should match"),
            () -> assertEquals(discountPercentage, book.getDiscountPercentage(), "Discounts should match"),
            () -> assertEquals(cover, book.getCover(), "Covers should match"),
            () -> assertEquals(publicationDate, book.getPublicationDate(), "Publication dates should match")
        );
    }

    @Test
    @DisplayName("Test get title in Spanish")
    void testGetTitleInSpanish(){
        String language = "es";
        String isbn = "978-84-376-0494-7";
        LocaleString title = new LocaleString("titleEs", "titleEn");
        LocaleString synopsis = new LocaleString("synopsisEs", "synopsisEn");
        BigDecimal basePrice = new BigDecimal("10.00");
        double discountPercentage = 5.00;
        String cover = "978-84-376-0494-7.jpg";
        LocalDate publicationDate = LocalDate.of(2021, 1, 1);

        Book book = new Book(isbn, title, synopsis, basePrice, discountPercentage, cover, publicationDate);

        assertEquals("titleEs", book.getTitle(language), "Titles should match");
    }

    @Test
    @DisplayName("Test get title in English")
    void testGetTitleInEnglish(){
        String language = "en";
        String isbn = "978-84-376-0494-7";
        LocaleString title = new LocaleString("titleEs", "titleEn");
        LocaleString synopsis = new LocaleString("synopsisEs", "synopsisEn");
        BigDecimal basePrice = new BigDecimal("10.00");
        double discountPercentage = 5.00;
        String cover = "978-84-376-0494-7.jpg";
        LocalDate publicationDate = LocalDate.of(2021, 1, 1);

        Book book = new Book(isbn, title, synopsis, basePrice, discountPercentage, cover, publicationDate);

        assertEquals("titleEn", book.getTitle(language), "Titles should match");
    }

    @Test
    @DisplayName("Test get synopsis in Spanish")
    void testGetSynopsisInSpanish(){
        String language = "es";
        String isbn = "978-84-376-0494-7";
        LocaleString title = new LocaleString("titleEs", "titleEn");
        LocaleString synopsis = new LocaleString("synopsisEs", "synopsisEn");
        BigDecimal basePrice = new BigDecimal("10.00");
        double discountPercentage = 5.00;
        String cover = "978-84-376-0494-7.jpg";
        LocalDate publicationDate = LocalDate.of(2021, 1, 1);

        Book book = new Book(isbn, title, synopsis, basePrice, discountPercentage, cover, publicationDate);

        assertEquals("synopsisEs", book.getSynopsis(language), "Synopses should match");
    }

    @Test
    @DisplayName("Test get synopsis in English")
    void testGetSynopsisInEnglish(){
        String language = "en";
        String isbn = "978-84-376-0494-7";
        LocaleString title = new LocaleString("titleEs", "titleEn");
        LocaleString synopsis = new LocaleString("synopsisEs", "synopsisEn");
        BigDecimal basePrice = new BigDecimal("10.00");
        double discountPercentage = 5.00;
        String cover = "978-84-376-0494-7.jpg";
        LocalDate publicationDate = LocalDate.of(2021, 1, 1);

        Book book = new Book(isbn, title, synopsis, basePrice, discountPercentage, cover, publicationDate);

        assertEquals("synopsisEn", book.getSynopsis(language), "Synopses should match");
    }

    @Test
    @DisplayName("Test get title with non supported language should return titleEs")
    void testGetTitleWithNonSupportedLanguage(){
        String language = "fr";
        String isbn = "978-84-376-0494-7";
        LocaleString title = new LocaleString("titleEs", "titleEn");
        LocaleString synopsis = new LocaleString("synopsisEs", "synopsisEn");
        BigDecimal basePrice = new BigDecimal("10.00");
        double discountPercentage = 5.00;
        String cover = "978-84-376-0494-7.jpg";
        LocalDate publicationDate = LocalDate.of(2021, 1, 1);

        Book book = new Book(isbn, title, synopsis, basePrice, discountPercentage, cover, publicationDate);

        assertEquals("titleEs", book.getTitle(language), "Titles should match");
    }

    @Test
    @DisplayName("Test get synopsis with non supported language should return synopsisEs")
    void testGetSynopsisWithNonSupportedLanguage(){
        String language = "fr";
        String isbn = "978-84-376-0494-7";
        LocaleString title = new LocaleString("titleEs", "titleEn");
        LocaleString synopsis = new LocaleString("synopsisEs", "synopsisEn");
        BigDecimal basePrice = new BigDecimal("10.00");
        double discountPercentage = 5.00;
        String cover = "978-84-376-0494-7.jpg";
        LocalDate publicationDate = LocalDate.of(2021, 1, 1);

        Book book = new Book(isbn, title, synopsis, basePrice, discountPercentage, cover, publicationDate);

        assertEquals("synopsisEs", book.getSynopsis(language), "Synopses should match");
    }

    @Test
    @DisplayName("Test discount price")
    void testDiscountPrice(){
        // Arrange
        String isbn = "978-84-376-0494-7";
        LocaleString title = new LocaleString("titleEs", "titleEn");
        LocaleString synopsis = new LocaleString("synopsisEs", "synopsisEn");
        BigDecimal basePrice = new BigDecimal("10.00");
        double discountPercentage = 5.00;
        String cover = "978-84-376-0494-7.jpg";
        LocalDate publicationDate = LocalDate.of(2021, 1, 1);
        // Act
        Book book = new Book(isbn, title, synopsis, basePrice, discountPercentage, cover, publicationDate);
        // Assert
        assertEquals(0 ,book.calculateFinalPrice().compareTo(new BigDecimal("9.50")), "Discount price should match");
    }

    @Test
    @DisplayName("Test discount price with zero discount")
    void testDiscountPriceWithZeroDiscount(){
        // Arrange
        String isbn = "978-84-376-0494-7";
        LocaleString title = new LocaleString("titleEs", "titleEn");
        LocaleString synopsis = new LocaleString("synopsisEs", "synopsisEn");
        BigDecimal basePrice = new BigDecimal("10.00");
        double discountPercentage = 0.00;
        String cover = "978-84-376-0494-7.jpg";
        LocalDate publicationDate = LocalDate.of(2021, 1, 1);
        // Act
        Book book = new Book(isbn, title, synopsis, basePrice, discountPercentage, cover, publicationDate);
        // Assert
        assertEquals(0, book.calculateFinalPrice().compareTo(new BigDecimal("10")), "Discount price should match");
    }

    @Test
    @DisplayName("Test round discount price")
    void testRoundDiscountPrice(){
        // Arrange
        String isbn = "978-84-376-0494-7";
        LocaleString title = new LocaleString("titleEs", "titleEn");
        LocaleString synopsis = new LocaleString("synopsisEs", "synopsisEn");
        BigDecimal basePrice = new BigDecimal("10.00");
        double discountPercentage = 33;
        String cover = "978-84-376-0494-7.jpg";
        LocalDate publicationDate = LocalDate.of(2021, 1, 1);
        // Act
        Book book = new Book(isbn, title, synopsis, basePrice, discountPercentage, cover, publicationDate);
        // Assert
        assertEquals(0, book.calculateFinalPrice().compareTo(new BigDecimal("6.7")), "Discount price should match");
    }

    @Test
    @DisplayName("Test discount price rounding up")
    void testDiscountPriceRoundingUp(){
        // Arrange
        String isbn = "978-84-376-0494-7";
        LocaleString title = new LocaleString("titleEs", "titleEn");
        LocaleString synopsis = new LocaleString("synopsisEs", "synopsisEn");
        BigDecimal basePrice = new BigDecimal("10.00");
        double discountPercentage = 33.33;
        String cover = "978-84-376-0494-7.jpg";
        LocalDate publicationDate = LocalDate.of(2021, 1, 1);
        // Act
        Book book = new Book(isbn, title, synopsis, basePrice, discountPercentage, cover, publicationDate);
        // Assert
        assertEquals(0, book.calculateFinalPrice().compareTo(new BigDecimal("6.67")), "Discount price should match");
    }

    @Test
    @DisplayName("Test discount price rounding down")
    void testDiscountPriceRoundingDown(){
        // Arrange
        String isbn = "978-84-376-0494-7";
        LocaleString title = new LocaleString("titleEs", "titleEn");
        LocaleString synopsis = new LocaleString("synopsisEs", "synopsisEn");
        BigDecimal basePrice = new BigDecimal("10.00");
        double discountPercentage = 33.66;
        String cover = "978-84-376-0494-7.jpg";
        LocalDate publicationDate = LocalDate.of(2021, 1, 1);
        // Act
        Book book = new Book(isbn, title, synopsis, basePrice, discountPercentage, cover, publicationDate);
        // Assert
        assertEquals(0, book.calculateFinalPrice().compareTo(new BigDecimal("6.63")), "Discount price should match");
    }

}