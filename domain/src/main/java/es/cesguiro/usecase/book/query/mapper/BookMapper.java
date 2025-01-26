package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.model.Book;
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
                bookEntity.title(),
                bookEntity.synopsis(),
                bookEntity.basePrice(),
                bookEntity.discountPercentage(),
                bookEntity.cover(),
                bookEntity.publicationDate()
        );
    }

    public static BookCollectionQuery toBookCollectionDto(Book book){
        if(book == null){
            return null;
        }
        return new BookCollectionQuery(
                book.getIsbn(),
                book.getTitle(),
                book.getBasePrice(),
                book.getDiscountPercentage(),
                book.calculateFinalPrice(),
                book.getCover(),
                book.getAuthors().stream().map(AuthorMapper::toAuthorCollectionDto).toList()
        );
    }

    public static BookQuery toBookDto(Book book){
        if(book == null){
            return null;
        }
        return new BookQuery(
                book.getIsbn(),
                book.getTitle(),
                book.getSynopsis(),
                book.getBasePrice(),
                book.getDiscountPercentage(),
                book.calculateFinalPrice(),
                book.getCover(),
                book.getPublicationDate(),
                PublisherMapper.toPublisherDto(book.getPublisher()),
                CategoryMapper.toCategoryDto(book.getCategory()),
                book.getGenres().stream().map(GenreMapper::toGenreDto).toList(),
                book.getAuthors().stream().map(AuthorMapper::toAuthorDto).toList()
        );
    }
}
