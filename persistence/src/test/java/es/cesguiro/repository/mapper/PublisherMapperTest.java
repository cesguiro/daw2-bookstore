package es.cesguiro.repository.mapper;

import es.cesguiro.dao.jpa.entity.PublisherEntityJpa;
import es.cesguiro.repository.model.PublisherEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublisherMapperTest {

    @Test
    @DisplayName("Test toPublisherEntity with null should return null")
    void testToPublisherEntityWithNull() {
        assertNull(PublisherMapper.toPublisherEntity(null));
    }

    @Test
    @DisplayName("Test map PublisherEntityJpa to toPublisherEntity")
    void testToPublisherEntityWithLocaleEn() {
        PublisherEntityJpa publisherEntityJpa = new PublisherEntityJpa();
        publisherEntityJpa.setName("Publisher Name");
        publisherEntityJpa.setSlug("publisher-name");

        PublisherEntity publisherEntity = PublisherMapper.toPublisherEntity(publisherEntityJpa);

        assertAll(
                () -> assertEquals("Publisher Name", publisherEntity.name()),
                () -> assertEquals("publisher-name", publisherEntity.slug())
        );
    }

}