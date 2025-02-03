package es.cesguiro.usecase.book.query.data;

import es.cesguiro.model.Publisher;
import es.cesguiro.repository.model.PublisherEntity;
import es.cesguiro.usecase.book.query.model.PublisherQuery;

import java.util.List;

public class PublisherData {

    private static final List<PublisherEntity> publisherEntities = List.of(
            new PublisherEntity("publisher1", "publisher1"),
            new PublisherEntity("publisher2", "publisher2"),
            new PublisherEntity("publisher3", "publisher3"),
            new PublisherEntity("publisher4",  "publisher4")
    );

    private static final List<Publisher> publishers = List.of(
            new Publisher("publisher1", "publisher1"),
            new Publisher("publisher2", "publisher2"),
            new Publisher("publisher3", "publisher3"),
            new Publisher("publisher4", "publisher4")
    );

    private static final List<PublisherQuery> publisherQueries = List.of(
            new PublisherQuery("publisher1", "publisher1"),
            new PublisherQuery("publisher2", "publisher2"),
            new PublisherQuery("publisher3", "publisher3"),
            new PublisherQuery("publisher4", "publisher4")
    );

    public static List<PublisherEntity> getPublisherEntities() {
        return List.copyOf(publisherEntities);
    }

    public static PublisherEntity getPublisherEntity(int position) {
        return publisherEntities.get(position);
    }

    public static List<Publisher> getPublishers() {
        return List.copyOf(publishers);
    }

    public static Publisher getPublisher(int position) {
        return publishers.get(position);
    }

    public static List<PublisherQuery> getPublisherQueries() {
        return List.copyOf(publisherQueries);
    }

    public static PublisherQuery getPublisherQuery(int position) {
        return publisherQueries.get(position);
    }
}
