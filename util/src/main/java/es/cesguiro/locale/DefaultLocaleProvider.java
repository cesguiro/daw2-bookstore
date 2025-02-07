package es.cesguiro.locale;

import es.cesguiro.property.PropertyUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DefaultLocaleProvider implements LocaleProvider {

    private final List<String> supportedLanguages;
    private final String defaultLanguage;
    private final Locale locale;

    public DefaultLocaleProvider() {
        this.defaultLanguage = PropertyUtil.getPropertyProvider().getProperty("app.default.language", "es");
        String supportedLanguagesConfig = PropertyUtil.getPropertyProvider().getProperty("app.supported.languages", this.defaultLanguage);
        this.supportedLanguages = Arrays.asList(supportedLanguagesConfig.split(","));
        this.locale = Locale.getDefault();
        if (!supportedLanguages.contains(this.locale.getLanguage())) {
            Locale.setDefault(Locale.of(this.defaultLanguage));
        }
    }


    @Override
    public String getLanguage() {
        String language = Locale.getDefault().getLanguage();
        if(language.isEmpty() || !supportedLanguages.contains(language)) {
            return defaultLanguage;
        }
        return language;
    }

    @Override
    public String formatDate(LocalDate date) {
        String pattern;
        if (this.locale.getLanguage().equals("es")) {
            pattern = "dd/MM/yyyy";
        } else {
            pattern = "yyyy/MM/dd";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
        return simpleDateFormat.format(java.sql.Date.valueOf(date));

    }
}
