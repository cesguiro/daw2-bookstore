package es.cesguiro.repository.impl;

import es.cesguiro.repository.CategoryRepository;
import es.cesguiro.repository.model.CategoryEntity;

import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public Optional<CategoryEntity> findByBookIsbn(String s) {
        return Optional.empty();
    }
}
