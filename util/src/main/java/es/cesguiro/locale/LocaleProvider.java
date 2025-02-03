package es.cesguiro.locale;

import java.time.LocalDate;

public interface LocaleProvider {

    String getLanguage();
    String formatDate(LocalDate date);
}
