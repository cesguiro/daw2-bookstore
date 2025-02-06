package es.cesguiro.locale;

import es.cesguiro.exception.LocaleException;

import java.time.LocalDate;

public class LocaleUtil {

    private static LocaleProvider localeProvider = new DefaultLocaleProvider();

    private LocaleUtil() {
        throw new LocaleException("Utility class");
    }


    public static LocaleProvider getLocaleProvider() {
        return LocaleUtil.localeProvider;
    }

    public static void setLocaleProvider(LocaleProvider localeProvider) {
        if (localeProvider == null) {
            throw new LocaleException("Locale provider is required");
        }
        LocaleUtil.localeProvider = localeProvider;
    }

    public static String getLanguage() {
        return localeProvider.getLanguage();
    }

    public static String formatDate(LocalDate date) {
        return localeProvider.formatDate(date);
    }

}
