package es.cesguiro.dao;

import es.cesguiro.dao.jpa.entity.BookEntityJpa;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Optional<BookEntityJpa> findByIsbn(String isbn);
    List<BookEntityJpa> findAll(int page, int size);
    long count();
}
