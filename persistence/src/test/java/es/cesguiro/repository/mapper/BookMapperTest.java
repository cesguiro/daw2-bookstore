package es.cesguiro.repository.mapper;

import es.cesguiro.dao.jpa.entity.BookEntityJpa;
import es.cesguiro.repository.model.BookEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {

    @Test
    @DisplayName("Test toBookEntity with null should return null")
    void testToBookEntityWithNull() {
        assertNull(BookMapper.toBookEntity(null));
    }

    @Test
    @DisplayName("Test map BookEntityJpa to BookEntity")
    void testToBookEntityWithLocaleEn() {
        BookEntityJpa bookEntityJpa = new BookEntityJpa();
        bookEntityJpa.setIsbn("978-3-16-148410-0");
        bookEntityJpa.setTitleEn("Title in English");
        bookEntityJpa.setTitleEs("Título en Español");
        bookEntityJpa.setSynopsisEn("Synopsis in English");
        bookEntityJpa.setSynopsisEs("Sinopsis en Español");
        bookEntityJpa.setBasePrice(new BigDecimal("10.0"));
        bookEntityJpa.setDiscountPercentage(0.0);
        bookEntityJpa.setCover("cover.jpg");

        Locale.setDefault(Locale.ENGLISH);
        BookEntity bookEntity = BookMapper.toBookEntity(bookEntityJpa);

        assertAll(
                () -> assertEquals("978-3-16-148410-0", bookEntity.isbn()),
                () -> assertEquals("Título en Español", bookEntity.titleEs()),
                () -> assertEquals("Title in English", bookEntity.titleEn()),
                () -> assertEquals("Sinopsis en Español", bookEntity.synopsisEs()),
                () -> assertEquals("Synopsis in English", bookEntity.synopsisEn()),
                () -> assertEquals(0, bookEntity.basePrice().compareTo(new BigDecimal("10.0"))),
                () -> assertEquals(0.0, bookEntity.discountPercentage()),
                () -> assertEquals("cover.jpg", bookEntity.cover()),
                () -> assertEquals(0.0, bookEntity.discountPercentage()),
                () -> assertEquals("cover.jpg", bookEntity.cover())
        );
    }
    

}