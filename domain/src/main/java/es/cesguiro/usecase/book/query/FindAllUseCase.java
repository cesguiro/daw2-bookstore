package es.cesguiro.usecase.book.query;


import es.cesguiro.pagination.PagedCollection;
import es.cesguiro.usecase.book.query.model.BookCollectionDto;

import java.util.List;

public interface FindAllUseCase {

    PagedCollection<BookCollectionDto> execute(int page, int size);
}
