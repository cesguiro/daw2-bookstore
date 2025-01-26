package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.model.Author;
import es.cesguiro.repository.model.AuthorEntity;
import es.cesguiro.usecase.book.query.model.AuthorCollectionQuery;
import es.cesguiro.usecase.book.query.model.AuthorQuery;

public class AuthorMapper {

    public static Author toAuthor(AuthorEntity authorEntity) {
        if(authorEntity == null) {
            return null;
        }
        return new Author(
                authorEntity.name(),
                authorEntity.nationality(),
                authorEntity.biography(),
                authorEntity.birthYear(),
                authorEntity.deathYear(),
                authorEntity.slug()
        );
    }

    public static AuthorCollectionQuery toAuthorCollectionDto(Author author) {
        if(author == null){
            return null;
        }
        return new AuthorCollectionQuery(
                author.getName(),
                author.getSlug()
        );
    }

    public static AuthorQuery toAuthorDto(Author author) {
        if(author == null){
            return null;
        }
        return new AuthorQuery(
                author.getName(),
                author.getSlug()
        );
    }

}
