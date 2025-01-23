package es.cesguiro.repository.impl;

import es.cesguiro.repository.GenreRepository;
import es.cesguiro.repository.model.GenreEntity;

import java.util.List;

public class GenreRepositoryImpl implements GenreRepository {

    @Override
    public List<GenreEntity> findAllByBookIsbn(String s) {
        return List.of();
    }
}
