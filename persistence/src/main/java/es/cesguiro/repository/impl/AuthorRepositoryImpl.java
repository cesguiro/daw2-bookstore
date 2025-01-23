package es.cesguiro.repository.impl;

import es.cesguiro.dao.jpa.repository.AuthorRepositoryJpa;
import es.cesguiro.repository.AuthorRepository;
import es.cesguiro.repository.mapper.AuthorMapper;
import es.cesguiro.repository.model.AuthorEntity;

import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {

    private final AuthorRepositoryJpa authorRepositoryJpa;

    public AuthorRepositoryImpl(AuthorRepositoryJpa authorRepositoryJpa) {
        this.authorRepositoryJpa = authorRepositoryJpa;
    }

    @Override
    public List<AuthorEntity> findAllByBookIsbn(String s) {
        return authorRepositoryJpa
                .findAllByBookIsbn(s)
                .stream()
                .map(AuthorMapper::toAuthorEntity)
                .toList();
    }
}
