package es.cesguiro.lang;

public class LanguageUtil {

    private static LanguageUtil instance;

    private LanguageProvider languageProvider = new DefaultLanguageProvider();

    public static synchronized LanguageUtil getInstance() {
        if (instance == null) {
            instance = new LanguageUtil();
        }
        return instance;
    }


    public String getLanguage() {
        return languageProvider.getLanguage();
    }

    /**
     * This method is used to set the language provider. It is used for testing purposes.
     *
     * @param languageProvider the language provider to set
     */
    public void setLanguageProvider(LanguageProvider languageProvider) {
        if (languageProvider == null) {
            throw new IllegalArgumentException("Language provider cannot be null");
        }
        this.languageProvider = languageProvider;
    }

    /**
     * This method is used to reset the instance. It is used for testing purposes.
     */
    public static synchronized void resetInstance() {
        instance = null;
    }

}
