package es.cesguiro.usecase.book.query.mapper;

import es.cesguiro.model.Book;
import es.cesguiro.repository.model.BookEntity;
import es.cesguiro.usecase.book.query.model.BookCollectionDto;
import es.cesguiro.usecase.book.query.model.BookDto;

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

    public static BookCollectionDto toBookCollectionDto(Book book){
        if(book == null){
            return null;
        }
        return new BookCollectionDto(
                book.getIsbn(),
                book.getTitle(),
                book.getBasePrice(),
                book.getDiscountPercentage(),
                book.calculateFinalPrice(),
                book.getCover(),
                book.getAuthors().stream().map(AuthorMapper::toAuthorCollectionDto).toList()
        );
    }

    public static BookDto toBookDto(Book book){
        if(book == null){
            return null;
        }
        return new BookDto(
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
