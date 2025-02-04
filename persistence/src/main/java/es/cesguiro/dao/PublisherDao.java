package es.cesguiro.dao;

import es.cesguiro.dao.jpa.entity.PublisherEntityJpa;
import es.cesguiro.repository.model.PublisherEntity;

import java.util.Optional;

public interface PublisherDao {

    Optional<PublisherEntity> findByBookIsbn(String isbn);
}
