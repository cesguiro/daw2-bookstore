package es.cesguiro.daw2_bookstore.controller.mapper;

import es.cesguiro.daw2_bookstore.controller.webModel.response.AuthorDetailResponse;
import es.cesguiro.daw2_bookstore.controller.webModel.response.AuthorSummaryResponse;
import es.cesguiro.domain.service.dto.AuthorDto;

public class AuthorMapper {

    public static AuthorSummaryResponse fromAuthorDtoToAuthorSummaryResponse(AuthorDto authorDto) {
        return new AuthorSummaryResponse(
            authorDto.name(),
            authorDto.slug()
        );
    }

    public static AuthorDetailResponse fromAuthorDtoToAuthorDetailResponse(AuthorDto authorDto) {
        return new AuthorDetailResponse(
            authorDto.name(),
            authorDto.nationality(),
            authorDto.biographyEs(),
            authorDto.biographyEn(),
            authorDto.birthYear(),
            authorDto.deathYear(),
            authorDto.slug()
        );
    }
}
