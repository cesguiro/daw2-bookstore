package es.cesguiro.controller;

import es.cesguiro.controller.data.BookData;
import es.cesguiro.exception.ResourceNotFoundException;
import es.cesguiro.handler.BookHandler;
import es.cesguiro.model.PageResponse;
import es.cesguiro.model.book.query.BookCollectionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookHandler mockBookHandler;

    @BeforeEach
    void setUp() {
        /*ReflectionTestUtils.setField(mockBookHandler, "RESOURCE", "books");
        ReflectionTestUtils.setField(mockBookHandler, "RESOURCE_PATH", "http://localhost:8080/api/books");
        ReflectionTestUtils.setField(mockBookHandler, "DEFAULT_PAGE_SIZE", 10);*/
    }

    @Test
    @DisplayName("GET /api/books")
    void findAll() throws Exception {
        when(mockBookHandler.findAll(1, 10)).thenReturn(
                new PageResponse<BookCollectionResponse>(
                        BookData.getBookCollectionResponsesEs(),
                        1,
                        10,
                        5,
                        1,
                        null,
                        null
                )
        );

        mockMvc.perform(get("/api/books")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.data.length()").value(5))
        .andExpect(jsonPath("$.data[0].isbn").value("isbn1"))
        .andExpect(jsonPath("$.data[0].title").value("titleEs1"))
        .andExpect(jsonPath("$.data[1].isbn").value("isbn2"))
        .andExpect(jsonPath("$.data[1].title").value("titleEs2"))
        .andExpect(jsonPath("$.page").value(1))
        .andExpect(jsonPath("$.pageSize").value(10))
        .andExpect(jsonPath("$.totalItems").value(5))
        .andExpect(jsonPath("$.totalPages").value(1))
        .andExpect(jsonPath("$.previous").doesNotExist())
        .andExpect(jsonPath("$.next").doesNotExist());
    }

    @Test
    @DisplayName("GET /api/books/{isbn}")
    void findByIsbn() throws Exception {
        when(mockBookHandler.findByIsbn("isbn3")).thenReturn(BookData.getBookResponseEs(2));

        mockMvc.perform(get("/api/books/isbn3")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.isbn").value("isbn3"))
        .andExpect(jsonPath("$.title").value("titleEs3"))
        .andExpect(jsonPath("$.synopsis").value("synopsisEs3"))
        .andExpect(jsonPath("$.authors.length()").value(2))
        .andExpect(jsonPath("$.authors[0].name").value("author2"))
        .andExpect(jsonPath("$.authors[0].links._self").value("http://localhost:8080/api/authors/author2"))
        .andExpect(jsonPath("$.authors[1].name").value("author3"))
        .andExpect(jsonPath("$.authors[1].links.books").value("http://localhost:8080/api/authors/author3/books"))
        .andExpect(jsonPath("$.publisher.name").value("publisher2"))
        .andExpect(jsonPath("$.publisher.links.books").value("http://localhost:8080/api/publishers/publisher2/books"))
        .andExpect(jsonPath("$.category.name").value("categoryEs1"))
        .andExpect(jsonPath("$.category.links.books").value("http://localhost:8080/api/categories/category1/books"))
        .andExpect(jsonPath("$.genres.length()").value(2))
        .andExpect(jsonPath("$.genres[0].name").value("genreEs3"))
        .andExpect(jsonPath("$.genres[0].links.books").value("http://localhost:8080/api/genres/genre3/books"));
    }

    @Test
    @DisplayName("GET /api/books/{isbn} with non existing isbn should return 404")
    void findByIsbnNonExisting() throws Exception {
        when(mockBookHandler.findByIsbn("isbn4")).thenThrow(
                new ResourceNotFoundException("Book with ISBN isbn4 not found")
        );

        mockMvc.perform(get("/api/books/isbn4")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Book with ISBN isbn4 not found"));
    }

}