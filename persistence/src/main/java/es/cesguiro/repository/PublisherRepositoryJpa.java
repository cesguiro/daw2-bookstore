package es.cesguiro.repository;

import es.cesguiro.dao.PublisherDao;
import es.cesguiro.repository.model.PublisherEntity;

import java.util.Optional;

public class PublisherRepositoryJpa implements PublisherRepository {

    private final PublisherDao publisherDao;

    public PublisherRepositoryJpa(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @Override
    public Optional<PublisherEntity> findByBookIsbn(String isbn) {
        return publisherDao.findByBookIsbn(isbn);
    }
}
