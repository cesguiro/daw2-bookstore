package es.cesguiro.usecase.book.query.data;

import es.cesguiro.model.Author;
import es.cesguiro.model.vo.LocaleString;
import es.cesguiro.repository.model.AuthorEntity;
import es.cesguiro.usecase.book.query.model.AuthorCollectionQuery;
import es.cesguiro.usecase.book.query.model.AuthorQuery;

import java.util.List;

public class AuthorData {

    private static List<AuthorEntity> authorEntities = List.of(
            new AuthorEntity("author1", "nationality1", "biographyAuthor1Es", "biographyAuthor2En",  1990, 2020, "author1"),
            new AuthorEntity("author2", "nationality2", "biographyAuthor2Es", "biographyAuthor2En",  1991, 2021, "author2"),
            new AuthorEntity("author3", "nationality1", "biographyAuthor3Es", "biographyAuthor3En",  1992, 2022, "author3"),
            new AuthorEntity("author4", "nationality1", "biographyAuthor4Es", "biographyAuthor4En",  1993, 2023, "author4")
    );

    private static List<Author> authors = List.of(
            new Author("author1", "nationality1", new LocaleString("biographyAuthor1Es", "biographyAuthor2En"),  1990, 2020, "author1"),
            new Author("author2", "nationality2", new LocaleString("biographyAuthor2Es", "biographyAuthor2En"),  1991, 2021, "author2"),
            new Author("author3", "nationality1", new LocaleString("biographyAuthor3Es", "biographyAuthor3En"),  1992, 2022, "author3"),
            new Author("author4", "nationality1", new LocaleString("biographyAuthor4Es", "biographyAuthor4En"),  1993, 2023, "author4")
    );

    private static List<AuthorCollectionQuery> authorCollectionQueries = List.of(
            new AuthorCollectionQuery("author1", "author1"),
            new AuthorCollectionQuery("author2", "author2"),
            new AuthorCollectionQuery("author3", "author3"),
            new AuthorCollectionQuery("author4", "author4")
    );

    private static List<AuthorQuery> authorQueries = List.of(
            new AuthorQuery("author1", "author1"),
            new AuthorQuery("author2", "author2"),
            new AuthorQuery("author3", "author3"),
            new AuthorQuery("author4", "author4")
    );

    public static List<AuthorEntity> getAuthorEntities() {
        return List.copyOf(authorEntities);
    }

    public static AuthorEntity getAuthorEntity(int position) {
        return authorEntities.get(position);
    }

    public static List<Author> getAuthors() {
        return List.copyOf(authors);
    }

    public static Author getAuthor(int position) {
        return authors.get(position);
    }

    public static List<AuthorCollectionQuery> getAuthorCollectionQueries() {
        return List.copyOf(authorCollectionQueries);
    }

    public static AuthorCollectionQuery getAuthorCollectionQuery(int position) {
        return authorCollectionQueries.get(position);
    }

    public static List<AuthorQuery> getAuthorQueries() {
        return List.copyOf(authorQueries);
    }

    public static AuthorQuery getAuthorQuery(int position) {
        return authorQueries.get(position);
    }

}
