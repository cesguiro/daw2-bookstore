package es.cesguiro.repository;

import es.cesguiro.dao.AuthorDao;
import es.cesguiro.repository.model.AuthorEntity;

import java.util.List;

public class AuthorRepositoryJpa implements AuthorRepository {

    private final AuthorDao authorDao;

    public AuthorRepositoryJpa(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public List<AuthorEntity> findAllByBookIsbn(String isbn) {
        return authorDao.findAllByBookIsbn(isbn);
    }
}
