package es.cesguiro.repository.impl;

import es.cesguiro.dao.jpa.entity.BookEntityJpa;
import es.cesguiro.dao.jpa.repository.BookRepositoryJpa;
import es.cesguiro.pagination.PagedCollection;
import es.cesguiro.repository.BookRepository;
import es.cesguiro.repository.mapper.BookMapper;
import es.cesguiro.repository.model.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class BookRepositoryImpl implements BookRepository {

    private final BookRepositoryJpa bookRepositoryJpa;

    public BookRepositoryImpl(BookRepositoryJpa bookRepositoryJpa) {
        this.bookRepositoryJpa = bookRepositoryJpa;
    }

    @Override
    public PagedCollection<BookEntity> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BookEntityJpa> bookEntityJpaPage = bookRepositoryJpa.findAll(pageable);
        return new PagedCollection<BookEntity>(
                bookEntityJpaPage.getContent().stream().map(BookMapper::toBookEntity).toList(),
                page,
                size,
                bookEntityJpaPage.getTotalElements()
        );
    }

    @Override
    public Optional<BookEntity> findByIsbn(String isbn) {
        BookEntityJpa bookEntityJpa = bookRepositoryJpa.findByIsbn(isbn).orElse(null);
        return Optional.ofNullable(BookMapper.toBookEntity(bookEntityJpa));
    }
}
