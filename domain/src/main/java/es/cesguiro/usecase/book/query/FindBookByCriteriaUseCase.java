package es.cesguiro.usecase.book.query;

import es.cesguiro.usecase.book.query.model.BookQuery;

public interface FindBookByCriteriaUseCase {

    BookQuery findByIsbn(String isbn);
}
