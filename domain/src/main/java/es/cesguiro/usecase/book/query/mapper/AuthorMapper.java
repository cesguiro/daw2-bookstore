package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.model.Author;
import es.cesguiro.model.vo.LocaleString;
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
                new LocaleString(authorEntity.biographyEs(), authorEntity.biographyEn()),
                authorEntity.birthYear(),
                authorEntity.deathYear(),
                authorEntity.slug()
        );
    }

    public static AuthorCollectionQuery toAuthorCollectionQuery(Author author) {
        if(author == null){
            return null;
        }
        return new AuthorCollectionQuery(
                author.getName(),
                author.getSlug()
        );
    }

    public static AuthorQuery toAuthorQuery(Author author) {
        if(author == null){
            return null;
        }
        return new AuthorQuery(
                author.getName(),
                author.getSlug()
        );
    }

}
