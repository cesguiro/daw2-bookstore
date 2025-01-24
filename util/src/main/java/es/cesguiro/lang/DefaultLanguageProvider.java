package es.cesguiro.lang;

import es.cesguiro.exception.AppFileNotFoundException;
import es.cesguiro.exception.LanguageException;
import es.cesguiro.property.PropertyUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DefaultLanguageProvider implements LanguageProvider {

    private final List<String> supportedLanguages;
    private final String defaultLanguage;

    public DefaultLanguageProvider() {
        this.defaultLanguage = PropertyUtil.getInstance().getProperty("app.default.language", "es");
        String supportedLanguagesConfig = PropertyUtil.getInstance().getProperty("app.supported.languages", this.defaultLanguage);
        this.supportedLanguages = Arrays.asList(supportedLanguagesConfig.split(","));
    }

    public DefaultLanguageProvider(String propertiesFile) {
        this.defaultLanguage = PropertyUtil.getInstance(propertiesFile).getProperty("app.default.language", "es");
        String supportedLanguagesConfig = PropertyUtil.getInstance(propertiesFile).getProperty("app.supported.languages", this.defaultLanguage);
        this.supportedLanguages = Arrays.asList(supportedLanguagesConfig.split(","));
    }

    @Override
    public String getLanguage() {
        String language = Locale.getDefault().getLanguage();
        if(language.isEmpty() || !supportedLanguages.contains(language)) {
            language = PropertyUtil.getInstance().getProperty("app.default.language");
        }
        if (!supportedLanguages.contains(language)) {
            throw new LanguageException("Language not supported: " + language);
        }
        return language;
    }
}
