package es.cesguiro.dao;

import es.cesguiro.dao.jpa.entity.CategoryEntityJpa;
import es.cesguiro.repository.model.CategoryEntity;

import java.util.Optional;

public interface CategoryDao {

    Optional<CategoryEntity> findByBookIsbn(String isbn);
}
