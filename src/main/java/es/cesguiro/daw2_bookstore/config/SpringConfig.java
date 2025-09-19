package es.cesguiro.daw2_bookstore.config;

import es.cesguiro.daw2_bookstore.persistence.BookRespositoryImpl;
import es.cesguiro.domain.repository.BookRepository;
import es.cesguiro.domain.service.impl.BookServiceImpl;
import es.cesguiro.domain.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public BookRepository bookRepository() {
        return new BookRespositoryImpl();
    }

    @Bean
    public BookService bookService() {
        return new BookServiceImpl(bookRepository());
    }
}
