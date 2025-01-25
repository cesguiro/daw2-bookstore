package es.cesguiro.repository.mapper;

import es.cesguiro.dao.jpa.entity.BookEntityJpa;
import es.cesguiro.lang.LanguageUtil;
import es.cesguiro.repository.model.BookEntity;

public class BookMapper {

    public static BookEntity toBookEntity(BookEntityJpa bookEntityJpa) {
        if(bookEntityJpa == null) {
            return null;
        }
        String language = LanguageUtil.getInstance().getLanguage();
        return new BookEntity(
                bookEntityJpa.getIsbn(),
                language.equals("en") ? bookEntityJpa.getTitleEn() : bookEntityJpa.getTitleEs(),
                language.equals("en") ? bookEntityJpa.getSynopsisEn() : bookEntityJpa.getSynopsisEs(),
                bookEntityJpa.getBasePrice(),
                bookEntityJpa.getDiscountPercentage(),
                bookEntityJpa.getCover(),
                null
        );
    }
}
