package es.cesguiro.integration;

import es.cesguiro.repository.impl.BookRepositoryImpl;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = AppConfig.class)
@Transactional
public class BookRepositoryDaoIntegrationTest {

    @Autowired
    private BookRepositoryImpl bookRepositoryImpl;

    @BeforeAll
    public static void setUp() {
        // Configuración de Flyway
        Flyway flyway = Flyway.configure().dataSource(
                "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MYSQL",
                "root",
                "root"
        ).load();

        // Ejecución de migraciones
        flyway.migrate();
    }

    @Test
    public void testFindAllBooks() {
        // Llamamos al método findAll() del repositorio
        var books = bookRepositoryImpl.findAll(0, 10);

        // Comprobamos que la lista no esté vacía y contiene elementos
        assertAll(
                () -> assertEquals(24, books.size()),
                () -> assertEquals("9780142424179", books.getFirst().isbn()),
                () -> assertEquals("9780142410363", books.get(1).isbn())
        );
    }

    @Test
    public void testFindBookByIsbn() {
        // Llamamos al método findByIsbn()
        var book = bookRepositoryImpl.findByIsbn("9780064400558");

        assertAll(
                () -> assertEquals("9780064400558", book.get().isbn()),
                () -> assertEquals("Alicia en el país de las maravillas", book.get().title())
        );
    }


}
