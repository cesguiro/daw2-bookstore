package es.cesguiro.dao;

import es.cesguiro.dao.jpa.entity.GenreEntityJpa;
import es.cesguiro.repository.model.GenreEntity;

import java.util.List;

public interface GenreDao {

    List<GenreEntity> findAllByBookIsbn(String isbn);
}
