package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.locale.LocaleUtil;
import es.cesguiro.model.Genre;
import es.cesguiro.model.vo.LocaleString;
import es.cesguiro.repository.model.GenreEntity;
import es.cesguiro.usecase.book.query.model.GenreQuery;

public class GenreMapper {

    public static Genre toGenre(GenreEntity genreEntity) {
        if(genreEntity == null) {
            return null;
        }
        return new Genre(
                new LocaleString(genreEntity.nameEs(), genreEntity.nameEn()),
                genreEntity.slug()
        );
    }

    public static GenreQuery toGenreQuery(Genre genre) {
        if(genre == null){
            return null;
        }
        return new GenreQuery(
                genre.getName(LocaleUtil.getLocaleProvider().getLanguage()),
                genre.getSlug()
        );
    }
}
