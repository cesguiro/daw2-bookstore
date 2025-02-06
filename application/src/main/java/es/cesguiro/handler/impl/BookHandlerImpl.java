package es.cesguiro.handler.impl;

import es.cesguiro.handler.BookHandler;
import es.cesguiro.model.PageResponse;
import es.cesguiro.model.book.query.BookCollectionResponse;
import es.cesguiro.model.book.query.BookResponse;
import es.cesguiro.model.book.query.mapper.BookMapper;
import es.cesguiro.pagination.Page;
import es.cesguiro.property.PropertyUtil;
import es.cesguiro.usecase.book.query.FindAllBooksByCriteriaUseCase;
import es.cesguiro.usecase.book.query.FindBookByCriteriaUseCase;
import es.cesguiro.usecase.book.query.model.BookCollectionQuery;
import es.cesguiro.usecase.book.query.model.BookQuery;
import es.cesguiro.util.PaginationUtil;

import java.util.List;

public class BookHandlerImpl implements BookHandler {

    public static final String RESOURCE = "books";
    public static final String RESOURCE_PATH = PropertyUtil.getProperty("app.api.path") + "/" + RESOURCE;

    private final int DEFAULT_PAGE_SIZE = Integer.parseInt(PropertyUtil.getProperty("app.default.page.size", "10"));

    private final FindAllBooksByCriteriaUseCase findAllBooksByCriteriaUseCase;
    private final FindBookByCriteriaUseCase findBookByCriteriaUseCase;

    public BookHandlerImpl(FindAllBooksByCriteriaUseCase findAllBooksByCriteriaUseCase, FindBookByCriteriaUseCase findBookByCriteriaUseCase) {
        this.findAllBooksByCriteriaUseCase = findAllBooksByCriteriaUseCase;
        this.findBookByCriteriaUseCase = findBookByCriteriaUseCase;
    }

    public PageResponse<BookCollectionResponse> findAll(Integer page, Integer size) {

        int pageNumber = page != null ? page : 1;
        int pageSize = size != null ? size : DEFAULT_PAGE_SIZE;

        Page<BookCollectionQuery> pagedCollection = findAllBooksByCriteriaUseCase.findAll(pageNumber, pageSize);
        List<BookCollectionResponse> bookCollectionResponses = pagedCollection.data().stream()
                .map(BookMapper::toBookCollectionResponse)
                .toList();
        return PaginationUtil.generatePagedCollectionResponse(
                bookCollectionResponses,
                pageNumber,
                pageSize,
                pagedCollection.totalElements(),
                pagedCollection.totalPages(),
                PropertyUtil.getProperty("app.base.url") + RESOURCE_PATH
        );
    }

    public BookResponse findByIsbn(String isbn) {
        BookQuery bookQuery = findBookByCriteriaUseCase.findByIsbn(isbn);
        return BookMapper.toBookResponse(bookQuery);
    }

}
