package es.cesguiro.dao;

import es.cesguiro.dao.jpa.entity.GenreEntityJpa;

import java.util.List;

public interface GenreDao {

    List<GenreEntityJpa> findAllByBookIsbn(String isbn);
}
