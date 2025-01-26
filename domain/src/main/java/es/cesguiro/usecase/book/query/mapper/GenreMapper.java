package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.model.Genre;
import es.cesguiro.repository.model.GenreEntity;
import es.cesguiro.usecase.book.query.model.GenreQuery;

public class GenreMapper {

    public static Genre toGenre(GenreEntity genreEntity) {
        if(genreEntity == null) {
            return null;
        }
        return new Genre(
                genreEntity.name(),
                genreEntity.slug()
        );
    }

    public static GenreQuery toGenreDto(Genre genre) {
        if(genre == null){
            return null;
        }
        return new GenreQuery(
                genre.getName(),
                genre.getSlug()
        );
    }
}
