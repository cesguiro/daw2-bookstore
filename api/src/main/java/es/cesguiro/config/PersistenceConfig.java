package es.cesguiro.config;

import es.cesguiro.dao.*;
import es.cesguiro.dao.jpa.*;
import es.cesguiro.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "es.cesguiro.repository")
public class PersistenceConfig {

    @Bean
    public BookDao bookDao(EntityManager entityManager) {
        return new BookDaoJpa(entityManager);
    }

    /*@Bean
    public Dao<T> getDao(EntityManager entityManager,class<T> clazz) {
        return new AuthorDaoJpa(entityManager);
    }*/


    @Bean
    public AuthorDao authorDao(EntityManager entityManager) {
        return new AuthorDaoJpa(entityManager);
    }

    @Bean
    public CategoryDao categoryDao(EntityManager entityManager) {
        return new CategoryDaoJpa(entityManager);
    }

    @Bean
    public GenreDao genreDao(EntityManager entityManager) {
        return new GenreDaoJpa(entityManager);
    }

    @Bean
    public PublisherDao publisherDao(EntityManager entityManager) {
        return new PublisherDaoJpa(entityManager);
    }

    @Bean
    public BookRepository bookRepository(BookDao bookDao) {
        return new BookRepositoryJpa(bookDao);
    }

    @Bean
    public AuthorRepository authorRepository(AuthorDao authorDao) {
        return new AuthorRepositoryJpa(authorDao);
    }

    @Bean
    public CategoryRepository categoryRepository(CategoryDao categoryDao) {
        return new CategoryRepositoryJpa(categoryDao);
    }

    @Bean
    public GenreRepository genreRepository(GenreDao genreDao) {
        return new GenreRepositoryJpa(genreDao);
    }

    @Bean
    public PublisherRepository publisherRepository(PublisherDao publisherDao) {
        return new PublisherRepositoryJpa(publisherDao);
    }

}
