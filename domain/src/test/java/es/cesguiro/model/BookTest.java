package es.cesguiro.model;

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
        String title = "title";
        String synopsis = "synopsis";
        BigDecimal basePrice = new BigDecimal("10.00");
        double discountPercentage = 5.00;
        String cover = "978-84-376-0494-7.jpg";
        LocalDate publicationDate = LocalDate.of(2021, 1, 1);
        // Act
        Book book = new Book(isbn, title, synopsis, basePrice, discountPercentage, cover, publicationDate);
        // Assert
        assertAll(
            () -> assertEquals(isbn, book.getIsbn(), "ISBNs should match"),
            () -> assertEquals(title, book.getTitle(), "Titles should match"),
            () -> assertEquals(synopsis, book.getSynopsis(), "Synopses should match"),
            () -> assertEquals(basePrice, book.getBasePrice(), "Base prices should match"),
            () -> assertEquals(discountPercentage, book.getDiscountPercentage(), "Discounts should match"),
            () -> assertEquals(cover, book.getCover(), "Covers should match"),
            () -> assertEquals(publicationDate, book.getPublicationDate(), "Publication dates should match")
        );
    }

    @Test
    @DisplayName("Test discount price")
    void testDiscountPrice(){
        // Arrange
        String isbn = "978-84-376-0494-7";
        String title = "title";
        String synopsis = "synopsis";
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
        String title = "title";
        String synopsis = "synopsis";
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
        String title = "title";
        String synopsis = "synopsis";
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
        String title = "title";
        String synopsis = "synopsis";
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
        String title = "title";
        String synopsis = "synopsis";
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