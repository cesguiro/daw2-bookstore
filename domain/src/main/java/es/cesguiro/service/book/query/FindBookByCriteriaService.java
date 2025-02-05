package es.cesguiro.service.book.query;

import es.cesguiro.exception.DomainException;
import es.cesguiro.model.*;
import es.cesguiro.repository.*;
import es.cesguiro.usecase.book.query.FindBookByCriteriaUseCase;
import es.cesguiro.usecase.book.query.mapper.*;
import es.cesguiro.usecase.book.query.model.BookQuery;

import java.util.List;

public class FindBookByCriteriaService implements FindBookByCriteriaUseCase {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;


    public FindBookByCriteriaService(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, CategoryRepository categoryRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.categoryRepository = categoryRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public BookQuery findByIsbn(String isbn) {
        Book book = BookMapper.toBook(
                bookRepository.findByIsbn(isbn)
                        .orElseThrow(() -> new DomainException("Book not found"))
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
        return BookMapper.toBookQuery(book);
    }


}
