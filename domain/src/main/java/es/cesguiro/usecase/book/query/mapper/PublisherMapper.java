package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.model.Publisher;
import es.cesguiro.repository.model.PublisherEntity;
import es.cesguiro.usecase.book.query.model.PublisherDto;

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

    public static PublisherDto toPublisherDto(Publisher publisher) {
        if(publisher == null) {
            return null;
        }
        return new PublisherDto(
                publisher.getName(),
                publisher.getSlug()
        );
    }
}
