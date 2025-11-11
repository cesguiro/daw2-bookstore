package es.cesguiro.daw2_bookstore.controller;

import es.cesguiro.daw2_bookstore.controller.webModel.response.BookDetailResponse;
import es.cesguiro.daw2_bookstore.controller.webModel.response.BookSummaryResponse;
import es.cesguiro.daw2_bookstore.controller.mapper.BookMapper;
import es.cesguiro.domain.model.Page;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.service.dto.BookDto;
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
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return bookService.create(bookDto);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable("id") Long id, @RequestBody BookDto bookDto) {
        if (!id.equals(bookDto.id())) {
            throw new IllegalArgumentException("ID in path and request body must match");
        }
        return bookService.update(bookDto);
    }

    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable("isbn") String isbn) {
        bookService.deleteByIsbn(isbn);
    }
}
