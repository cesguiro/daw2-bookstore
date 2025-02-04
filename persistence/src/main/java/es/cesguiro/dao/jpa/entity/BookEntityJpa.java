package es.cesguiro.dao.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class BookEntityJpa {

    @Id
    private Long id;
    private String isbn;
    @Column(name = "title_es")
    private String titleEs;
    @Column(name = "title_en")
    private String titleEn;
    @Column(name = "synopsis_es")
    private String synopsisEs;
    @Column(name = "synopsis_en")
    private String synopsisEn;
    @Column(name = "base_price")
    private BigDecimal basePrice;
    @Column(name = "discount_percentage")
    private double discountPercentage;
    private String cover;
    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @ManyToMany
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<AuthorEntityJpa> authors;

    @ManyToMany
    @JoinTable(
            name = "books_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<GenreEntityJpa> genres;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private PublisherEntityJpa publisher;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntityJpa category;
}
