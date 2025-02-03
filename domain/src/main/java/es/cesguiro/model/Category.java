package es.cesguiro.model;

import es.cesguiro.model.vo.LocaleString;

public class Category {

    private LocaleString name;
    private String slug;

    public Category(LocaleString name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public String getName(String language) {
        return name.getValue(language);
    }

    public void setName(LocaleString name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
