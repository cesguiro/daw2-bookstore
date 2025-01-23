package es.cesguiro.repository.mapper;

import es.cesguiro.dao.jpa.entity.AuthorEntityJpa;
import es.cesguiro.repository.model.AuthorEntity;

public class AuthorMapper {

    public static AuthorEntity toAuthorEntity(AuthorEntityJpa authorEntityJpa) {
        return new AuthorEntity(
                authorEntityJpa.getName(),
                authorEntityJpa.getNationality(),
                authorEntityJpa.getBiographyEs(),
                authorEntityJpa.getBirthYear(),
                authorEntityJpa.getDeathYear(),
                authorEntityJpa.getSlug()
        );
    }
}
