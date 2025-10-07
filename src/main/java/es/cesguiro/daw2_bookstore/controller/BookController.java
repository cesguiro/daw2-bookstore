package es.cesguiro.daw2_bookstore.controller;

import es.cesguiro.domain.model.Page;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.service.dto.BookDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Page<BookDto> listBooks(@RequestParam(required = false, defaultValue = "1") int page,
                                   @RequestParam(required = false, defaultValue = "10") int size) {
        return bookService.getAll(page, size);
    }

    @GetMapping("/{isbn}")
    public BookDto getBookByIsbn(@PathVariable String isbn) {
        return bookService.getByIsbn(isbn);
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
