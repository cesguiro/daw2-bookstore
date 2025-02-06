package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.locale.LocaleUtil;
import es.cesguiro.model.Book;
import es.cesguiro.model.vo.LocaleString;
import es.cesguiro.repository.model.BookEntity;
import es.cesguiro.usecase.book.query.model.BookCollectionQuery;
import es.cesguiro.usecase.book.query.model.BookQuery;

public class BookMapper {

    public static Book toBook(BookEntity bookEntity) {
        if(bookEntity == null) {
            return null;
        }
        return new Book(
                bookEntity.isbn(),
                new LocaleString(bookEntity.titleEs(), bookEntity.titleEn()),
                new LocaleString(bookEntity.synopsisEs(), bookEntity.synopsisEn()),
                bookEntity.basePrice(),
                bookEntity.discountPercentage(),
                bookEntity.cover(),
                bookEntity.publicationDate()
        );
    }

    public static BookCollectionQuery toBookCollectionQuery(Book book){
        if(book == null){
            return null;
        }
        return new BookCollectionQuery(
                book.getIsbn(),
                book.getTitle(LocaleUtil.getLanguage()),
                book.getBasePrice(),
                book.getDiscountPercentage(),
                book.calculateFinalPrice(),
                book.getCover(),
                book.getAuthors().stream().map(AuthorMapper::toAuthorCollectionQuery).toList()
        );
    }

    public static BookQuery toBookQuery(Book book){
        if(book == null){
            return null;
        }
        return new BookQuery(
                book.getIsbn(),
                book.getTitle(LocaleUtil.getLanguage()),
                book.getSynopsis(LocaleUtil.getLanguage()),
                book.getBasePrice(),
                book.getDiscountPercentage(),
                book.calculateFinalPrice(),
                book.getCover(),
                book.getPublicationDate(),
                PublisherMapper.toPublisherQuery(book.getPublisher()),
                CategoryMapper.toCategoryQuery(book.getCategory()),
                book.getGenres().stream().map(GenreMapper::toGenreQuery).toList(),
                book.getAuthors().stream().map(AuthorMapper::toAuthorQuery).toList()
        );
    }
}
