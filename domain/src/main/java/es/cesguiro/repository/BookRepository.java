package es.cesguiro.repository;

import es.cesguiro.repository.model.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<BookEntity> findAll(int page, int size);

    Optional<BookEntity> findByIsbn(String isbn);

}
