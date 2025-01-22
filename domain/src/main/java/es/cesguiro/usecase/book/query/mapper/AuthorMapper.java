package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.model.Author;
import es.cesguiro.repository.model.AuthorEntity;
import es.cesguiro.usecase.book.query.model.AuthorCollectionDto;
import es.cesguiro.usecase.book.query.model.AuthorDto;

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

    public static AuthorCollectionDto toAuthorCollectionDto(Author author) {
        if(author == null){
            return null;
        }
        return new AuthorCollectionDto(
                author.getName(),
                author.getSlug()
        );
    }

    public static AuthorDto toAuthorDto(Author author) {
        if(author == null){
            return null;
        }
        return new AuthorDto(
                author.getName(),
                author.getSlug()
        );
    }

}
