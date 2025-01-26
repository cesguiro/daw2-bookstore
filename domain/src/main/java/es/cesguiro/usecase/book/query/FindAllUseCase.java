package es.cesguiro.usecase.book.query;


import es.cesguiro.pagination.PagedCollection;
import es.cesguiro.usecase.book.query.model.BookCollectionQuery;

public interface FindAllUseCase {

    PagedCollection<BookCollectionQuery> findAll(int page, int size);
}
