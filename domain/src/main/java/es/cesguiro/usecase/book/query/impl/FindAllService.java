package es.cesguiro.usecase.book.query.impl;

import es.cesguiro.exception.DomainException;
import es.cesguiro.model.Book;
import es.cesguiro.pagination.PagedCollection;
import es.cesguiro.repository.AuthorRepository;
import es.cesguiro.repository.BookRepository;
import es.cesguiro.repository.model.BookEntity;
import es.cesguiro.usecase.book.query.FindAllUseCase;
import es.cesguiro.usecase.book.query.mapper.AuthorMapper;
import es.cesguiro.usecase.book.query.mapper.BookMapper;
import es.cesguiro.usecase.book.query.model.BookCollectionDto;

import java.util.List;

public class FindAllService implements FindAllUseCase {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public FindAllService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public PagedCollection<BookCollectionDto> execute(int page, int size) {
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
}
