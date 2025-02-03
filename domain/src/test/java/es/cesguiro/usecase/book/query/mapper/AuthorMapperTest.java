package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.locale.LocaleUtil;
import es.cesguiro.model.Author;
import es.cesguiro.repository.model.AuthorEntity;
import es.cesguiro.usecase.book.query.data.AuthorData;
import es.cesguiro.usecase.book.query.model.AuthorCollectionQuery;
import es.cesguiro.usecase.book.query.model.AuthorQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorMapperTest {

    @AfterEach
    void teardown() {
        LocaleUtil.resetInstance();
    }

    @Test
    @DisplayName("Test map AuthorEntity to Author")
    void toAuthor() {
        AuthorEntity authorEntity = AuthorData.getAuthorEntity(0);

        Author result = AuthorMapper.toAuthor(authorEntity);

        assertAll(
                () -> assertEquals(authorEntity.name(), result.getName(), "Names should match"),
                () -> assertEquals(authorEntity.nationality(), result.getNationality(), "Nationalities should match"),
                () -> assertEquals(authorEntity.biographyEs(), result.getBiography("es"), "Biographies in ES should match"),
                () -> assertEquals(authorEntity.biographyEn(), result.getBiography("en"), "Biographies in EN should match"),
                () -> assertEquals(authorEntity.birthYear(), result.getBirthYear(), "Birth years should match"),
                () -> assertEquals(authorEntity.deathYear(), result.getDeathYear(), "Death years should match"),
                () -> assertEquals(authorEntity.slug(), result.getSlug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null AuthorEntity returns null Author")
    void toAuthorNull() {
        Author result = AuthorMapper.toAuthor(null);

        assertNull(result, "Mapping null AuthorEntity should return null Author");
    }

    @Test
    @DisplayName("Test map list of AuthorEntity to list of Author")
    void toAuthorList() {
        List<AuthorEntity> authorEntities = List.of(
                AuthorData.getAuthorEntity(0),
                AuthorData.getAuthorEntity(1)
        );

        List<Author> result = authorEntities.stream()
                .map(AuthorMapper::toAuthor)
                .toList();

        assertAll(
                () -> assertEquals(authorEntities.size(), result.size(), "List sizes should match"),
                () -> assertEquals(authorEntities.getFirst().name(), result.getFirst().getName(), "First author's name should match"),
                () -> assertEquals(authorEntities.get(1).biographyEs(), result.get(1).getBiography("es"), "Second author's biography should match")
        );
    }

    @Test
    @DisplayName("Test map empty list of AuthorEntity returns empty list of Author")
    void toAuthorEmptyList() {
        List<AuthorEntity> authorEntities = Collections.emptyList();

        List<Author> result = authorEntities.stream()
                .map(AuthorMapper::toAuthor)
                .toList();

        assertTrue(result.isEmpty(), "Mapping an empty list should return an empty list");
    }

    @Test
    @DisplayName("Test map Author to AuthorCollectionQuery")
    void toAuthorCollectionQuery() {
        Author author = AuthorData.getAuthor(0);

        AuthorCollectionQuery result = AuthorMapper.toAuthorCollectionQuery(author);

        assertAll(
                () -> assertEquals(author.getName(), result.name(), "Names should match"),
                () -> assertEquals(author.getSlug(), result.slug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null Author returns null AuthorCollectionQuery")
    void toAuthorCollectionQueryNull() {
        AuthorCollectionQuery result = AuthorMapper.toAuthorCollectionQuery(null);

        assertNull(result, "Mapping null Author should return null AuthorCollectionDto");
    }

    @Test
    @DisplayName("Test map list of Author to list of AuthorCollectionQuery")
    void toAuthorCollectionQueryList() {
        List<Author> authors = List.of(
                AuthorData.getAuthor(0),
                AuthorData.getAuthor(1)
        );

        List<AuthorCollectionQuery> result = authors.stream()
                .map(AuthorMapper::toAuthorCollectionQuery)
                .toList();

        assertAll(
                () -> assertEquals(authors.size(), result.size(), "List sizes should match"),
                () -> assertEquals(authors.getFirst().getName(), result.getFirst().name(), "First author's name should match"),
                () -> assertEquals(authors.get(1).getSlug(), result.get(1).slug(), "Second author's slug should match")
        );
    }

    @Test
    @DisplayName("Test map empty list of Author returns empty list of AuthorCollectionDto")
    void toAuthorCollectionQueryEmptyList() {
        List<Author> authors = Collections.emptyList();

        List<AuthorCollectionQuery> result = authors.stream()
                .map(AuthorMapper::toAuthorCollectionQuery)
                .toList();

        assertTrue(result.isEmpty(), "Mapping an empty list should return an empty list");
    }

    @Test
    @DisplayName("Test map Author to AuthorQuery")
    void toAuthorQuery() {
        Author author = AuthorData.getAuthor(0);

        AuthorQuery result = AuthorMapper.toAuthorQuery(author);

        assertAll(
                () -> assertEquals(author.getName(), result.name(), "Names should match"),
                () -> assertEquals(author.getSlug(), result.slug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null Author returns null AuthorQuery")
    void toAuthorQueryNull() {
        AuthorQuery result = AuthorMapper.toAuthorQuery(null);

        assertNull(result, "Mapping null Author should return null AuthorQuery");
    }

    @Test
    @DisplayName("Test map list of Author to list of AuthorDto")
    void toAuthorQueryList() {
        List<Author> authors = List.of(
                AuthorData.getAuthor(0),
                AuthorData.getAuthor(1)
        );

        List<AuthorQuery> result = authors.stream()
                .map(AuthorMapper::toAuthorQuery)
                .toList();

        assertAll(
                () -> assertEquals(authors.size(), result.size(), "List sizes should match"),
                () -> assertEquals(authors.getFirst().getName(), result.getFirst().name(), "First author's name should match"),
                () -> assertEquals(authors.get(1).getSlug(), result.get(1).slug(), "Second author's slug should match")
        );
    }

    @Test
    @DisplayName("Test map empty list of Author returns empty list of AuthorDto")
    void toAuthorQueryEmptyList() {
        // Arrange
        List<Author> authors = Collections.emptyList();
        List<AuthorQuery> result = authors.stream()
                .map(AuthorMapper::toAuthorQuery)
                .toList();
        assertTrue(result.isEmpty(), "Mapping an empty list should return an empty list");
    }

}