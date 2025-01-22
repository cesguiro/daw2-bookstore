package es.cesguiro.repository;

import es.cesguiro.repository.model.CategoryEntity;

import java.util.Optional;

public interface CategoryRepository {

    Optional<CategoryEntity> findByBookIsbn(String isbn);
}
