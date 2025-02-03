package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.model.Publisher;
import es.cesguiro.repository.model.PublisherEntity;
import es.cesguiro.usecase.book.query.data.PublisherData;
import es.cesguiro.usecase.book.query.model.PublisherQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublisherMapperTest {

    @Test
    @DisplayName("Test map PublisherEntity to Publisher")
    void toPublisher() {
        PublisherEntity publisherEntity = PublisherData.getPublisherEntity(0);
        Publisher result = PublisherMapper.toPublisher(publisherEntity);
        assertAll(
                () -> assertEquals(publisherEntity.name(), result.getName(), "Names should match"),
                () -> assertEquals(publisherEntity.slug(), result.getSlug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null PublisherEntity returns null Publisher")
    void toPublisherNull() {
        Publisher result = PublisherMapper.toPublisher(null);
        assertNull(result, "Mapping null PublisherEntity should return null Publisher");
    }

    @Test
    @DisplayName("Test map Publisher to PublisherDto")
    void toPublisherQuery() {
        Publisher result = PublisherData.getPublishers().get(0);
        PublisherQuery publisherQuery = PublisherMapper.toPublisherQuery(result);
        assertAll(
                () -> assertEquals(result.getName(), publisherQuery.name(), "Names should match"),
                () -> assertEquals(result.getSlug(), publisherQuery.slug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null Publisher returns null PublisherDto")
    void toPublisherQueryNull() {
        PublisherQuery result = PublisherMapper.toPublisherQuery(null);
        assertNull(result, "Mapping null Publisher should return null PublisherDto");
    }

}