package es.cesguiro.usecase.book.query;

import es.cesguiro.usecase.book.query.model.BookDto;

public interface FindByIsbnUseCase {

    BookDto execute(String isbn);
}
