package es.cesguiro.dao.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authors")
public class AuthorEntityJpa {

    @Id
    private Long id;
    private String name;
    private String nationality;
    @Column(name = "biography_es")
    private String biographyEs;
    @Column(name = "biography_en")
    private String biographyEn;
    @Column(name = "birth_year")
    private int birthYear;
    @Column(name = "death_year")
    private Integer deathYear;
    private String slug;

    @ManyToMany(mappedBy = "authors")
    private List<BookEntityJpa> books;
}
