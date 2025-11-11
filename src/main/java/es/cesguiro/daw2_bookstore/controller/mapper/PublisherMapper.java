package es.cesguiro.daw2_bookstore.controller.mapper;

import es.cesguiro.daw2_bookstore.controller.webModel.response.PublisherDetailResponse;
import es.cesguiro.daw2_bookstore.controller.webModel.response.PublisherSummaryResponse;
import es.cesguiro.domain.service.dto.PublisherDto;

public class PublisherMapper {

    public static PublisherSummaryResponse fromPublisherDtoToPublisherSummaryResponse(PublisherDto publisherDto) {
        return new PublisherSummaryResponse(
            publisherDto.name(),
            publisherDto.slug()
        );
    }

    public static PublisherDetailResponse fromPublisherDtoToPublisherDetailResponse(PublisherDto publisherDto) {
        return new PublisherDetailResponse(
            publisherDto.name(),
            publisherDto.slug()
        );
    }
}
