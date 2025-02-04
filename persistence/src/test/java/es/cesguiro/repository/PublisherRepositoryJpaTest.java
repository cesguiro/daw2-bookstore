package es.cesguiro.repository;

import es.cesguiro.dao.PublisherDao;
import es.cesguiro.dao.jpa.entity.PublisherEntityJpa;
import es.cesguiro.repository.data.PublisherData;
import es.cesguiro.repository.model.PublisherEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublisherRepositoryJpaTest {

    @Mock
    PublisherDao publisherDao;

    @InjectMocks
    PublisherRepositoryJpa publisherRepositoryJpa;

    @Test
    @DisplayName("Test findByBookIsbn method returns Optional<PublisherEntity>")
    void testFindAll() {
        String isbn = "123";

        PublisherEntity publisherEntity = PublisherData.getPublisherEntity();

        when(publisherDao.findByBookIsbn(isbn)).thenReturn(Optional.of(publisherEntity));

        Optional<PublisherEntity> expected = Optional.of(publisherEntity);
        Optional<PublisherEntity> result = publisherRepositoryJpa.findByBookIsbn(isbn);

        assertAll(
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(expected.get().name(), result.get().name()),
                () -> assertEquals(expected.get().slug(), result.get().slug())
        );
    }

    @Test
    @DisplayName("Test findByBookIsbn method returns Optional.empty()")
    void testFindAllEmpty() {
        String isbn = "123";

        when(publisherDao.findByBookIsbn(isbn)).thenReturn(Optional.empty());

        assertTrue(publisherRepositoryJpa.findByBookIsbn(isbn).isEmpty());
    }

}