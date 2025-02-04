package es.cesguiro.repository.data;

import es.cesguiro.repository.model.PublisherEntity;
import lombok.Getter;

@Getter
public class PublisherData {

    private static final PublisherEntity publisherEntity = new PublisherEntity("Publisher 1", "publisher-1");

    public static PublisherEntity getPublisherEntity() {
        return publisherEntity;
    }
}
