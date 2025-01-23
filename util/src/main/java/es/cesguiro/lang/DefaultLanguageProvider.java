package es.cesguiro.lang;

import es.cesguiro.exception.LanguageException;
import es.cesguiro.property.PropertyUtil;

import java.util.List;
import java.util.Locale;

public class DefaultLanguageProvider implements LanguageProvider {

    private static final List<String> SUPPORTED_LANGUAGES = List.of("es", "en");

    @Override
    public String getLanguage() {
        String language = Locale.getDefault().getLanguage();
        if(language.isEmpty() || !SUPPORTED_LANGUAGES.contains(language)) {
            language = PropertyUtil.getInstance().getProperty("app.default.language");
        }
        if (!SUPPORTED_LANGUAGES.contains(language)) {
            throw new LanguageException("Language not supported: " + language);
        }
        return language;
    }
}
