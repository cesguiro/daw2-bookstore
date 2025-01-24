package es.cesguiro.lang;

import es.cesguiro.exception.LanguageException;

public class LanguageUtil {

    private static LanguageUtil instance;
    private final LanguageProvider languageProvider;

    private LanguageUtil(LanguageProvider languageProvider) {
        this.languageProvider = languageProvider;
    }


    public static synchronized LanguageUtil getInstance() {
        if (instance == null) {
            instance = new LanguageUtil(new DefaultLanguageProvider());
        }
        return instance;
    }

    public static synchronized LanguageUtil getInstance(LanguageProvider languageProvider) {
        if (languageProvider == null) {
            throw new LanguageException("Language provider is required");
        }
        if (instance == null) {
            instance = new LanguageUtil(languageProvider);
        }
        return instance;
    }


    public String getLanguage() {
        return languageProvider.getLanguage();
    }

    /**
     * This method is used to reset the instance. It is used for testing purposes.
     */
    public static synchronized void resetInstance() {
        instance = null;
    }

}
