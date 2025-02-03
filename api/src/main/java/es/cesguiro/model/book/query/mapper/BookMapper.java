package es.cesguiro.model.book.query.mapper;

import es.cesguiro.handler.BookHandler;
import es.cesguiro.locale.LocaleUtil;
import es.cesguiro.model.book.query.BookCollectionResponse;
import es.cesguiro.model.book.query.BookResponse;
import es.cesguiro.property.PropertyUtil;
import es.cesguiro.usecase.book.query.model.BookCollectionQuery;
import es.cesguiro.usecase.book.query.model.BookQuery;

import java.util.Map;

public class BookMapper {

    private BookMapper() {
    }

    public static BookCollectionResponse toBookCollectionResponse(BookCollectionQuery bookCollectionQuery) {
        if (bookCollectionQuery == null) {
            return null;
        }
        return new BookCollectionResponse(
                bookCollectionQuery.isbn(),
                bookCollectionQuery.title(),
                bookCollectionQuery.basePrice(),
                bookCollectionQuery.discount(),
                bookCollectionQuery.finalPrice(),
                bookCollectionQuery.cover(),
                bookCollectionQuery.authors().stream().map(AuthorMapper::toAuthorCollectionResponse).toList(),
                Map.of("_self", PropertyUtil.getInstance().getProperty("app.base.url")
                        + BookHandler.RESOURCE_PATH + "/"
                        + bookCollectionQuery.isbn())
        );
    }

    public static BookResponse toBookResponse(BookQuery bookQuery) {
        if (bookQuery == null) {
            return null;
        }
        return new BookResponse(
                bookQuery.isbn(),
                bookQuery.title(),
                bookQuery.synopsis(),
                bookQuery.basePrice(),
                bookQuery.discount(),
                bookQuery.finalPrice(),
                bookQuery.cover(),
                LocaleUtil.getInstance().formatDate(bookQuery.publicationDate()),
                PublisherMapper.toPublisherResponse(bookQuery.publisher()),
                CategoryMapper.toCategoryResponse(bookQuery.category()),
                bookQuery.genres().stream().map(GenreMapper::toGenreResponse).toList(),
                bookQuery.authors().stream().map(AuthorMapper::toAuthorResponse).toList()
        );
    }


}
