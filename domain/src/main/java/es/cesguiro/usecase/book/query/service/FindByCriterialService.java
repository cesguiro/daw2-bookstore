package es.cesguiro.usecase.book.query.service;

import es.cesguiro.exception.DomainException;
import es.cesguiro.model.*;
import es.cesguiro.pagination.PagedCollection;
import es.cesguiro.repository.*;
import es.cesguiro.repository.model.BookEntity;
import es.cesguiro.usecase.book.query.FindAllUseCase;
import es.cesguiro.usecase.book.query.FindByIsbnUseCase;
import es.cesguiro.usecase.book.query.mapper.*;
import es.cesguiro.usecase.book.query.model.BookCollectionQuery;
import es.cesguiro.usecase.book.query.model.BookQuery;

import java.util.List;

public class FindByCriterialService implements FindAllUseCase, FindByIsbnUseCase {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;


    public FindByCriterialService(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, CategoryRepository categoryRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.categoryRepository = categoryRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public PagedCollection<BookCollectionQuery> findAll(int page, int size) {
        if(page < 0 || size <= 0) {
            throw new DomainException("Page number and page size must be greater than zero");
        }
        PagedCollection<BookEntity> bookEntityPage = bookRepository.findAll(page, size);
        List<Book> books = bookEntityPage
                .data()
                .stream()
                .map(BookMapper::toBook)
                .toList();
        books.forEach(
                book -> {
                    book.setAuthors(
                            authorRepository
                                    .findAllByBookIsbn(book.getIsbn())
                                    .stream()
                                    .map(AuthorMapper::toAuthor)
                                    .toList()
                    );
                }
        );
        return new PagedCollection<>(
                books.stream().map(BookMapper::toBookCollectionDto).toList(),
                page,
                size,
                bookEntityPage.totalElements()
        );
    }

    @Override
    public BookQuery findByIsbn(String isbn) {
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
