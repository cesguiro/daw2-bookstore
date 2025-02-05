package es.cesguiro.model.book.query.mapper;

import es.cesguiro.data.PublisherData;
import es.cesguiro.model.book.query.PublisherResponse;
import es.cesguiro.usecase.book.query.model.PublisherQuery;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

class PublisherMapperTest {


    @Test
    @DisplayName("Test toPublisherResponse should return PublisherResponse")
    void testToPublisherResponse() {

        PublisherQuery publisherQuery = PublisherData.getPublisherQuery(1);

        PublisherResponse expected = PublisherData.getPublisherResponse(1);
        PublisherResponse result = PublisherMapper.toPublisherResponse(publisherQuery);

        assertAll(
                () -> assertEquals(expected.name(), result.name()),
                () -> assertEquals(expected.links().size(), result.links().size()),
                () -> assertEquals(expected.links().get("books"), result.links().get("books"))
        );
    }

    @Test
    @DisplayName("Test toPublisherResponse should return null")
    void testToPublisherResponseNull() {

        PublisherQuery publisherQuery = null;

        PublisherResponse result = PublisherMapper.toPublisherResponse(publisherQuery);

        assertEquals(null, result);
    }
}