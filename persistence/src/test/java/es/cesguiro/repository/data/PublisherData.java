package es.cesguiro.repository.data;

import es.cesguiro.dao.jpa.entity.PublisherEntityJpa;
import es.cesguiro.repository.model.PublisherEntity;
import lombok.Getter;

@Getter
public class PublisherData {

    private static final PublisherEntityJpa publisherEntityJpa = new PublisherEntityJpa(1L, "Publisher 1", "publisher-1");
    private static final PublisherEntity publisherEntity = new PublisherEntity("Publisher 1", "publisher-1");

    public static PublisherEntityJpa getPublisherEntityJpa() {
        return publisherEntityJpa;
    }

    public static PublisherEntity getPublisherEntity() {
        return publisherEntity;
    }
}
