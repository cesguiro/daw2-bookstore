package es.cesguiro.repository.mapper;

import es.cesguiro.dao.jpa.entity.BookEntityJpa;
import es.cesguiro.repository.model.BookEntity;

public class BookMapper {

    public static BookEntity toBookEntity(BookEntityJpa bookEntityJpa) {
        if(bookEntityJpa == null) {
            return null;
        }
        return new BookEntity(
                bookEntityJpa.getIsbn(),
                bookEntityJpa.getTitleEs(),
                bookEntityJpa.getSynopsisEs(),
                bookEntityJpa.getBasePrice(),
                bookEntityJpa.getDiscountPercentage(),
                bookEntityJpa.getCover(),
                null
        );
    }
}
