package es.cesguiro.locale;

import es.cesguiro.exception.LocaleException;

import java.time.LocalDate;

public class LocaleUtil {

    private static LocaleUtil instance;
    private final LocaleProvider localeProvider;

    private LocaleUtil(LocaleProvider localeProvider) {
        this.localeProvider = localeProvider;
    }


    public static synchronized LocaleUtil getInstance() {
        if (instance == null) {
            instance = new LocaleUtil(new DefaultLocaleProvider());
        }
        return instance;
    }

    public static synchronized LocaleUtil getInstance(LocaleProvider localeProvider) {
        if (localeProvider == null) {
            throw new LocaleException("Locale provider is required");
        }
        if (instance == null) {
            instance = new LocaleUtil(localeProvider);
        }
        return instance;
    }


    public String getLanguage() {
        return localeProvider.getLanguage();
    }

    public String formatDate(LocalDate date) {
        return localeProvider.formatDate(date);
    }

    /**
     * This method is used to reset the instance. It is used for testing purposes.
     */
    public static synchronized void resetInstance() {
        instance = null;
    }

}
