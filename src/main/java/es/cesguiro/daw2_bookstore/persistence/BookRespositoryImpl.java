package es.cesguiro.daw2_bookstore.persistence;

import es.cesguiro.domain.model.Page;
import es.cesguiro.domain.repository.BookRepository;
import es.cesguiro.domain.repository.entity.BookEntity;
import es.cesguiro.domain.repository.entity.PublisherEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRespositoryImpl implements BookRepository {

    List<BookEntity> bookEntityList = List.of(
            new BookEntity(
                    "978-84-376-0494-7",
                    "El Quijote",
                    "Don Quixote",
                    "Una novela escrita por Miguel de Cervantes Saavedra.",
                    "A novel written by Miguel de Cervantes Saavedra.",
                    new BigDecimal("29.99"),
                    0,
                    null,
                    null,
                    new PublisherEntity("Penguin Random House","penguin-random-house"),
                    new ArrayList<>()
            ),
            new BookEntity(
                    "978-84-376-0494-8",
                    "Cien años de soledad",
                    "One Hundred Years of Solitude",
                    "Una novela escrita por Gabriel García Márquez.",
                    "A novel written by Gabriel García Márquez.",
                    new BigDecimal("19.99"),
                    0,
                    null,
                    null,
                    new PublisherEntity("Penguin Random House","penguin-random-house"),
                    new ArrayList<>()
            )
    );

    @Override
    public Page<BookEntity> findAll(int page, int size) {
        return new Page<>(bookEntityList, page, size, bookEntityList.size(), 1);
    }

    @Override
    public Optional<BookEntity> findByIsbn(String s) {
        return Optional.empty();
    }
}
