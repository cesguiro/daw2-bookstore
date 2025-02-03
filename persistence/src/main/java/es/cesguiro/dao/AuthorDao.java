package es.cesguiro.dao;

import es.cesguiro.dao.jpa.entity.AuthorEntityJpa;

import java.util.List;

public interface AuthorDao {

    List<AuthorEntityJpa> findAllByBookIsbn(String isbn);
}
