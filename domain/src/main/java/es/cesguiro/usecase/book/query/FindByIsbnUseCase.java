package es.cesguiro.usecase.book.query;

import es.cesguiro.usecase.book.query.model.BookQuery;

public interface FindByIsbnUseCase {

    BookQuery findByIsbn(String isbn);
}
