package es.cesguiro.service.book.query;

import es.cesguiro.exception.DomainException;
import es.cesguiro.model.Book;
import es.cesguiro.pagination.Page;
import es.cesguiro.repository.AuthorRepository;
import es.cesguiro.repository.BookRepository;
import es.cesguiro.repository.model.BookEntity;
import es.cesguiro.usecase.book.query.FindAllBooksByCriteriaUseCase;
import es.cesguiro.usecase.book.query.mapper.AuthorMapper;
import es.cesguiro.usecase.book.query.mapper.BookMapper;
import es.cesguiro.usecase.book.query.model.BookCollectionQuery;

import java.util.List;

public class FindAllBooksByCriteriaService implements FindAllBooksByCriteriaUseCase {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public FindAllBooksByCriteriaService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Page<BookCollectionQuery> findAll(int page, int size) {
        if(page <= 0 || size <= 0) {
            throw new DomainException("Page number and page size must be greater than zero");
        }
        Page<BookEntity> bookEntityPage = bookRepository.findAll(page, size);
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
        return new Page<>(
                books.stream().map(BookMapper::toBookCollectionQuery).toList(),
                page,
                size,
                bookEntityPage.totalElements()
        );
    }

}
