package es.cesguiro.daw2_bookstore.util;

import es.cesguiro.daw2_bookstore.controller.webModel.request.BookInsertRequest;
import es.cesguiro.daw2_bookstore.controller.webModel.request.BookUpdateRequest;
import es.cesguiro.domain.service.dto.AuthorDto;
import es.cesguiro.domain.service.dto.BookDto;
import es.cesguiro.domain.service.dto.PublisherDto;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;
import java.util.List;

import static org.instancio.Select.field;

public class InstancioModel {

    private static final String ISBN_PATTERN = "#d#d#d#d#d#d#d#d#d#d#d#d#d";
    private static final String SLUG_PATTERN = "#c#c#c-#c#c#c";

    /*******************************************************************************
     * Modelos de Publisher
     **************************************************************************/
    public static final Model<PublisherDto> PUBLISHER_DTO_MODEL = Instancio.of(PublisherDto.class)
            .generate(field(PublisherDto.class, "slug"), gen -> gen.text().pattern(SLUG_PATTERN))
            .toModel();
    /*******************************************************************************
     * Modelos de Author
     **************************************************************************/
    public static final Model<AuthorDto> AUTHOR_DTO_MODEL = Instancio.of(AuthorDto.class)
            .generate(field(AuthorDto.class, "slug"), gen -> gen.text().pattern(SLUG_PATTERN))
            .toModel();

    public static Model<List<AuthorDto>> AUTHOR_DTO_LIST_MODEL = Instancio.ofList(AUTHOR_DTO_MODEL)
            .toModel();

    /*******************************************************************************
     * Modelos de Book
     **************************************************************************/

    public static final Model<BookDto> BOOK_DTO_MODEL = Instancio.of(BookDto.class)
            .generate(field(BookDto::isbn), gen -> gen.text().pattern(ISBN_PATTERN))
            .generate(field(BookDto::basePrice), gen -> gen.math().bigDecimal().range(new BigDecimal("1.00"), new BigDecimal("500.00")))
            .generate(field(BookDto::discountPercentage), gen -> gen.math().bigDecimal().range(new BigDecimal("0.00"), new BigDecimal("100.00")))
            .generate(field(BookDto::publicationDate), gen -> gen.temporal().localDate().past())
            .setModel(field(BookDto::publisher), PUBLISHER_DTO_MODEL)
            .setModel(field(BookDto::authors), AUTHOR_DTO_LIST_MODEL)
            .toModel();

    public static final Model<BookInsertRequest> BOOK_INSERT_REQUEST_MODEL = Instancio.of(BookInsertRequest.class)
            .generate(field(BookInsertRequest::isbn), gen -> gen.text().pattern(ISBN_PATTERN))
            .generate(field(BookInsertRequest::basePrice), gen -> gen.math().bigDecimal().range(new BigDecimal("1.00"), new BigDecimal("500.00")))
            .generate(field(BookInsertRequest::discountPercentage), gen -> gen.math().bigDecimal().range(new BigDecimal("0.00"), new BigDecimal("100.00")))
            .generate(field(BookInsertRequest::publicationDate), gen -> gen.temporal().localDate().past())
            .toModel();
    public static final Model<BookUpdateRequest> BOOK_UPDATE_REQUEST_MODEL = Instancio.of(BookUpdateRequest.class)
            .generate(field(BookUpdateRequest::isbn), gen -> gen.text().pattern(ISBN_PATTERN))
            .generate(field(BookUpdateRequest::basePrice), gen -> gen.math().bigDecimal().range(new BigDecimal("1.00"), new BigDecimal("500.00")))
            .generate(field(BookUpdateRequest::discountPercentage), gen -> gen.math().bigDecimal().range(new BigDecimal("0.00"), new BigDecimal("100.00")))
            .generate(field(BookUpdateRequest::publicationDate), gen -> gen.temporal().localDate().past())
            .toModel();

}
