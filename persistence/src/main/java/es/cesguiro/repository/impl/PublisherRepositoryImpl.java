package es.cesguiro.repository.impl;

import es.cesguiro.repository.PublisherRepository;
import es.cesguiro.repository.model.PublisherEntity;

import java.util.Optional;

public class PublisherRepositoryImpl implements PublisherRepository {

    @Override
    public Optional<PublisherEntity> findByBookIsbn(String s) {
        return Optional.empty();
    }
}
