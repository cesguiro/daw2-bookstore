package es.cesguiro.usecase.book.query;


import es.cesguiro.usecase.book.query.model.BookCollectionDto;

import java.util.List;

public interface FindAllUseCase {

    List<BookCollectionDto> execute(int page, int size);
}
