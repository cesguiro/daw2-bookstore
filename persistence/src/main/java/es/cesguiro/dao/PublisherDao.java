package es.cesguiro.dao;

import es.cesguiro.dao.jpa.entity.PublisherEntityJpa;

import java.util.Optional;

public interface PublisherDao {

    Optional<PublisherEntityJpa> findByBookIsbn(String isbn);
}
