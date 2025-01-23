package es.cesguiro.dao.jpa.repository;

import es.cesguiro.dao.jpa.entity.BookEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepositoryJpa extends JpaRepository<BookEntityJpa, Long> {

    Optional<BookEntityJpa> findByIsbn(String isbn);
}
