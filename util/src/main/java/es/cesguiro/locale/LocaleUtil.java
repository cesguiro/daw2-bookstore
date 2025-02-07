package es.cesguiro.locale;

import es.cesguiro.exception.LocaleException;

import java.time.LocalDate;

public class LocaleUtil {

    private static LocaleProvider localeProvider;

    private LocaleUtil() {
        throw new LocaleException("Utility class");
    }

    public static LocaleProvider getLocaleProvider() {
        if (localeProvider == null) {
            LocaleUtil.localeProvider = new DefaultLocaleProvider();
        }
        return LocaleUtil.localeProvider;
    }

    public static void setLocaleProvider(LocaleProvider localeProvider) {
        if (localeProvider == null) {
            throw new LocaleException("Locale provider is required");
        }
        LocaleUtil.localeProvider = localeProvider;
    }

    /**
     * Reset the locale provider to null. This method is intended for testing purposes only.
     */
    public static void resetLocaleProvider() {
        LocaleUtil.localeProvider = null;
    }

}
