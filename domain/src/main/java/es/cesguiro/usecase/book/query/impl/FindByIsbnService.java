package es.cesguiro.usecase.book.query.impl;

import es.cesguiro.model.*;
import es.cesguiro.repository.*;
import es.cesguiro.usecase.book.query.FindByIsbnUseCase;
import es.cesguiro.usecase.book.query.mapper.*;
import es.cesguiro.usecase.book.query.model.BookDto;

import java.util.List;

public class FindByIsbnService implements FindByIsbnUseCase {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;

    public FindByIsbnService(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, CategoryRepository categoryRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.categoryRepository = categoryRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public BookDto execute(String isbn) {
        Book book = BookMapper.toBook(
                bookRepository.findByIsbn(isbn)
                        .orElseThrow(() -> new IllegalArgumentException("Book not found"))
        );
        List<Author> authors = authorRepository
                .findAllByBookIsbn(book.getIsbn())
                .stream()
                .map(AuthorMapper::toAuthor)
                .toList();
        book.setAuthors(authors);
        List<Genre> genres = genreRepository
                .findAllByBookIsbn(book.getIsbn())
                .stream()
                .map(GenreMapper::toGenre)
                .toList();
        book.setGenres(genres);
        Publisher publisher = publisherRepository
                .findByBookIsbn(book.getIsbn())
                .map(PublisherMapper::toPublisher)
                .orElse(null);
        book.setPublisher(publisher);
        Category category = categoryRepository
                .findByBookIsbn(book.getIsbn())
                .map(CategoryMapper::toCategory)
                .orElse(null);
        book.setCategory(category);
        return BookMapper.toBookDto(book);
    }
}
