package es.cesguiro.usecase.book.query.impl;

import es.cesguiro.model.Book;
import es.cesguiro.repository.AuthorRepository;
import es.cesguiro.repository.BookRepository;
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
    public List<BookCollectionDto> execute(int page, int size) {
        List<Book> books = bookRepository
                .findAll(page, size)
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
        return  books.stream().map(BookMapper::toBookCollectionDto).toList();
    }
}
