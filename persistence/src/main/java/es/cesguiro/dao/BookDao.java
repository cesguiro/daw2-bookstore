package es.cesguiro.dao;

import es.cesguiro.repository.model.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Optional<BookEntity> findByIsbn(String isbn);
    List<BookEntity> findAll(int page, int size);
    long count();

    /********** CriteriaBuilder version **********/

    List<BookEntity> findAllCB(int page, int size);
    Optional<BookEntity> findByIsbnCB(String isbn);
    long countCB();
}
