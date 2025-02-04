package es.cesguiro.repository;

import es.cesguiro.dao.BookDao;
import es.cesguiro.dao.jpa.entity.BookEntityJpa;
import es.cesguiro.pagination.Page;
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
        List<BookEntity> data = bookDao.findAll(page, size);
        long totalElements = bookDao.count();
        return new Page<>(
                data,
                page,
                size,
                totalElements
        );
    }

    @Override
    public Optional<BookEntity> findByIsbn(String isbn) {
        return bookDao.findByIsbn(isbn);
    }
}
