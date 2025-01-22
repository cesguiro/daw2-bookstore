package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.model.Genre;
import es.cesguiro.repository.model.GenreEntity;
import es.cesguiro.usecase.book.query.model.GenreDto;

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

    public static GenreDto toGenreDto(Genre genre) {
        if(genre == null){
            return null;
        }
        return new GenreDto(
                genre.getName(),
                genre.getSlug()
        );
    }
}
