package es.cesguiro.controller;

import es.cesguiro.handler.BookHandler;
import es.cesguiro.usecase.book.query.FindAllUseCase;
import es.cesguiro.usecase.book.query.FindByIsbnUseCase;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FindAllUseCase findAllUseCase;

    @MockitoBean
    private FindByIsbnUseCase findByIsbnUseCase;

    @InjectMocks
    private BookHandler bookHandler;

            /*mockMvc.perform(get("/api/books?page=1&size=2")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data.length()").value(2))
            .andExpect(jsonPath("$.data[0].isbn").value("isbn1"))
            .andExpect(jsonPath("$.data[0].title").value("title1"))
            .andExpect(jsonPath("$.data[1].isbn").value("isbn2"))
            .andExpect(jsonPath("$.data[1].title").value("title2"))
            .andExpect(jsonPath("$.page").value(1))
            .andExpect(jsonPath("$.pageSize").value(2))
            .andExpect(jsonPath("$.totalItems").value(2))
            .andExpect(jsonPath("$.totalPages").value(1))
            .andExpect(jsonPath("$.previous").doesNotExist())
            .andExpect(jsonPath("$.next").doesNotExist());*/


}