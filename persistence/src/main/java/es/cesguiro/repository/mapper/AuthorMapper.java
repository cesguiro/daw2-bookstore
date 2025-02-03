package es.cesguiro.repository.mapper;

import es.cesguiro.dao.jpa.entity.AuthorEntityJpa;
import es.cesguiro.locale.LocaleUtil;
import es.cesguiro.repository.model.AuthorEntity;

public class AuthorMapper {

    private AuthorMapper() {
    }

    public static AuthorEntity toAuthorEntity(AuthorEntityJpa authorEntityJpa) {
        if (authorEntityJpa == null) {
            return null;
        }
        return new AuthorEntity(
                authorEntityJpa.getName(),
                authorEntityJpa.getNationality(),
                authorEntityJpa.getBiographyEs(),
                authorEntityJpa.getBiographyEn(),
                authorEntityJpa.getBirthYear(),
                authorEntityJpa.getDeathYear(),
                authorEntityJpa.getSlug()
        );
    }
}
