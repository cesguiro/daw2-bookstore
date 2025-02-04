package es.cesguiro.dao;

import es.cesguiro.dao.jpa.entity.AuthorEntityJpa;
import es.cesguiro.repository.model.AuthorEntity;

import java.util.List;

public interface AuthorDao {

    List<AuthorEntity> findAllByBookIsbn(String isbn);
}
