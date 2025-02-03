package es.cesguiro.repository;

import es.cesguiro.dao.GenreDao;
import es.cesguiro.repository.mapper.GenreMapper;
import es.cesguiro.repository.model.GenreEntity;

import java.util.List;

public class GenreRepositoryJpa implements GenreRepository {

    private final GenreDao genreDao;

    public GenreRepositoryJpa(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<GenreEntity> findAllByBookIsbn(String isbn) {
        return genreDao
                .findAllByBookIsbn(isbn)
                .stream()
                .map(GenreMapper::toGenreEntity)
                .toList();
    }
}
