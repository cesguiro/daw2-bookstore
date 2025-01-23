package es.cesguiro.dao.jpa.repository;

import es.cesguiro.dao.jpa.entity.AuthorEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepositoryJpa extends JpaRepository<AuthorEntityJpa, Long> {

    @Query(value = "SELECT a.* FROM authors a " +
            "INNER JOIN books_authors ba ON a.id=ba.author_id " +
            "INNER JOIN books b ON ba.book_id = b.id " +
            "AND b.isbn=:isbn", nativeQuery = true)
    List<AuthorEntityJpa> findAllByBookIsbn(String isbn);
}
