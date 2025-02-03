package es.cesguiro.controller;

import es.cesguiro.handler.BookHandler;
import es.cesguiro.model.PagedCollectionResponse;
import es.cesguiro.model.book.query.BookCollectionResponse;
import es.cesguiro.property.PropertyUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${app.api.path}/" + BookHandler.RESOURCE)
public class BookController {

    public static final String RESOURCE = "books";
    public static final String RESOURCE_PATH = PropertyUtil.getInstance().getProperty("app.api.path") + "/" + RESOURCE;

    private final int DEFAULT_PAGE_SIZE = Integer.parseInt(PropertyUtil.getInstance().getProperty("app.default.page.size", "10"));

    @GetMapping
    public ResponseEntity<PagedCollectionResponse<BookCollectionResponse>> findAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false) Integer size) {
        return null;
        //return new ResponseEntity<>(result, HttpStatus.OK);

    }

}
