package es.cesguiro.daw2_bookstore.config;

import es.cesguiro.domain.repository.AuthorRepository;
import es.cesguiro.domain.repository.BookRepository;
import es.cesguiro.domain.repository.PublisherRepository;
import es.cesguiro.domain.service.AuthorService;
import es.cesguiro.domain.service.PublisherService;
import es.cesguiro.domain.service.impl.AuthorServiceImpl;
import es.cesguiro.domain.service.impl.BookServiceImpl;
import es.cesguiro.domain.service.BookService;
import es.cesguiro.domain.service.impl.PublisherServiceImpl;
import es.cesguiro.persistence.PersistenceConfig;
import es.cesguiro.persistence.dao.AuthorDao;
import es.cesguiro.persistence.dao.BookDao;
import es.cesguiro.persistence.dao.PublisherDao;
import es.cesguiro.persistence.repository.AuthorRepositoryImpl;
import es.cesguiro.persistence.repository.BookRepositoryImpl;
import es.cesguiro.persistence.repository.PublisherRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceConfig.class)
public class SpringConfig {

    /************* BOOK *************/

    @Bean
    public BookRepository bookRepository(BookDao bookDao) {
        return new BookRepositoryImpl(bookDao);
    }

    @Bean
    public BookService bookService(BookRepository bookRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository) {
        return new BookServiceImpl(bookRepository, publisherRepository, authorRepository);
    }

    /************* PUBLISHER *************/

    @Bean
    public PublisherRepository publisherRepository(PublisherDao publisherDao) {
        return new PublisherRepositoryImpl(publisherDao);
    }

    @Bean
    public PublisherService publisherService(PublisherRepository publisherRepository) {
        return new PublisherServiceImpl(publisherRepository);
    }

    /*************** AUTHOR **************/
    @Bean
    public AuthorRepository authorRepository(AuthorDao authorDao) {
        return new AuthorRepositoryImpl(authorDao);
    }

    @Bean
    public AuthorService authorService(AuthorRepository authorRepository) {
        return new AuthorServiceImpl(authorRepository);
    }

}
