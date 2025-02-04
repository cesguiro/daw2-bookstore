package es.cesguiro.repository.data;

import es.cesguiro.repository.model.AuthorEntity;

import java.util.List;

public class AuthorData {

    private static final List<AuthorEntity> authorEntities = List.of(
            new AuthorEntity("Author 1", "english", "BiographyEs 1", "BiographyEn 1", 1900, 2000, "author-1"),
            new AuthorEntity("Author 2", "english", "BiographyEs 2", "BiographyEn 2", 1900, 2000, "author-2"),
            new AuthorEntity("Author 3", "english", "BiographyEs 3", "BiographyEn 3", 1900, 2000, "author-3")
    );

    public static List<AuthorEntity> getAuthorEntities() {
        return authorEntities;
    }

    public static AuthorEntity getAuthorEntity(int position) {
        return authorEntities.get(position);
    }
}
