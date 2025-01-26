package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.model.Publisher;
import es.cesguiro.repository.model.PublisherEntity;
import es.cesguiro.usecase.book.query.model.PublisherQuery;

public class PublisherMapper {

    public static Publisher toPublisher(PublisherEntity publisherEntity) {
        if(publisherEntity == null) {
            return null;
        }
        return new Publisher(
                publisherEntity.name(),
                publisherEntity.slug()
        );
    }

    public static PublisherQuery toPublisherDto(Publisher publisher) {
        if(publisher == null) {
            return null;
        }
        return new PublisherQuery(
                publisher.getName(),
                publisher.getSlug()
        );
    }
}
