package es.cesguiro.repository;

import es.cesguiro.pagination.PagedCollection;
import es.cesguiro.repository.model.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    PagedCollection<BookEntity> findAll(int page, int size);

    Optional<BookEntity> findByIsbn(String isbn);

}
