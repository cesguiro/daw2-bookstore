package es.cesguiro.repository.mapper;

import es.cesguiro.dao.jpa.entity.GenreEntityJpa;
import es.cesguiro.repository.model.GenreEntity;

public class GenreMapper {

    private GenreMapper() {
    }

    public static GenreEntity toGenreEntity(GenreEntityJpa genreEntityJpa) {
        if (genreEntityJpa == null) {
            return null;
        }
        return new GenreEntity(
                genreEntityJpa.getNameEs(),
                genreEntityJpa.getNameEn(),
                genreEntityJpa.getSlug()
        );
    }
}
