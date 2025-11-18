package es.cesguiro.daw2_bookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.cesguiro.daw2_bookstore.controller.webModel.request.BookInsertRequest;
import es.cesguiro.daw2_bookstore.util.InstancioModel;
import es.cesguiro.domain.exception.ResourceNotFoundException;
import es.cesguiro.domain.model.Page;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.service.dto.BookDto;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerShould {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void return_list_of_books() throws Exception {
        List<BookDto> bookDtos = Instancio.ofList(InstancioModel.BOOK_DTO_MODEL)
                .size(2)
                .create();

        Page<BookDto> bookDtoPage = new Page<>(
                bookDtos,
                1,
                10,
                2L,
                1
        );

        when(bookService.getAll(1, 10)).thenReturn(bookDtoPage);

        mockMvc.perform(get("/api/books?page=1&size=10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].isbn").value(bookDtos.getFirst().isbn()))
                .andExpect(jsonPath("$.data[1].isbn").value(bookDtos.getLast().isbn()));
    }

    @Test
    void return_book_when_isbn_exists() throws Exception {
        BookDto bookDto = Instancio.of(InstancioModel.BOOK_DTO_MODEL)
                .create();
        when(bookService.getByIsbn(anyString())).thenReturn(bookDto);

        mockMvc.perform(get("/api/books/" + bookDto.isbn()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.isbn").value(bookDto.isbn()));
    }

    @Test
    void return_not_found_when_isbn_does_not_exist() throws Exception {
        when(bookService.getByIsbn(anyString())).thenThrow(new ResourceNotFoundException("Book with isbn 1234567890123 not found"));

        mockMvc.perform(get("/api/books/1234567890123"))
                .andExpect(status().isNotFound());
    }

    @Test
    void return_newly_created_book() throws Exception {
        BookInsertRequest bookInsertRequest = Instancio.of(InstancioModel.BOOK_INSERT_REQUEST_MODEL)
                .create();
        BookDto bookDto = Instancio.of(InstancioModel.BOOK_DTO_MODEL)
                .create();
        when(bookService.create(bookDto)).thenReturn(bookDto);

        // Convertir request a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(bookInsertRequest);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                )
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isCreated());
    }

}