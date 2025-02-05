package es.cesguiro.config;

import es.cesguiro.handler.BookHandler;
import es.cesguiro.handler.impl.BookHandlerImpl;
import es.cesguiro.usecase.book.query.FindAllBooksByCriteriaUseCase;
import es.cesguiro.usecase.book.query.FindBookByCriteriaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfig {

    @Bean
    BookHandler bookHandler(
            FindAllBooksByCriteriaUseCase FindAllBooksByCriteriaUseCase,
            FindBookByCriteriaUseCase FindBookByCriteriaUseCase) {
        return new BookHandlerImpl(FindAllBooksByCriteriaUseCase, FindBookByCriteriaUseCase);
    }
}
