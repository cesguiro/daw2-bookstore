package es.cesguiro.repository;

import es.cesguiro.repository.model.GenreEntity;

import java.util.List;

public interface GenreRepository {

    List<GenreEntity> findAllByBookIsbn(String isbn);
}
