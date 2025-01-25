package es.cesguiro.repository.mapper;

import es.cesguiro.dao.jpa.entity.AuthorEntityJpa;
import es.cesguiro.lang.LanguageUtil;
import es.cesguiro.repository.model.AuthorEntity;

public class AuthorMapper {

    public static AuthorEntity toAuthorEntity(AuthorEntityJpa authorEntityJpa) {
        if (authorEntityJpa == null) {
            return null;
        }
        String language = LanguageUtil.getInstance().getLanguage();
        return new AuthorEntity(
                authorEntityJpa.getName(),
                authorEntityJpa.getNationality(),
                language.equals("en") ? authorEntityJpa.getBiographyEn() : authorEntityJpa.getBiographyEs(),
                authorEntityJpa.getBirthYear(),
                authorEntityJpa.getDeathYear(),
                authorEntityJpa.getSlug()
        );
    }
}
