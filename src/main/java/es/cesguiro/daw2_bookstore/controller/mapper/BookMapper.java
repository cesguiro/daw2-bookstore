package es.cesguiro.daw2_bookstore.controller.mapper;

import es.cesguiro.daw2_bookstore.controller.webModel.response.BookDetailResponse;
import es.cesguiro.daw2_bookstore.controller.webModel.response.BookSummaryResponse;
import es.cesguiro.domain.service.dto.BookDto;

public class BookMapper {

    public static BookSummaryResponse fromBookDtoToBookSummaryResponse(BookDto bookDto) {
        return new BookSummaryResponse(
            bookDto.isbn(),
            bookDto.titleEs(),
            bookDto.titleEn(),
            bookDto.basePrice(),
            bookDto.discountPercentage(),
            bookDto.price(),
            bookDto.cover()
        );
    }

    public static BookDetailResponse fromBookDtoToBookDetailResponse(BookDto bookDto) {
        return new BookDetailResponse(
            bookDto.isbn(),
            bookDto.titleEs(),
            bookDto.titleEn(),
            bookDto.synopsisEs(),
            bookDto.synopsisEn(),
            bookDto.basePrice(),
            bookDto.discountPercentage(),
            bookDto.price(),
            bookDto.cover(),
            bookDto.publicationDate(),
            PublisherMapper.fromPublisherDtoToPublisherSummaryResponse(bookDto.publisher()),
            bookDto.authors().stream()
                .map(AuthorMapper::fromAuthorDtoToAuthorSummaryResponse)
                .toList()
        );
    }
}
