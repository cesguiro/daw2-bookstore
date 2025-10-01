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
import es.cesguiro.persistence.dao.AuthorDao;
import es.cesguiro.persistence.dao.BookDao;
import es.cesguiro.persistence.dao.PublisherDao;
import es.cesguiro.persistence.dao.jpa.AuthorDaoJpa;
import es.cesguiro.persistence.dao.jpa.BookDaoJpa;
import es.cesguiro.persistence.dao.jpa.PublisherDaoJpa;
import es.cesguiro.persistence.repository.AuthorRepositoryImpl;
import es.cesguiro.persistence.repository.BookRepositoryImpl;
import es.cesguiro.persistence.repository.PublisherRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    /************* BOOK *************/
    @Bean
    public BookDao bookDao() {
        return new BookDaoJpa();
    }

    @Bean
    public BookRepository bookRepository() {
        return new BookRepositoryImpl(bookDao());
    }

    @Bean
    public BookService bookService() {
        return new BookServiceImpl(bookRepository(), publisherRepository());
    }

    /************* PUBLISHER *************/
    @Bean
    public PublisherDao publisherDao() {
        return new PublisherDaoJpa();
    }

    @Bean
    public PublisherRepository publisherRepository() {
        return new PublisherRepositoryImpl(publisherDao());
    }

    @Bean
    public PublisherService publisherService() {
        return new PublisherServiceImpl(publisherRepository());
    }

    /*************** AUTHOR *************/
    @Bean
    public AuthorDao authorDao() {
        return new AuthorDaoJpa();
    }

    @Bean
    public AuthorRepository authorRepository() {
        return new AuthorRepositoryImpl(authorDao());
    }

    @Bean
    public AuthorService authorService() {
        return new AuthorServiceImpl(authorRepository());
    }

}
