package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.model.Publisher;
import es.cesguiro.repository.model.PublisherEntity;
import es.cesguiro.usecase.book.query.model.PublisherQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublisherMapperTest {

    @Test
    @DisplayName("Test map PublisherEntity to Publisher")
    void toPublisher() {
        PublisherEntity publisherEntity = new PublisherEntity("name", "slug");
        Publisher publisher = PublisherMapper.toPublisher(publisherEntity);
        assertAll(
                () -> assertEquals(publisherEntity.name(), publisher.getName(), "Names should match"),
                () -> assertEquals(publisherEntity.slug(), publisher.getSlug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null PublisherEntity returns null Publisher")
    void toPublisherNull() {
        Publisher publisher = PublisherMapper.toPublisher(null);
        assertNull(publisher, "Mapping null PublisherEntity should return null Publisher");
    }

    @Test
    @DisplayName("Test map Publisher to PublisherDto")
    void toPublisherDto() {
        Publisher publisher = new Publisher("name", "slug");
        PublisherQuery publisherQuery = PublisherMapper.toPublisherDto(publisher);
        assertAll(
                () -> assertEquals(publisher.getName(), publisherQuery.name(), "Names should match"),
                () -> assertEquals(publisher.getSlug(), publisherQuery.slug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null Publisher returns null PublisherDto")
    void toPublisherDtoNull() {
        PublisherQuery publisherQuery = PublisherMapper.toPublisherDto(null);
        assertNull(publisherQuery, "Mapping null Publisher should return null PublisherDto");
    }

}