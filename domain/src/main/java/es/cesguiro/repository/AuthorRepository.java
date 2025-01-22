package es.cesguiro.repository;

import es.cesguiro.repository.model.AuthorEntity;

import java.util.List;

public interface AuthorRepository {

    List<AuthorEntity> findAllByBookIsbn(String isbn);
}
