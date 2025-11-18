package es.cesguiro.daw2_bookstore.controller;

import es.cesguiro.daw2_bookstore.controller.webModel.request.BookInsertRequest;
import es.cesguiro.daw2_bookstore.controller.webModel.request.BookUpdateRequest;
import es.cesguiro.daw2_bookstore.controller.webModel.response.BookDetailResponse;
import es.cesguiro.daw2_bookstore.controller.webModel.response.BookSummaryResponse;
import es.cesguiro.daw2_bookstore.controller.mapper.BookMapper;
import es.cesguiro.domain.model.Page;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.service.dto.BookDto;
import jakarta.validation.constraints.Null;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Page<BookSummaryResponse>> findAllBooks(@RequestParam(required = false, defaultValue = "1") int page,
                                                                 @RequestParam(required = false, defaultValue = "10") int size) {
        Page<BookDto> bookDtoPage = bookService.getAll(page, size);

        List<BookSummaryResponse> bookSummaries = bookDtoPage.data().stream()
                .map(BookMapper::fromBookDtoToBookSummaryResponse)
                .toList();

        Page<BookSummaryResponse> bookSummaryPage = new Page<>(
                bookSummaries,
                bookDtoPage.pageNumber(),
                bookDtoPage.pageSize(),
                bookDtoPage.totalElements(),
                bookDtoPage.totalPages()
        );
        return new ResponseEntity<>(bookSummaryPage, HttpStatus.OK);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDetailResponse> getBookByIsbn(@PathVariable String isbn) {
        BookDetailResponse bookDetailResponse = BookMapper.fromBookDtoToBookDetailResponse(bookService.getByIsbn(isbn));
        return new ResponseEntity<>(bookDetailResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDetailResponse> createBook(@RequestBody BookInsertRequest bookInsertRequest) {
        BookDto bookDto = BookMapper.fromBookInsertRequestToBookDto(bookInsertRequest);
        BookDto createdBook = bookService.create(bookDto);
        return new ResponseEntity<>(BookMapper.fromBookDtoToBookDetailResponse(createdBook), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDetailResponse> updateBook(@PathVariable("id") Long id, @RequestBody BookUpdateRequest bookUpdateRequest) {
        if (!id.equals(bookUpdateRequest.id())) {
            throw new IllegalArgumentException("ID in path and request body must match");
        }
        BookDto bookDto = BookMapper.fromBookUpdateRequestToBookDto(bookUpdateRequest);
        BookDto updatedBook = bookService.update(bookDto);
        return new ResponseEntity<>(BookMapper.fromBookDtoToBookDetailResponse(updatedBook), HttpStatus.OK);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable("isbn") String isbn) {
        bookService.deleteByIsbn(isbn);
        return ResponseEntity.noContent().build();
    }
}
