package es.cesguiro.repository;

import es.cesguiro.dao.BookDao;
import es.cesguiro.dao.jpa.entity.BookEntityJpa;
import es.cesguiro.pagination.Page;
import es.cesguiro.repository.mapper.BookMapper;
import es.cesguiro.repository.model.BookEntity;

import java.util.List;
import java.util.Optional;

public class BookRepositoryJpa implements BookRepository {

    private final BookDao bookDao;

    public BookRepositoryJpa(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Page<BookEntity> findAll(int page, int size) {
        List<BookEntityJpa> bookEntityJpaPage = bookDao.findAll(page, size);
        long totalElements = bookDao.count();
        return new Page<>(
                bookEntityJpaPage.stream().map(BookMapper::toBookEntity).toList(),
                page,
                size,
                totalElements
        );
    }

    @Override
    public Optional<BookEntity> findByIsbn(String isbn) {
        BookEntityJpa bookEntityJpa = bookDao.findByIsbn(isbn).orElse(null);
        return Optional.ofNullable(BookMapper.toBookEntity(bookEntityJpa));
    }
}
