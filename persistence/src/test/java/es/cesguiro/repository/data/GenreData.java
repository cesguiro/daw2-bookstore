package es.cesguiro.repository.data;

import es.cesguiro.dao.jpa.entity.GenreEntityJpa;
import es.cesguiro.repository.model.GenreEntity;

import java.util.List;

public class GenreData {

    private static final List<GenreEntityJpa> genreEntityJpas = List.of(
            new GenreEntityJpa(
                    1L,
                    "GenreEs 1",
                    "GenreEn 1",
                    "slug"
            ),
            new GenreEntityJpa(
                    2L,
                    "GenreEs 2",
                    "GenreEn 2",
                    "slug"
            )
    );

    private static final List<GenreEntity> genreEntities = List.of(
            new GenreEntity(
                    "GenreEs 1",
                    "GenreEn 1",
                    "slug"
            ),
            new GenreEntity(
                    "GenreEs 2",
                    "GenreEn 2",
                    "slug"
            )
    );

    public static List<GenreEntityJpa> getGenreEntityJpas() {
        return genreEntityJpas;
    }

    public static GenreEntityJpa getGenreEntityJpa(int position) {
        return genreEntityJpas.get(position);
    }

    public static List<GenreEntity> getGenreEntities() {
        return genreEntities;
    }

    public static GenreEntity getGenreEntity(int position) {
        return genreEntities.get(position);
    }
}
