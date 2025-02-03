package es.cesguiro.usecase.book.query;


import es.cesguiro.pagination.Page;
import es.cesguiro.usecase.book.query.model.BookCollectionQuery;

public interface FindAllUseCase {

    Page<BookCollectionQuery> findAll(int page, int size);
}
