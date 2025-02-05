package es.cesguiro.handler;

import es.cesguiro.model.PageResponse;
import es.cesguiro.model.book.query.BookCollectionResponse;
import es.cesguiro.model.book.query.BookResponse;

public interface BookHandler {

    PageResponse<BookCollectionResponse> findAll(Integer page, Integer size);

    BookResponse findByIsbn(String isbn);

}
