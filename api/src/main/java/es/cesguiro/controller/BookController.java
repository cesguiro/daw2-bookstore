package es.cesguiro.controller;

import es.cesguiro.handler.BookHandler;
import es.cesguiro.handler.impl.BookHandlerImpl;
import es.cesguiro.model.PageResponse;
import es.cesguiro.model.book.query.BookCollectionResponse;
import es.cesguiro.model.book.query.BookResponse;
import es.cesguiro.property.PropertyUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("${app.api.path}/" + BookHandlerImpl.RESOURCE)
//@RequestMapping(BookController.URL)
@RequestMapping("/api/books")
public class BookController {

    //public static final String URL = PropertyUtil.getProperty("app.api.path");
    private final int DEFAULT_PAGE_SIZE = Integer.parseInt(PropertyUtil.getProperty("app.default.pageSize", "10"));

    private final BookHandler bookHandler;

    public BookController(BookHandler bookHandler) {
        this.bookHandler = bookHandler;
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookCollectionResponse>> findAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false) Integer size) {
        int pageSize = size != null ? size : DEFAULT_PAGE_SIZE;
        PageResponse<BookCollectionResponse> result = bookHandler.findAll(page, pageSize);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookResponse> findByIsbn(@PathVariable("isbn") String isbn) {
        return new ResponseEntity<>(bookHandler.findByIsbn(isbn), HttpStatus.OK);
    }

}
