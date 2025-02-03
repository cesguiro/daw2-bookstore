package es.cesguiro.repository.mapper;

import es.cesguiro.dao.jpa.entity.PublisherEntityJpa;
import es.cesguiro.repository.model.PublisherEntity;

public class PublisherMapper {

        private PublisherMapper() {
        }

        public static PublisherEntity toPublisherEntity(PublisherEntityJpa publisherEntityJpa) {
            if (publisherEntityJpa == null) {
                return null;
            }
            return new PublisherEntity(
                    publisherEntityJpa.getName(),
                    publisherEntityJpa.getSlug()
            );
        }
}
