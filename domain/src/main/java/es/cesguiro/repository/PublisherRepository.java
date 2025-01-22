package es.cesguiro.repository;

import es.cesguiro.repository.model.PublisherEntity;

import java.util.Optional;

public interface PublisherRepository {

    Optional<PublisherEntity> findByBookIsbn(String isbn);
}
