package es.cesguiro.config;

import es.cesguiro.repository.*;
import es.cesguiro.service.book.query.FindAllBooksByCriteriaService;
import es.cesguiro.service.book.query.FindBookByCriteriaService;
import es.cesguiro.usecase.book.query.FindAllBooksByCriteriaUseCase;
import es.cesguiro.usecase.book.query.FindBookByCriteriaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public FindAllBooksByCriteriaUseCase findAllBooksUseCase(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, CategoryRepository categoryRepository, PublisherRepository publisherRepository) {
        return new FindAllBooksByCriteriaService(bookRepository, authorRepository);
    }

    @Bean
    FindBookByCriteriaUseCase findBookByIsbnUseCase(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, CategoryRepository categoryRepository, PublisherRepository publisherRepository) {
        return new FindBookByCriteriaService(bookRepository, authorRepository, genreRepository, categoryRepository, publisherRepository);
    }

}
