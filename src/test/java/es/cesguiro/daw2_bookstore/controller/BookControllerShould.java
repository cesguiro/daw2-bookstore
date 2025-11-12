package es.cesguiro.daw2_bookstore.controller;

import es.cesguiro.domain.model.Page;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.service.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerShould {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void response_list_of_books_when() throws Exception {
        //MockMvcTester client = MockMvcTester.create(mockMvc);

        List<BookDto> bookDtos = List.of(
                new BookDto(
                        1L,
                        "1111111111111",
                        "Book Title es 1",
                        "Book Title en 1",
                        "Synopsis es 1",
                        "Synopsis en 1",
                        new BigDecimal("23.00"),
                        new BigDecimal("0.00"),
                        new BigDecimal("23.00"),
                        "cover1.jpg",
                        null,
                        null,
                        List.of()),
                new BookDto(
                        2L,
                        "2222222222222",
                        "Book Title es 2",
                        "Book Title en 2",
                        "Synopsis es 2",
                        "Synopsis en 2",
                        new BigDecimal("30.00"),
                        new BigDecimal("10.00"),
                        new BigDecimal("27.00"),
                        "cover2.jpg",
                        null,
                        null,
                        List.of())
                );

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
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].isbn").value("1111111111111"))
                .andExpect(jsonPath("$.data[1].isbn").value("2222222222222"));

        /*var response = client.get()
                .uri("/api/books")
                .exchange()
                .getResponse();

        assertThat(response).isNotNull();
        assertThat(response.getResponse().getContentType()).contains("application/json");

        // Assert del cuerpo JSON (sin jsonPath)
        assertThatJson(response.getBody())
                .node("data").isArray()
                .hasSize(2)
                .node("data[0].isbn").isEqualTo("1111111111111")
                .node("data[1].isbn").isEqualTo("2222222222222");*/

    }

}