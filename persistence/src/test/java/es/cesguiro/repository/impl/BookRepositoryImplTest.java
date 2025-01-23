package es.cesguiro.repository.impl;

import es.cesguiro.dao.jpa.entity.BookEntityJpa;
import es.cesguiro.dao.jpa.repository.BookRepositoryJpa;
import es.cesguiro.repository.model.BookEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookRepositoryImplTest {

    @Mock
    BookRepositoryJpa bookRepositoryJpa;

    @InjectMocks
    BookRepositoryImpl bookRepositoryImpl;

    @Test
    @DisplayName("Test findAll method returns a list of BookEntity")
    void testFindAll() {
        int page = 0;
        int size = 10;

        BookEntityJpa bookEntityJpa1 = new BookEntityJpa(
                1L,
                "123",
                "TitleEs 1",
                "TitleEn 1",
                "SynopsisEs 1",
                "SynopsisEn 1",
                new BigDecimal("10.0"),
                5.0,
                "cover",
                null
        );
        BookEntityJpa bookEntityJpa2 = new BookEntityJpa(
                2L,
                "456",
                "TitleEs 2",
                "TitleEn 2",
                "SynopsisEs 2",
                "SynopsisEn 2",
                new BigDecimal("20.0"),
                10.0,
                "cover",
                null
        );

        when(bookRepositoryJpa.findAll()).thenReturn(List.of(bookEntityJpa1, bookEntityJpa2));

        List<BookEntity> bookEntities = bookRepositoryImpl.findAll(page, size);
        assertAll(
                () -> assertEquals(2, bookEntities.size()),
                () -> assertEquals("123", bookEntities.getFirst().isbn()),
                () -> assertEquals("TitleEs 1", bookEntities.getFirst().title()),
                () -> assertEquals("SynopsisEs 1", bookEntities.getFirst().synopsis()),
                () -> assertEquals(new BigDecimal("10.0"), bookEntities.getFirst().basePrice()),
                () -> assertEquals(5.0, bookEntities.getFirst().discountPercentage()),
                () -> assertEquals("cover", bookEntities.getFirst().cover()),
                () -> assertEquals("456", bookEntities.get(1).isbn()),
                () -> assertEquals("TitleEs 2", bookEntities.get(1).title()),
                () -> assertEquals("SynopsisEs 2", bookEntities.get(1).synopsis()),
                () -> assertEquals(new BigDecimal("20.0"), bookEntities.get(1).basePrice()),
                () -> assertEquals(10.0, bookEntities.get(1).discountPercentage()),
                () -> assertEquals("cover", bookEntities.get(1).cover())
        );

    }

}