package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.model.Author;
import es.cesguiro.repository.model.AuthorEntity;
import es.cesguiro.usecase.book.query.model.AuthorCollectionQuery;
import es.cesguiro.usecase.book.query.model.AuthorQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorMapperTest {

    @Test
    @DisplayName("Test map AuthorEntity to Author")
    void toAuthor() {
        // Arrange
        AuthorEntity authorEntity = new AuthorEntity("name", "nationality", "biography", 1990, 2020, "slug");

        // Act
        Author author = AuthorMapper.toAuthor(authorEntity);

        // Assert
        assertAll(
                () -> assertEquals(authorEntity.name(), author.getName(), "Names should match"),
                () -> assertEquals(authorEntity.nationality(), author.getNationality(), "Nationalities should match"),
                () -> assertEquals(authorEntity.biography(), author.getBiography(), "Biographies should match"),
                () -> assertEquals(authorEntity.birthYear(), author.getBirthYear(), "Birth years should match"),
                () -> assertEquals(authorEntity.deathYear(), author.getDeathYear(), "Death years should match"),
                () -> assertEquals(authorEntity.slug(), author.getSlug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null AuthorEntity returns null Author")
    void toAuthorNull() {
        // Act
        Author author = AuthorMapper.toAuthor(null);

        // Assert
        assertNull(author, "Mapping null AuthorEntity should return null Author");
    }

    @Test
    @DisplayName("Test map list of AuthorEntity to list of Author")
    void toAuthorList() {
        // Arrange
        List<AuthorEntity> authorEntities = List.of(
                new AuthorEntity("name1", "nationality1", "biography1", 1980, 2000, "slug1"),
                new AuthorEntity("name2", "nationality2", "biography2", 1990, 2010, "slug2")
        );

        // Act
        List<Author> authors = authorEntities.stream()
                .map(AuthorMapper::toAuthor)
                .toList();

        // Assert
        assertAll(
                () -> assertEquals(authorEntities.size(), authors.size(), "List sizes should match"),
                () -> assertEquals(authorEntities.getFirst().name(), authors.getFirst().getName(), "First author's name should match"),
                () -> assertEquals(authorEntities.get(1).biography(), authors.get(1).getBiography(), "Second author's biography should match")
        );
    }

    @Test
    @DisplayName("Test map empty list of AuthorEntity returns empty list of Author")
    void toAuthorEmptyList() {
        // Arrange
        List<AuthorEntity> authorEntities = Collections.emptyList();

        // Act
        List<Author> authors = authorEntities.stream()
                .map(AuthorMapper::toAuthor)
                .toList();

        // Assert
        assertTrue(authors.isEmpty(), "Mapping an empty list should return an empty list");
    }

    @Test
    @DisplayName("Test map Author to AuthorCollectionDto")
    void toAuthorCollectionDto() {
        // Arrange
        Author author = new Author(
                "Author Name",
                "Author Nationality",
                "Author Biography",
                1990,
                2020,
                "author-slug"
        );

        // Act
        AuthorCollectionQuery dto = AuthorMapper.toAuthorCollectionDto(author);

        // Assert
        assertAll(
                () -> assertEquals(author.getName(), dto.name(), "Names should match"),
                () -> assertEquals(author.getSlug(), dto.slug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null Author returns null AuthorCollectionDto")
    void toAuthorCollectionDtoNull() {
        // Act
        AuthorCollectionQuery dto = AuthorMapper.toAuthorCollectionDto(null);

        // Assert
        assertNull(dto, "Mapping null Author should return null AuthorCollectionDto");
    }

    @Test
    @DisplayName("Test map list of Author to list of AuthorCollectionDto")
    void toAuthorCollectionDtoList() {
        // Arrange
        Author author1 = new Author(
                "Author Name",
                "Author National",
                "Author Bio",
                1990,
                2020,
                "author-slug"
        );

        Author author2 = new Author(
                "Author Name 2",
                "Author National 2",
                "Author Bio 2",
                1991,
                2021,
                "author-slug-2"
        );

        List<Author> authors = List.of(author1, author2);

        // Act
        List<AuthorCollectionQuery> dtos = authors.stream()
                .map(AuthorMapper::toAuthorCollectionDto)
                .toList();

        // Assert
        assertAll(
                () -> assertEquals(authors.size(), dtos.size(), "List sizes should match"),
                () -> assertEquals(authors.get(0).getName(), dtos.get(0).name(), "First author's name should match"),
                () -> assertEquals(authors.get(1).getSlug(), dtos.get(1).slug(), "Second author's slug should match")
        );
    }

    @Test
    @DisplayName("Test map empty list of Author returns empty list of AuthorCollectionDto")
    void toAuthorCollectionDtoEmptyList() {
        // Arrange
        List<Author> authors = Collections.emptyList();

        // Act
        List<AuthorCollectionQuery> dtos = authors.stream()
                .map(AuthorMapper::toAuthorCollectionDto)
                .toList();

        // Assert
        assertTrue(dtos.isEmpty(), "Mapping an empty list should return an empty list");
    }

    @Test
    @DisplayName("Test map Author to AuthorDto")
    void toAuthorDto() {
        // Arrange
        Author author = new Author(
                "Author Name",
                "Author National",
                "Author Bio",
                1990,
                2020,
                "author-slug"
        );
        AuthorQuery authorQuery = AuthorMapper.toAuthorDto(author);
        assertAll(
                () -> assertEquals(author.getName(), authorQuery.name(), "Names should match"),
                () -> assertEquals(author.getSlug(), authorQuery.slug(), "Slugs should match")
        );
    }

    @Test
    @DisplayName("Test null Author returns null AuthorDto")
    void toAuthorDtoNull() {
        // Act
        AuthorQuery authorQuery = AuthorMapper.toAuthorDto(null);
        assertNull(authorQuery, "Mapping null Author should return null AuthorDto");
    }

    @Test
    @DisplayName("Test map list of Author to list of AuthorDto")
    void toAuthorDtoList() {
        // Arrange
        Author author1 = new Author(
                "Author Name",
                "Author National",
                "Author Bio",
                1990,
                2020,
                "author-slug"
        );
        Author author2 = new Author(
                "Author Name 2",
                "Author National 2",
                "Author Bio 2",
                1991,
                2021,
                "author-slug-2"
        );
        List<Author> authors = List.of(author1, author2);
        List<AuthorQuery> authorQueries = authors.stream()
                .map(AuthorMapper::toAuthorDto)
                .toList();
        assertAll(
                () -> assertEquals(authors.size(), authorQueries.size(), "List sizes should match"),
                () -> assertEquals(authors.get(0).getName(), authorQueries.get(0).name(), "First author's name should match"),
                () -> assertEquals(authors.get(1).getSlug(), authorQueries.get(1).slug(), "Second author's slug should match")
        );
    }

    @Test
    @DisplayName("Test map empty list of Author returns empty list of AuthorDto")
    void toAuthorDtoEmptyList() {
        // Arrange
        List<Author> authors = Collections.emptyList();
        List<AuthorQuery> authorQueries = authors.stream()
                .map(AuthorMapper::toAuthorDto)
                .toList();
        assertTrue(authorQueries.isEmpty(), "Mapping an empty list should return an empty list");
    }

}