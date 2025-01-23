package es.cesguiro.repository.impl;

import es.cesguiro.dao.jpa.entity.BookEntityJpa;
import es.cesguiro.dao.jpa.repository.BookRepositoryJpa;
import es.cesguiro.repository.BookRepository;
import es.cesguiro.repository.mapper.BookMapper;
import es.cesguiro.repository.model.BookEntity;

import java.util.List;
import java.util.Optional;

public class BookRepositoryImpl implements BookRepository {

    private final BookRepositoryJpa bookRepositoryJpa;

    public BookRepositoryImpl(BookRepositoryJpa bookRepositoryJpa) {
        this.bookRepositoryJpa = bookRepositoryJpa;
    }

    @Override
    public List<BookEntity> findAll(int page, int size) {
        return bookRepositoryJpa
                .findAll()
                .stream()
                .map(BookMapper::toBookEntity)
                .toList();
    }

    @Override
    public Optional<BookEntity> findByIsbn(String isbn) {
        BookEntityJpa bookEntityJpa = bookRepositoryJpa.findByIsbn(isbn).orElse(null);
        return Optional.ofNullable(BookMapper.toBookEntity(bookEntityJpa));
    }
}
