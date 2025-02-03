package es.cesguiro.handler;

import es.cesguiro.model.PagedCollectionResponse;
import es.cesguiro.model.book.query.BookCollectionResponse;
import es.cesguiro.model.book.query.BookResponse;
import es.cesguiro.model.book.query.mapper.BookMapper;
import es.cesguiro.pagination.Page;
import es.cesguiro.property.PropertyUtil;
import es.cesguiro.usecase.book.query.FindAllUseCase;
import es.cesguiro.usecase.book.query.FindByIsbnUseCase;
import es.cesguiro.usecase.book.query.model.BookCollectionQuery;
import es.cesguiro.usecase.book.query.model.BookQuery;
import es.cesguiro.util.PaginationUtil;

import java.util.List;

public class BookHandler {


    public static final String RESOURCE = "books";
    public static final String RESOURCE_PATH = PropertyUtil.getInstance().getProperty("app.api.path") + "/" + RESOURCE;

    private final int DEFAULT_PAGE_SIZE = Integer.parseInt(PropertyUtil.getInstance().getProperty("app.default.page.size", "10"));

    private final FindAllUseCase findAllUseCase;
    private final FindByIsbnUseCase findByIsbnUseCase;

    public BookHandler(FindAllUseCase findAllUseCase, FindByIsbnUseCase findByIsbnUseCase) {
        this.findAllUseCase = findAllUseCase;
        this.findByIsbnUseCase = findByIsbnUseCase;
    }

    public PagedCollectionResponse<BookCollectionResponse> findAll(Integer page, Integer size) {

        int pageNumber = page != null ? page : 1;
        int pageSize = size != null ? size : DEFAULT_PAGE_SIZE;

        Page<BookCollectionQuery> pagedCollection = findAllUseCase.findAll(pageNumber, pageSize);
        List<BookCollectionResponse> bookCollectionResponses = pagedCollection.data().stream()
                .map(BookMapper::toBookCollectionResponse)
                .toList();
        return PaginationUtil.generatePagedCollectionResponse(
                bookCollectionResponses,
                pageNumber,
                pageSize,
                pagedCollection.totalElements(),
                pagedCollection.totalPages(),
                PropertyUtil.getInstance().getProperty("app.base.url") + RESOURCE_PATH
        );
    }

    public BookResponse findByIsbn(String isbn) {
        BookQuery bookQuery = findByIsbnUseCase.findByIsbn(isbn);
        return BookMapper.toBookResponse(bookQuery);
    }

}
