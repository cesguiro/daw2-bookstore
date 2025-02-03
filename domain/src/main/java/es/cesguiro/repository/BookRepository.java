package es.cesguiro.repository;

import es.cesguiro.pagination.Page;
import es.cesguiro.repository.model.BookEntity;

import java.util.Optional;

public interface BookRepository {

    Page<BookEntity> findAll(int page, int size);

    Optional<BookEntity> findByIsbn(String isbn);

}
