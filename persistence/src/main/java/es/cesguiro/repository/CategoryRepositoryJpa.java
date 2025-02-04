package es.cesguiro.repository;

import es.cesguiro.dao.CategoryDao;
import es.cesguiro.repository.model.CategoryEntity;

import java.util.Optional;

public class CategoryRepositoryJpa implements CategoryRepository {

    private final CategoryDao categoryDao;

    public CategoryRepositoryJpa(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Optional<CategoryEntity> findByBookIsbn(String isbn) {
        return categoryDao.findByBookIsbn(isbn);
    }
}
