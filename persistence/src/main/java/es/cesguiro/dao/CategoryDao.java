package es.cesguiro.dao;

import es.cesguiro.dao.jpa.entity.CategoryEntityJpa;

import java.util.Optional;

public interface CategoryDao {

    Optional<CategoryEntityJpa> findByBookIsbn(String isbn);
}
