package es.cesguiro.model;

import es.cesguiro.model.vo.LocaleString;

public class Author {

    private String name;
    private String nationality;
    private LocaleString biography;
    private int birthYear;
    private Integer deathYear;
    private String slug;

    public Author(String name, String nationality, LocaleString biography, int birthYear, Integer deathYear, String slug) {
        this.name = name;
        this.nationality = nationality;
        this.biography = biography;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.slug = slug;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBiography(String language) {
        return biography.getValue(language);
    }

    public void setBiography(LocaleString biography) {
        this.biography = biography;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
